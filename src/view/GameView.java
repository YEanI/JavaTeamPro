package view;

import data.Bomb;
import data.DrawingObject;
import data.Game;
import data.Player;
import viewcomponent.GamePanel;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * Created by minchul on 2017-06-08.
 *
 */
public class GameView extends BaseView {
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 650;
    private static final int MOVE_ELEMENT_DELAY = 20;
    private static final int LABEL_UPDATE_DELAY = 100;
    private final Timer gameTick;
    private final Timer timer;

    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    private Game game;
    private Player player;
    private java.util.List<Bomb> bombList;
    private boolean isPlaying;
    private Random random = new Random();

    public GameView() {
        gameTick = new Timer(MOVE_ELEMENT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createBomb();

                moveBombList();

                player.move(SCREEN_WIDTH);
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
        gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

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
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    initGame();
                    startGame();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    player.setStatus(Player.Status.ACCEL);
                    player.setDirection(Player.Direction.LEFT);
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    player.setStatus(Player.Status.ACCEL);
                    player.setDirection(Player.Direction.RIGHT);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    if(player.getStatus() == Player.Status.ACCEL && player.getDirection() == Player.Direction.LEFT) {
                        player.setStatus(Player.Status.IDLE);
                        player.setDirection(Player.Direction.LEFT);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if(player.getStatus() == Player.Status.ACCEL && player.getDirection() == Player.Direction.RIGHT) {
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

    public GamePanel getGamePanel(){ return gamePanel; }
    public JPanel getContentPanel() {
        return panel;
    }

    private void initGame() {

        bombList = new ArrayList<>();
        if(player == null) {
            player = new Player();
            DrawingObject object = player.getObject();
            int height = object.getHeight();
            object.setPoint(new Point(SCREEN_WIDTH / 2, SCREEN_HEIGHT - height));
            gamePanel.addDrawingObject(object);

            player.setMax_dx(30);
            player.setBraking_force(30);
            player.setAx(30);
        }
        isPlaying = false;
    }

    private void startGame() {
        if(!isPlaying) {
            isPlaying = true;
            gameTick.start();
            timer.start();
        }

    }
    private void checkCrush() {
        boolean isCrush = false;
        for(Bomb b : bombList){
            if(Game.CHECk_CRUSH(player, b)){
                isCrush = true;
                break;
            }
        }

        if(isCrush){
            isPlaying = false;
            gameTick.stop();
            timer.stop();
        }
    }

    private void moveBombList() {
        for (Bomb b : bombList) {
            b.move();
        }
        ArrayList<Bomb> removeList = new ArrayList<>();
        for (Bomb b : bombList) {
            if (b.getObject().getPoint().y > SCREEN_HEIGHT){
                removeList.add(b);
                gamePanel.removeDrawingObject(b.getObject());
            }
        }
        bombList.removeAll(removeList);
    }

    private void createBomb() {
        if (random.nextInt(100) < 10 ) { //폭탄은 30% 확률로 생성됨.
            Bomb bomb  = new Bomb();
            bomb.getObject().setPoint(new Point(random.nextInt(SCREEN_WIDTH), 0));
            bomb.setAy(random.nextInt(2) + 1);
            bombList.add(bomb);
            gamePanel.addDrawingObject(bomb.getObject());
        }
    }

}
