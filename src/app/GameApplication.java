package app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CharacterReport;
import view.BaseView;
import view.GameView;
import view.MainView;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static app.GameConstants.CHARACTER_REPORT_PATH;
import static java.lang.Thread.sleep;

/**
 * Created by minchul on 2017-06-09.
 *
 */
public class GameApplication {


    static private GameApplication instance;
    static public GameApplication getInstance(){
        if (instance == null){
            instance = new GameApplication();
        }
        return instance;
    }

    final private JFrame frame;

    private GameApplication(){
        frame = new JFrame("JavaTeamProject");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    private void start(){
        loadCharacterReport();

        startView(MainView.class);

    }

    private void loadCharacterReport() {
        String json = null;
        try {
            FileReader fileReader = new FileReader(CHARACTER_REPORT_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            json = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(json == null){
            //TODO read fail!
            return;
        }
        List<CharacterReport> characterReports;
        Gson gson = new Gson();
        characterReports = gson.fromJson(json, new TypeToken<List<CharacterReport>>(){}.getType());
        PlayerFactory.getInstance().setChararterReportList(characterReports);
    }

    public void startView(Class<? extends BaseView> newViewType) {
        try {
            BaseView viewInstance = newViewType.newInstance();
            switchView(viewInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void switchView(BaseView newView) {
        JPanel content = newView.getContentPanel();
        frame.setVisible(false);
        frame.setContentPane(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        content.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> GameApplication.getInstance().start());

    }

}
