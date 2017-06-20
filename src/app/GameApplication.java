package app;

import DB.DataBaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CharacterReport;
import util.PlayerFactory;
import view.BaseView;
import view.MainView;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                DataBaseHelper.getInstance().disconnectDB();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        frame.setLocationRelativeTo(null);

    }

    private void start(){
        loadCharacterReport();
        DataBaseHelper.getInstance().connectDB();
        startView(MainView.class);
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
        PlayerFactory.getInstance().setCharacterReports(characterReports);
    }

    public void startView(Class<? extends BaseView> newViewType) {
        try {
            BaseView viewInstance = newViewType.newInstance();
            switchView(viewInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void startView(ViewCaller viewCaller) {
        try {

            BaseView viewInstance = viewCaller.target.newInstance();
            viewInstance.viewCaller = viewCaller;
            switchView(viewInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void switchView(BaseView newView) {
        JPanel content = newView.getContentPanel();
        frame.setVisible(true);
        frame.setContentPane(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        content.requestFocus();
        newView.onViewChanged();
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
