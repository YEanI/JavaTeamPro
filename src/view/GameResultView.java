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
    private JTextArea scoretextArea;
    private JLabel denominationLabel;

    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void onSwiched() {
        Gson gson = new Gson();
        if(this.viewCaller.getBundleJson() != null) {
            game = gson.fromJson(this.viewCaller.getBundleJson(), Game.class);
            double point = (double)game.getScore()/(double)game.getCrushNumber();
            if(point == 4.5){
                game.setDenomination("신");
            }
            if(4.2 <= point && point <= 4.49){
                game.setDenomination("교수님의 사랑");
            }
            if(3.5 <= point && point <= 4.19){
                game.setDenomination("현 체제의 수호자");
            }
            if(2.8 <= point && point <= 3.49){
                game.setDenomination("일반인");
            }
            if(2.3 <= point && point <= 2.79){
                game.setDenomination("일탈을 꿈꾸는 소시민");
            }
            if(1.75 <= point && point <= 2.29){
                game.setDenomination("오락문화의 선구자");
            }
            if(1.0 <= point && point <= 1.74){
                game.setDenomination("불가촉천민");
            }
            if(0.5 <= point && point <= 0.99){
                game.setDenomination("자벌레");
            }
            if(0.1 <= point && point <= 0.49){
                game.setDenomination("플랑크톤");
            }
            if(point == 0){
                game.setDenomination("시대를 앞서가는 혁명의 씨앗");
            }


            denominationLabel.setText("당신은 " + game.getDenomination() + " 입니다");
            int i;
            StringBuilder t = new StringBuilder();
            for(i=0; i<12;i++) {
                t.append(i+1).append("학기평점 : ").append(String.format("%.2f", game.getScoreList()[i])).append("\n");
            }
            scoretextArea.setText(t.toString()+ "총평점"+point);
        }
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

    }
}
