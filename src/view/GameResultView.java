package view;

import DB.DataBaseHelper;
import app.GameConstants;
import com.google.gson.Gson;
import data.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by minchul on 2017-06-12.
 */
public class GameResultView extends BaseView {
    Game game;
    private JPanel panel;
    private JTextArea textArea1;
    private JLabel label1;
    private JButton btnRanking;
    private JButton btnRetry;
    private JButton btnMain;
    private JLabel label2;

    public GameResultView(){
        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
                dataBaseHelper.addRecord(game.getUserName(), game.getScore(), game.getSemester(), game.getCharacterName());
                startView(RankingView.class);
            }
        });
        btnRetry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(CharacterSelectView.class);
            }
        });
        btnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(MainView.class);
            }
        });
    }

    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    private String getDenomination(double point) {
        if (point == 4.0) {
            return "신";
        }
        if (3.5 <= point && point <= 3.9) {
            return "교수님의 사랑";
        }
        if (3.0 <= point && point <= 3.49) {
            return "일반인";
        }
        if (2.5 <= point && point <= 2.9) {
            return "오락문화의 선구자";
        }
        if (2.0<= point && point <= 2.49) {
            return "불가촉천민";
        }
        if (1.5 <= point && point <= 1.9) {
            return "플랑크톤";
        }
        return "시대를 앞서가는 혁명의 씨앗";
    }

    @Override
    public void onViewChanged() {
        Gson gson = new Gson();
        if (this.viewCaller.getBundleJson() != null) {
            game = gson.fromJson(this.viewCaller.getBundleJson(), Game.class);
            double point = (double) game.getScore() / (double) game.getAcademicCredit();

            label1.setText("당신은 " + getDenomination(point) + " 입니다");
            label1.setForeground(Color.BLUE);
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                t.append(i + 1).append("학기평점 : ").append(String.format("%.2f", game.getScoreList()[i])).append("\n");
            }
            textArea1.setText(t.toString() + "총평점" + String.format("%.2f", point));
        }
    }
    public void registerName() {
        label2.setText("사용자 이름 등록:"+ game.getCharacterName());
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

    }
}
