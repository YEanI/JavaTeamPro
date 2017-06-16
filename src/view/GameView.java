package view;

import app.BombBuilder;
import app.GameConstants;
import app.PlayerBuilder;
import data.Bomb;
import data.DrawingObject;
import data.Game;
import data.Player;
import viewcomponent.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.GameConstants.*;
import static java.awt.event.KeyEvent.*;
import static view.GameView.PlayerState.IDLE;


/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView {
    private static final int UPDATE_SCREEN_DELAY = 10;

    private final Timer gameTick;
    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    private Game game;
    private Player player;
    private List<Bomb> bombs;
    private final Random random;

    private GameState gameState;
    private PlayerState playerState;

    public GameView() {
        initGame();

        random = new Random();
        gameTick = new Timer(UPDATE_SCREEN_DELAY, e -> {
            createBomb();
            moveBombList();
            movePlayer();
            gamePanel.repaint();
            checkCrush();
        });

    }

    private void initGame() {
        gameState = GameState.INIT;
        playerState = IDLE;
        bombs = new ArrayList<>();

        //init player
        player = new PlayerBuilder()
                .setMax_dx(10)
                .setBraking_force(3)
                .setAx(3)
                .setImage("/images/character_mario.png", DEFAULT_CHARACTER_SIZE)
                .build();

        gamePanel.addDrawingObject(player.getObject());

    }


    private void createUIComponents() {
        panel = new JPanel();
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

        panel.addKeyListener(new MyKeyListener());

    }

    private void onKeyReleased(int keyCode) {
        if ((keyCode == VK_LEFT && playerState == PlayerState.ACCEL_LEFT) || (keyCode == VK_RIGHT && playerState == PlayerState.ACCEL_RIGHT)) {
            playerState = PlayerState.BRAKE;
        }
    }

    private void onKeyPressed(int keyCode) {
        switch (gameState) {
            case INIT:
                if (keyCode == VK_SPACE) {
                    gameState = GameState.RUNNING;
                    startGame();
                }
                break;
            case RUNNING:
                if (keyCode == VK_LEFT) {
                    playerState = PlayerState.ACCEL_LEFT;
                } else if (keyCode == VK_RIGHT) {
                    playerState = PlayerState.ACCEL_RIGHT;
                }
                if (keyCode == VK_SPACE || keyCode == VK_ESCAPE) {
                    gameState = GameState.PAUSE;
                    pauseGame();
                }
                break;
            case PAUSE:
                if (keyCode == VK_SPACE || keyCode == VK_ESCAPE) {
                    gameState = GameState.RUNNING;
                    resumeGame();
                }
                break;
            case GAME_OVER:
                if (keyCode == VK_SPACE) {
                    gameState = GameState.RUNNING;
                    startGame();
                }
                break;
        }
    }


    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    private void resumeGame() {
        gameTick.start();
    }

    private void pauseGame() {
        gameTick.stop();
    }

    private void startGame() {
        //remove bombs
        final List<DrawingObject> drawingObjects = new ArrayList<>();
        for (final Bomb b : bombs) {
            drawingObjects.add(b.getObject());
        }
        gamePanel.removeDrawingObject(drawingObjects);
        bombs.clear();
        gameTick.start();
    }

    private void stopGame() {
        gameTick.stop();
    }


    private void checkCrush() {
        boolean isCrush = false;
        final List<Bomb> crusheds = new ArrayList<>();
        final List<DrawingObject> crushedObjects = new ArrayList<>();
        for (final Bomb b : bombs) {
            if (checkCrush(player, b)) {
                onCrushBomb(b);
                crusheds.add(b);
                crushedObjects.add(b.getObject());
//                isCrush = true;
//                break;
            }
        }
        bombs.removeAll(crusheds);
        gamePanel.removeDrawingObject(crushedObjects);
//        if (isCrush) {
//            gameState = GameState.GAME_OVER;
//            stopGame();
//        }
    }

    int score = 0;

    int crushNumber = 0;
    int senester = 0;
    private void onCrushBomb(Bomb bomb) {

        bomb.getGrade();
        if (bomb.getGrade() == Bomb.Grade.A) {
            score = score + 4;
        }
        if (bomb.getGrade() == Bomb.Grade.B) {
            score = score + 3;
        }
        if (bomb.getGrade() == Bomb.Grade.C) {
            score += 2;
        }
        if (bomb.getGrade() == Bomb.Grade.D) {
            score += 1;
        }

        //scoreLabel.setText(score);
        //GUI켜서 편집하는거 어떻게 하지 scoreLabel하나 만들어줘야 하는데

        crushNumber = crushNumber + 1;
        //이 메소드가 한번 실행된다는 건 한번 부딪혔다는 거니까 부딪힐 때마다 횟수한번씩 늘리기
        //근데 부딪힐 때마다 이 메소드가 실행되게 하는 거 어떻게 하지<<으앙
        if(crushNumber % 6 == 0){
            senester += 1;
        }
        levelLabel.setText(String.valueOf(senester));
        timeLabel.setText(String.valueOf(score));
        //semesterLabel.setText(semester)
        //학기라벨도 하나더 만들어줘야 함...
        if (senester == 8) {
            //48번 부딪히면 게임멈춰라
            stopGame();
            //이러면 되나
        }


    }

    private boolean checkCrush(final Player player, final Bomb bomb) {
        final DrawingObject object1 = player.getObject();
        final DrawingObject object2 = bomb.getObject();
        final int bomb_x = object2.getPoint().x;
        final int bomb_y = object2.getPoint().y;
        final int player_x = object1.getPoint().x;
        final int player_y = object1.getPoint().y;

        //crush on left
        if (bomb_x < player_x + DEFAULT_CRUSH_OFFSET_X && bomb_x + DEFAULT_BOMB_SIZE > player_x + DEFAULT_CRUSH_OFFSET_X) {
            if (bomb_y + DEFAULT_BOMB_SIZE > player_y + DEFAULT_CRUSH_OFFSET_Y) {
                return true;
            }
        }
        //crush on right
        if (bomb_x < player_x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X && player_x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X < bomb_x + DEFAULT_BOMB_SIZE) {
            if (bomb_y + DEFAULT_BOMB_SIZE > player_y + DEFAULT_CRUSH_OFFSET_Y) {
                return true;
            }
        }
        //crush on center
        if (bomb_x > player_x + DEFAULT_CRUSH_OFFSET_X && player_x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X > bomb_x + DEFAULT_BOMB_SIZE) {
            if (bomb_y + DEFAULT_BOMB_SIZE > player_y + DEFAULT_CRUSH_OFFSET_Y) {
                return true;
            }
        }
        return false;
    }

    private void createBomb() {
        if (random.nextDouble() < 0.1) { //폭탄은 30% 확률로 생성됨.
            final Bomb bomb = new BombBuilder()
                    .setPoint(new Point(random.nextInt(SCREEN_WIDTH), 0))
                    .setAy(random.nextDouble() / 10d + 0.1)
                    .build();
            bombs.add(bomb);
            gamePanel.addDrawingObject(bomb.getObject());
        }
    }

    private void moveBombList() {
        bombs.forEach(Bomb::move);

        final List<Bomb> removeList = new ArrayList<>();
        final List<DrawingObject> drawingObjects = new ArrayList<>();
        for (final Bomb b : bombs) {
            if (b.getObject().getPoint().y > GameConstants.SCREEN_HEIGHT) {
                removeList.add(b);
                drawingObjects.add(b.getObject());
            }
        }
        gamePanel.removeDrawingObject(drawingObjects);
        bombs.removeAll(removeList);
    }

    private void movePlayer() {
        switch (playerState) {
            case ACCEL_LEFT: {
                final double newDx = player.getDx() - player.getAx();
                player.setDx(newDx > -player.getMaxDx() ? newDx : -player.getMaxDx());
            }
            break;
            case ACCEL_RIGHT: {
                final double newDx = player.getDx() + player.getAx();
                player.setDx(newDx < player.getMaxDx() ? newDx : player.getMaxDx());
            }
            break;
            case BRAKE:
                final double absDx = Math.abs(player.getDx()) - player.getBrakingForce();
                if (absDx > 0) {
                    player.setDx(player.getDx() > 0 ? absDx : -absDx);
                } else {
                    player.setDx(0);
                }
                break;
            case IDLE:
        }
        if (playerState != IDLE) {
            final Point point = player.getObject().getPoint();
            double newX = point.getX() + player.getDx();
            if (newX < 0) {
                newX = 0;
                player.setDx(0);
            } else if (newX + player.getObject().getWidth() > SCREEN_WIDTH) {
                newX = SCREEN_WIDTH - player.getObject().getWidth();
                player.setDx(0);
            }
            point.setLocation(newX, point.getY());
        }
    }

    enum GameState {
        INIT, RUNNING, GAME_OVER, PAUSE
    }

    enum PlayerState {
        ACCEL_LEFT, ACCEL_RIGHT, BRAKE, IDLE
    }

    private class MyKeyListener implements KeyListener {
        private static final byte ESC_KEY_MASK = 0x8;
        private static final byte SPACE_KEY_MASK = 0x4;
        private static final byte LEFT_KEY_MASK = 0x1;
        private static final byte RIGHT_KEY_MASK = 0x2;
        private byte keyState = 0x0;

        @Override
        public void keyTyped(KeyEvent e) {
            //not use
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    if ((keyState & ESC_KEY_MASK) == 0) {
                        keyState |= ESC_KEY_MASK;
                        onKeyPressed(e.getKeyCode());
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if ((keyState & SPACE_KEY_MASK) == 0) {
                        keyState |= SPACE_KEY_MASK;
                        onKeyPressed(e.getKeyCode());
                    }
                    break;
                case VK_LEFT:
                    if ((keyState & LEFT_KEY_MASK) == 0) { //key press
                        keyState |= LEFT_KEY_MASK;
                        onKeyPressed(e.getKeyCode());
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if ((keyState & RIGHT_KEY_MASK) == 0) { //key press
                        keyState |= RIGHT_KEY_MASK;
                        onKeyPressed(e.getKeyCode());
                    }
                    break;
                default:
                    break;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    keyState &= ~ESC_KEY_MASK;
                    onKeyReleased(e.getKeyCode());
                    break;
                case KeyEvent.VK_SPACE:
                    keyState &= ~SPACE_KEY_MASK;
                    onKeyReleased(e.getKeyCode());
                    break;
                case VK_LEFT:
                    keyState &= ~LEFT_KEY_MASK;
                    onKeyReleased(e.getKeyCode());
                    break;
                case KeyEvent.VK_RIGHT:
                    keyState &= ~RIGHT_KEY_MASK;
                    onKeyReleased(e.getKeyCode());
                    break;
                default:
                    break;
            }

        }
    }
}
