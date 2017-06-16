package view;

import app.GameConstants;
import viewcomponent.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minchul on 2017-06-12.
 */
public class CharacterSelectView extends BaseView{
    private JPanel panel1;
    private GamePanel gamePanel1;

    @Override
    public JPanel getContentPanel(){
        throw new RuntimeException("not yet implement!");
    }


    private void createUIComponents() {
        panel1 = new JPanel();
        gamePanel1 = new GamePanel();

        panel1.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
    }

}
