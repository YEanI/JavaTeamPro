package view;

import com.google.gson.Gson;
import data.*;
import util.GameConstants;
import app.ViewCaller;
import util.ImageUtil;
import viewcomponent.GamePanel;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.*;
import java.util.List;

import static data.Grade.*;
import static util.GameConstants.*;
import static java.awt.event.KeyEvent.*;
import static view.GameView.PlayerState.IDLE;


/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView {

    private static final int UPDATE_SCREEN_DELAY = 10;

    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel scoreLabel;
    private JLabel semesterLabel;
    private JLabel currCalcGradeLabel;

    private final Timer gameTick;
    private GameInfo gameInfo;
    private final CharacterReport characterReport;

    private Player player;
    private List<Bomb> bombs;

    private final Random random;

    private GameState gameState;
    private PlayerState playerState;


    public GameView(ViewCaller viewCaller) {
        super(viewCaller);
        Gson gson = new Gson();
        characterReport = gson.fromJson(viewCaller.getBundleJson(), CharacterReport.class);

        random = new Random();
        gameTick = new Timer(UPDATE_SCREEN_DELAY, e -> {
            createBomb();
            moveBombList();
            movePlayer();
            gamePanel.repaint();
            checkCrush();
        });
    }


    private void createUIComponents() {
        panel = new JPanel();
        URL imageURL = HelpView.class.getResource("/images/background.png");
        ImageIcon icon = new ImageIcon(imageURL);
        Image scaleImage = ImageUtil.getScaleImage(icon.getImage(), GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        DrawingObject background = new DrawingObject();
        gamePanel.addDrawingObject(background);
        background.setImage(scaleImage);
        final Point point = background.getPoint();
        point.setLocation(0, 0);
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
                    setGameState(GameState.RUNNING);
                }
                break;
            case RUNNING:
                if (keyCode == VK_LEFT) {
                    playerState = PlayerState.ACCEL_LEFT;
                } else if (keyCode == VK_RIGHT) {
                    playerState = PlayerState.ACCEL_RIGHT;
                }
                if (keyCode == VK_SPACE || keyCode == VK_ESCAPE) {
                    setGameState(GameState.PAUSE);
                }
                break;
            case PAUSE:
                if (keyCode == VK_SPACE || keyCode == VK_ESCAPE) {
                    setGameState(GameState.RUNNING);
                }
                break;
            case GAME_OVER:
                if (keyCode == VK_SPACE) {
                    setGameState(GameState.INIT);
                }
                break;
        }
    }



    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void onViewChanged() {
        setGameState(GameState.INIT);
    }


    private void checkCrush() {
        final List<Bomb> crusheds = new ArrayList<>();
        final List<DrawingObject> crushedObjects = new ArrayList<>();
        for (final Bomb b : bombs) {
            if (checkCrush(player, b)) {
                onCrushBomb(b);
                crusheds.add(b);
                crushedObjects.add(b.getObject());
            }
        }
        bombs.removeAll(crusheds);
        gamePanel.removeDrawingObject(crushedObjects);
    }

    private void onCrushBomb(Bomb bomb) {
        gameInfo.setScore(gameInfo.getScore() + bomb.getGrade().getScore());
        gameInfo.getScoreList()[gameInfo.getSemester()] += bomb.getGrade().getScore();
        gameInfo.setFullCredit(gameInfo.getFullCredit() + CREDIT_PER_CRASH);

        if(bomb.getGrade() != F){
            gameInfo.setAcademicCredit(gameInfo.getAcademicCredit() + CREDIT_PER_CRASH);
        }

        if (gameInfo.getFullCredit() % ACADEMIC_CREDIT_PER_SEMESTER == 0) {
            gameInfo.getScoreList()[gameInfo.getSemester()] /= (ACADEMIC_CREDIT_PER_SEMESTER / CREDIT_PER_CRASH);
            gameInfo.setSemester(gameInfo.getSemester() + 1);
        }
        if (gameInfo.getAcademicCredit() >= MAX_ACADEMIC_CREDIT) {
            setGameState(GameState.GAME_OVER);
        }
        if (gameInfo.getSemester() == MAX_SEMESTER) {
            setGameState(GameState.GAME_OVER);
        }

        updateLabel();

    }

    private void updateLabel() {
        semesterLabel.setText(String.valueOf(gameInfo.getSemester()) + "학기");
        if(gameInfo.getAcademicCredit() != 0) {
            final String point = String.format("%.1f", (double) gameInfo.getScore() / (double) gameInfo.getAcademicCredit());
            scoreLabel.setText("평점 : " + point);
        }else{
            scoreLabel.setText("평점 : 0");
        }
        currCalcGradeLabel.setText("이수학점 : " + String.valueOf(gameInfo.getFullCredit()));
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
            final Bomb bomb = newBomb();
            bombs.add(bomb);
            gamePanel.addDrawingObject(bomb.getObject());
        }
    }
    private Bomb newBomb() {
        final int prob = random.nextInt(100);
        Bomb b = new Bomb();
        final DrawingObject drawingObject = b.getObject();
        final Point point = b.getObject().getPoint();

        final Map<String, Integer> probability = characterReport.getPercent();
        final Map<String, Double> speed = characterReport.getGradeSpeed();

        if(prob < probability.get("A")){
            b.setGrade(A);
        }else if(prob < probability.get("A") + probability.get("B")){
            b.setGrade(B);
        }else if(prob < probability.get("A") + probability.get("B") + probability.get("C")){
            b.setGrade(C);
        }else if(prob < probability.get("A") + probability.get("B") + probability.get("C") + probability.get("D")){
            b.setGrade(D);
        }else{
            b.setGrade(F);
        }

        b.setAy(speed.get(b.getGrade().name()));
        drawingObject.setImage(ImageUtil.loadImage(b.getGrade().getImagePath(), DEFAULT_BOMB_SIZE));
        point.setLocation(random.nextInt(SCREEN_WIDTH - DEFAULT_BOMB_SIZE) , 0);
        return b;
    }
    private void moveBomb(Bomb bomb) {
        final Point point = bomb.getObject().getPoint();
        bomb.setDy(bomb.getDy() + bomb.getAy());
        point.setLocation(point.getX(), point.getY() + bomb.getDy());
    }

    private void moveBombList() {
        bombs.forEach(this::moveBomb);

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

    enum GameState {
        INIT, RUNNING, GAME_OVER, PAUSE
    }

    private void setGameState(GameState newState){
        gameState = newState;
        switch (newState){
            case INIT:
                onInitGame();
                break;
            case RUNNING:
                onStartGame();
                break;
            case PAUSE:
                onPauseGame();
                break;
            case GAME_OVER:
                onGameOver();
                break;
        }
    }

    private void onInitGame() {
        playerState = IDLE;
        if(bombs != null){
            removeAllBombs();
        }
        bombs = new ArrayList<>();
        initPlayer();
        gameInfo = new GameInfo();
        gameInfo.setCharacterName(characterReport.getName());
        gamePanel.addDrawingObject(player.getObject());
        updateLabel();
    }

    private void initPlayer() {
        player = new Player();
        player.setBrakingForce(characterReport.getBrakingForce());
        player.setMaxDx(characterReport.getMaxDx());
        player.setAx(characterReport.getAx());
        player.getObject().setImage(ImageUtil.loadImage(characterReport.getPath(), DEFAULT_CHARACTER_SIZE));
        final DrawingObject object = player.getObject();
        final int height = object.getHeight();
        final Point point = object.getPoint();
        point.setLocation(SCREEN_WIDTH / 2, SCREEN_HEIGHT - height);
    }

    private void removeAllBombs() {
        final List<DrawingObject> drawingObjects = new ArrayList<>();
        for (final Bomb b : bombs) {
            drawingObjects.add(b.getObject());
        }
        gamePanel.removeDrawingObject(drawingObjects);
        bombs.clear();
    }

    private void onPauseGame() {
        gameTick.stop();
    }

    private void onStartGame() {
        gameTick.start();
    }

    private void onGameOver() {
        gameTick.stop();
        if(gameInfo.getSemester() == MAX_SEMESTER) {
            JOptionPane.showMessageDialog(gamePanel, "혁명의 씨앗이여, 더 큰 세상으로 나아가라");
        }else {
            ViewCaller viewCaller = new ViewCaller(GameResultView.class);
            viewCaller.setBundleJson(gameInfo);
            startView(viewCaller);
        }
    }

}
