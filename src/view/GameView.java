package view;

import viewcomponent.GamePanel;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView implements GameView.GameInfoChangeListener {
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

    /**
     * Created by minchul on 2017-06-08.
     */
    public static interface GameInfoChangeListener {
        void onChange(int time, int level);
    }
}
