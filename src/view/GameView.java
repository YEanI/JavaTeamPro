package view;

import app.GameConstants;
import data.Bomb;
import data.DrawingObject;
import data.Game;
import data.Player;
import viewcomponent.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView {
    private static final int UPDATE_SCREEN_DELAY = 10;
    private static final int LABEL_UPDATE_DELAY = 100;

    private final Timer gameTick;
    private final Timer timer;

    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    private Game game;
    private Player player;
    private List<Bomb> bombs;
    private boolean isPlaying;
    private Random random = new Random();

    public GameView() {
        gameTick = new Timer(UPDATE_SCREEN_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBomb();
                moveBombList();
                player.move(GameConstants.SCREEN_WIDTH);
                gamePanel.repaint();
                checkCrush();
            }
        });

        timer = new Timer(LABEL_UPDATE_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void createUIComponents() {
        panel = new JPanel();
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

        levelLabel = new JLabel();
        timeLabel = new JLabel();
        levelLabel.setText("1");
        timeLabel.setText("60");

        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //not use
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    initGame();
                    startGame();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setStatus(Player.Status.ACCEL);
                    player.setDirection(Player.Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setStatus(Player.Status.ACCEL);
                    player.setDirection(Player.Direction.RIGHT);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (player.getStatus() == Player.Status.ACCEL && player.getDirection() == Player.Direction.LEFT) {
                        player.setStatus(Player.Status.IDLE);
                        player.setDirection(Player.Direction.LEFT);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (player.getStatus() == Player.Status.ACCEL && player.getDirection() == Player.Direction.RIGHT) {
                        player.setStatus(Player.Status.IDLE);
                        player.setDirection(Player.Direction.RIGHT);
                    }
                }
            }
        });

        onViewCreate();
    }

    private void onViewCreate() {
        initGame();
    }

    public JPanel getContentPanel() {
        return panel;
    }

    private void initGame() {
        if (bombs != null) { //remove all
            final List<DrawingObject> removeList = new ArrayList<>();
            for (Bomb b : bombs) {
                removeList.add(b.getObject());
            }
            gamePanel.removeDrawingObject(removeList);
            bombs.clear();
        }
        bombs = new ArrayList<>();
        if (player == null) {
            player = new Player();
            DrawingObject object = player.getObject();
            int height = object.getHeight();
            object.setPoint(new Point(GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT - height));
            gamePanel.addDrawingObject(object);

            player.setMax_dx(10);
            player.setBraking_force(10);
            player.setAx(10);
        }
        isPlaying = false;
    }

    private void startGame() {
        if (!isPlaying) {
            isPlaying = true;
            gameTick.start();
            timer.start();
        }

    }

    private void checkCrush() {
        boolean isCrush = false;

        for (Bomb b : bombs) {
            if (Game.CHECK_CRUSH(player, b)) {
                isCrush = true;
                break;
            }
        }

        if (isCrush) {
            isPlaying = false;
            gameTick.stop();
            timer.stop();
        }
    }

    private void moveBombList() {
        bombs.forEach(Bomb::move);

        final List<Bomb> removeList = new ArrayList<>();
        final List<DrawingObject> drawingObjects = new ArrayList<>();
        for (Bomb b : bombs) {
            if (b.getObject().getPoint().y > GameConstants.SCREEN_HEIGHT) {
                removeList.add(b);
                drawingObjects.add(b.getObject());
            }
        }
        gamePanel.removeDrawingObject(drawingObjects);
        bombs.removeAll(removeList);
    }

    private void createBomb() {
        if (random.nextDouble() < 0.1) { //폭탄은 30% 확률로 생성됨.
            Bomb bomb = new Bomb();
            bomb.getObject().setPoint(new Point(random.nextInt(GameConstants.SCREEN_WIDTH), 0));
            bomb.setAy(random.nextDouble() / 10d + 0.1);
            bombs.add(bomb);
            gamePanel.addDrawingObject(bomb.getObject());
        }
    }

}
