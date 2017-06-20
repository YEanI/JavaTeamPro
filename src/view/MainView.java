package view;

import app.GameConstants;
import data.DrawingObject;
import viewcomponent.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import static app.GameConstants.DEFAULT_CHARACTER_SIZE;
import static app.GameConstants.SCREEN_WIDTH;

/**
 * Created by minchul on 2017-06-09.
 */
public class MainView extends BaseView {

    private static final int ANIM_DELAY = 20;
    private JPanel panel;
    private JButton btnStartGame;
    private JButton btnRanking;
    private JButton btnHelp;
    private GamePanel gamePanel;

    private final List<DrawingObject> drawingObjects;
    private final int ON_SCREEN_PLAYER;

    public MainView() {
        ON_SCREEN_PLAYER = SCREEN_WIDTH / DEFAULT_CHARACTER_SIZE;
        drawingObjects = new ArrayList<>();
        for (int i = 0; i < ON_SCREEN_PLAYER + 2; i++) {
//            drawingObjects.add(new PlayerBuilder().setImage("/images/character_mario.png", DEFAULT_CHARACTER_SIZE).build().getObject());
        }
        gamePanel.addDrawingObject(drawingObjects);
        new Timer(ANIM_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animation();
            }
        }).start();
    }

    int tick = 0;

    private void animation() {
        final int frequency = 100;

        final double progress = (double) tick / (double) frequency;
        int index = 0;
        for (DrawingObject object : drawingObjects) {
            final Point point = object.getPoint();
            point.setLocation(getLocation(index, progress), point.getY());
            index++;
        }
        tick++;
        if (tick > frequency) {
            tick = 0;
        }
        gamePanel.repaint();
    }

    private double getLocation(final int index, final double progress) {
        final double length = (DEFAULT_CHARACTER_SIZE + SCREEN_WIDTH);
        final double location = length * progress;
        final double locationWithIndex = location + (index - 1) * DEFAULT_CHARACTER_SIZE;
        return locationWithIndex;
    }

    private void createUIComponents() {
        panel = new JPanel();
        gamePanel = new GamePanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

        btnStartGame = new JButton();
        btnStartGame.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(CharacterSelectView.class);
            }
        });
        btnRanking = new JButton();
        btnRanking.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(RankingView.class);
            }
        });
        btnHelp = new JButton();
        btnHelp.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(HelpView.class);
            }
        });

        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                animation();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void onViewChanged() {

    }
}
