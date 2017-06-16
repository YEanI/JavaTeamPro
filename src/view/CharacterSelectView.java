package view;

import app.GameConstants;
import app.PlayerFactory;
import app.ViewCaller;
import data.CharacterReport;
import data.DrawingObject;
import data.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;

/**
 * Created by minchul on 2017-06-12.
 */
public class CharacterSelectView extends BaseView{
    JPanel panel1;
    private JButton btnStartGame;
    private JLabel label1;
    private JTextPane textPane1;
    private List<CharacterReport> characterReports;
    private int index = 0;

    public CharacterSelectView() {
        panel1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                index++;
                if(index >= PlayerFactory.getInstance().getReportSize()){
                    index = 0;
                }
                setCharacterImage(index);
            }


        });
    }

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
                viewCaller.setInt(index);
                startView(viewCaller);
            }

        });

        this.characterReports = PlayerFactory.getInstance().getCharacterReports();
        setCharacterImage(0);
    }

    private void setCharacterImage(int index) {
        final CharacterReport report = characterReports.get(index);
        URL imageURL = CharacterSelectView.class.getResource(report.getPath());
        ImageIcon imageIcon = new ImageIcon(imageURL);
        label1.setIcon(imageIcon);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("이름: ").append(report.getName()).append('\n')
                .append("최대 속도: ").append(report.getMaxDx()).append('\n')
                .append("가속도: ").append(report.getAx()).append('\n')
                .append("제동력").append(report.getBrakingForce()).append('\n')
                .append("학점의 속도").append(report.getGradeSpeed()).append('\n')
                .append("학점의 확률").append(report.getPercent()).append('\n');

        textPane1.setText(stringBuilder.toString());
    }

    private void createUIComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        textPane1 = new JTextPane();
//        gamePanel1 = new GamePanel();
        panel1.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        btnStartGame = new JButton();
    }
}
