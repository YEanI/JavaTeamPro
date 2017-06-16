package view;

import app.GameConstants;
import com.google.gson.Gson;
import data.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minchul on 2017-06-12.
 */
public class GameResultView extends BaseView{
    Game game;
    private JPanel panel;

    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void onSwiched() {
        Gson gson = new Gson();
        if(this.viewCaller.getBundleJson() != null) {
            game = gson.fromJson(this.viewCaller.getBundleJson(), Game.class);
            System.out.println(game.getScore());
        }
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

    }
}
