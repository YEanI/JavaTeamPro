package view;

import data.Bomb;
import data.GameInfoChangeListener;
import data.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class MyPanel extends JPanel {
    private static final int PREF_W = 600;
    private static final int PREF_H = 800;

    private static final int TIMER_DELAY = 20;
    private static final int MAX_TIME = 60;
    public static final int DELAY = 100;
    private final Timer gameTick;
    private final Timer timer;
    private final GameInfoChangeListener listener;
    private Player player;
    private ArrayList<Bomb> bombList;
    private int time;
    private int level;
    private Random random = new Random();
    public MyPanel(GameInfoChangeListener listener) throws IOException {
        bombList = new ArrayList<>();
        time = MAX_TIME;
        level = 1;
        player = new Player(PREF_H);
        this.listener = listener;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //not use
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    player.setAX(-1);
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    player.setAX(1);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        gameTick = new Timer(TIMER_DELAY, new GameTickListener());
        gameTick.start();

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                if(time == 0){
                    //TODO level up!
                    level++;
                    time = MAX_TIME;
                }
                listener.onChange(time, level);
            }
        });
        timer.start();

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player != null) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        for (Bomb bomb : bombList) {
            g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
    }

    private class GameTickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (random.nextInt(100) < 20 ) { //폭탄은 30% 확률로 생성됨.
                //TODO add level dependency to Bomb constructor
                Bomb bomb = new Bomb(random.nextInt(PREF_W), random.nextInt(3) + 3);
                bombList.add(bomb);
            }

            //TODO 폭탄 움직이기
            for (Bomb b : bombList) {
                b.move();
            }
            ArrayList<Bomb> removeList = new ArrayList<>();
            for (Bomb b : bombList) {
                if (b.getY() > PREF_H){
                    removeList.add(b);
                }
            }
            bombList.removeAll(removeList);

            player.move();

            //TODO 충돌 체크
            boolean isCrush = false;
            for(Bomb b : bombList){
                if(player.checkCrush(b)){
                    isCrush = true;
                    break;
                }
            }

            if(isCrush){
                //TODO show game over
                gameTick.stop();
                timer.stop();
            }

            repaint();
        }
    }


}