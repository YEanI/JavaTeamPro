package view;

import app.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by minchul on 2017-06-09.
 */
public class MainView extends BaseView {

    private JPanel panel;
    private JButton btnStartGame;
    private JButton btnRanking;
    private JButton btnHelp;

    public MainView(){

    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        btnStartGame = new JButton();
        btnStartGame.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(GameView.class);
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
    }

    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void onSwiched() {

    }
}
