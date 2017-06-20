package app;

import DB.DataBaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CharacterReport;
import view.BaseView;
import view.MainView;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * Created by minchul on 2017-06-09.
 *
 */
public class GameApplication {

    private final JFrame frame;
    private final List<CharacterReport> characterReports;

    private GameApplication(){
        frame = new JFrame("JavaTeamProject");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new MyWindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                DataBaseHelper.getInstance().disconnectDB();
            }

        });
        frame.setLocationRelativeTo(null);
        characterReports = new ArrayList<>();
    }

    private void start(){
        loadCharacterReport();
        DataBaseHelper.getInstance().connectDB();

        final ViewCaller viewCaller = new ViewCaller(null, MainView.class);
        startView(viewCaller);
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

    public void startView(ViewCaller viewCaller) {
        try {
            BaseView viewInstance = viewCaller.target
                    .getDeclaredConstructor(GameApplication.class, ViewCaller.class)
                    .newInstance(this, viewCaller);
            switchView(viewInstance);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
        GameApplication application = new GameApplication();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(application::start);
    }

    class MyWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

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
    }
}
