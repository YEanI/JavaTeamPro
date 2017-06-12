package viewcomponent;

import data.Bomb;
import data.DrawingObject;
import data.Game;
import data.Player;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel {
    private static final int PREF_W = 500;
    private static final int PREF_H = 650;

    private static final int TIMER_DELAY = 20;
    private static final int MAX_TIME = 60;
    public static final int DELAY = 100;
    private final Timer gameTick;
    private final Timer timer;
    private final GameView.GameInfoChangeListener listener;
    private Player player;
    private ArrayList<Bomb> bombList;
    private int time;
    private int level;
    private Random random = new Random();
    private boolean isPlaying;

    public GamePanel(GameView.GameInfoChangeListener listener){

        initPlayer();
        initGame();

        this.listener = listener;
        this.addKeyListener(new KeyListener() {
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
                    player.setStatus(Player.Status.IDLE);
                    player.setDirection(Player.Direction.LEFT);
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    player.setStatus(Player.Status.IDLE);
                    player.setDirection(Player.Direction.RIGHT);
                }
            }
        });
        gameTick = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createBomb();

                moveBombList();

                player.move(PREF_W);
                checkCrush();

                repaint();
            }
        });

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChange(time, level);
            }
        });
    }

    private void initGame() {
        time = MAX_TIME;
        level = 1;

        bombList = new ArrayList<>();
        isPlaying = false;
    }

    private void startGame() {
        if(!isPlaying) {
            isPlaying = true;
            gameTick.start();
            timer.start();
        }
    }

    private void initPlayer() {
        player = new Player();
        DrawingObject object = player.getObject();
        int height = object.getHeight();
        object.setPoint(new Point(PREF_W/2, PREF_H - height));

        player.setMax_dx(30);
        player.setBraking_force(30);
        player.setAx(30);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player != null) {
            drawingObject(g, player.getObject());
        }
        for (Bomb bomb : bombList) {
            drawingObject(g, bomb.getObject());
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawingObject(Graphics g, DrawingObject object) {
        g.drawImage(object.getImage(), object.getPoint().x, object.getPoint().y, this);
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
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
            if (b.getObject().getPoint().y > PREF_H){
                removeList.add(b);
            }
        }
        bombList.removeAll(removeList);
    }

    private void createBomb() {
        if (random.nextInt(100) < 10 ) { //폭탄은 30% 확률로 생성됨.
            Bomb bomb  = new Bomb();
            bomb.getObject().setPoint(new Point(random.nextInt(PREF_W), 0));
            bomb.setAy(random.nextInt(2) + 1);
            bombList.add(bomb);
        }
    }


}