package view;

import viewcomponent.GamePanel;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-08.
 */
public class GameView extends BaseView {
    private JPanel panel;
    private GamePanel gamePanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    public GameView() {

    }

    private void createUIComponents() {
        gamePanel = new GamePanel((time, level) -> {
            levelLabel.setText(String.valueOf(level));
            timeLabel.setText(String.valueOf(time));
        });
        levelLabel = new JLabel();
        timeLabel = new JLabel();
        levelLabel.setText("1");
        timeLabel.setText("60");


    }

    public GamePanel getGamePanel(){ return gamePanel; }
    public JPanel getContentPanel() {
        return panel;
    }

    /**
     * Created by minchul on 2017-06-08.
     */
    public interface GameInfoChangeListener {
        void onChange(int time, int level);
    }
}
