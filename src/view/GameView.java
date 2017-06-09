package view;

import data.GameInfoChangeListener;
import viewcomponent.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView implements GameInfoChangeListener {
    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    public GameView() {

    }

    private void createUIComponents() {
        gamePanel = new GamePanel(this);
        levelLabel = new JLabel();
        timeLabel = new JLabel();
        levelLabel.setText("1");
        timeLabel.setText("60");
    }


    @Override
    public void onChange(int time, int level) {
        timeLabel.setText(String.valueOf(time));
        levelLabel.setText(String.valueOf(level));

    }

    public JPanel getContentPanel() {
        return panel;
    }
}
