package view;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by minchul on 2017-06-09.
 */
public class MainView extends BaseView {
    private final MainViewListener listener;
    private JPanel panel;
    private JButton btnStartGame;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public MainView(MainViewListener listener){
        this.listener = listener;
    }

    private void createUIComponents() {
        btnStartGame = new JButton();
        btnStartGame.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onClickStartGame();
            }
        });
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
    }

    public JPanel getContentPanel() {
        return panel;
    }

    public interface MainViewListener{
        void onClickStartGame();
    }
}
