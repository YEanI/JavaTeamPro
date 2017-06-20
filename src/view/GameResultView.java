package view;

import DB.DataBaseHelper;
import app.ViewCaller;
import com.google.gson.Gson;
import data.GameInfo;
import util.GameConstants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by minchul on 2017-06-12.
 */
public class GameResultView extends BaseView {
    private JPanel panel;
    private JTextArea textArea1;
    private JLabel label1;
    private JButton btnRanking;
    private JButton btnRetry;
    private JButton btnMain;
    private JLabel label2;
    private JTextField textField1;


    private GameInfo gameInfo;

    public GameResultView(ViewCaller viewCaller) {
        super(viewCaller);
        Gson gson = new Gson();
        gameInfo = gson.fromJson(viewCaller.getBundleJson(), GameInfo.class);

        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
                dataBaseHelper.addRecord(textField1.getText(), gameInfo.getScore(), gameInfo.getSemester(), gameInfo.getCharacterName());
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
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onUpdateFieldState(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onUpdateFieldState(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onUpdateFieldState(e);
            }

            private void onUpdateFieldState(DocumentEvent e) {
                btnRanking.setEnabled(textField1.getText().length() != 0);
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
        if (2.0 <= point && point <= 2.49) {
            return "불가촉천민";
        }
        if (1.5 <= point && point <= 1.9) {
            return "플랑크톤";
        }
        return "시대를 앞서가는 혁명의 씨앗";
    }

    @Override
    public void onViewChanged() {
        double point = (double) gameInfo.getScore() / (double) gameInfo.getAcademicCredit();

        label1.setText("당신은 " + getDenomination(point) + " 입니다");
        label1.setForeground(Color.BLUE);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            stringBuilder.append(i + 1).append("학기평점 : ").append(String.format("%.1f", gameInfo.getScoreList()[i])).append("\n");
        }
        textArea1.setText(stringBuilder.toString() + "총평점 : " + String.format("%.1f", point));
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

    }
}
