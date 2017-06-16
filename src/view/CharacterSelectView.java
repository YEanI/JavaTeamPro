package view;

import app.GameConstants;
import app.PlayerFactory;
import app.ViewCaller;
import data.CharacterReport;
import data.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by minchul on 2017-06-12.
 */
public class CharacterSelectView extends BaseView{
    JPanel panel1;
    private JButton btnStartGame;
    private List<CharacterReport> characterReports;
    private Integer index;

    @Override
    public JPanel getContentPanel(){
        return panel1;
    }

    @Override
    public void onSwiched() {
        btnStartGame.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final ViewCaller viewCaller = new ViewCaller(GameView.class);
                viewCaller.setBundleJson(index);
                startView(viewCaller);
            }
        });

        this.characterReports = PlayerFactory.getInstance().getCharacterReports();
        Player player = PlayerFactory.getInstance().newPlayer(0);
        player.getObject().setPoint(new Point(0, 0));
        Player player1 = PlayerFactory.getInstance().newPlayer(1);
        player1.getObject().setPoint(new Point(100, 100));
//        gamePanel1.addDrawingObject(player.getObject());
//        gamePanel1.addDrawingObject(player1.getObject());
//        gamePanel1.repaint();
    }

    private void createUIComponents() {
        panel1 = new JPanel();
//        gamePanel1 = new GamePanel();
        panel1.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        btnStartGame = new JButton();
    }
}
