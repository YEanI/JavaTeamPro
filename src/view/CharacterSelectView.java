package view;

import app.GameApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CharacterReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by minchul on 2017-06-12.
 */
public class CharacterSelectView extends BaseView{
    private JPanel panel1;
    private JButton btnStartGame;
    private JLabel label1;
    private JTextPane textPane1;
    private JButton btnBack;

    final private List<CharacterReport> characterReports;
    private int currentReport = 0;

    public CharacterSelectView(Object param) {
        super(param);
        characterReports = new ArrayList<>();
        loadCharacterReport();
        setCharacterImage(0);

        panel1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                currentReport++;
                if(currentReport >= characterReports.size()){
                    currentReport = 0;
                }
                setCharacterImage(currentReport);
            }

        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(MainView.class);
            }
        });
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(GameView.class, characterReports.get(currentReport));
            }
        });
    }

    @Override
    public JPanel getContentPanel(){
        return panel1;
    }

    @Override
    public void onViewChanged() {

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

    }

    private void loadCharacterReport() {
        String json = null;
        try {
            InputStream reportStream= GameApplication.class.getResourceAsStream("/myFile.dat");
            InputStreamReader inputStreamReader = new InputStreamReader(reportStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            json = bufferedReader.lines().collect(Collectors.joining());
            bufferedReader.close();
            inputStreamReader.close();
            reportStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(json == null){
            //TODO read fail!
            return;
        }
        Gson gson = new Gson();
        characterReports.clear();
        characterReports.addAll(gson.fromJson(json, new TypeToken<List<CharacterReport>>(){}.getType()));
    }
}
