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
    private final Class<? extends BaseView> MAIN_VIEW_CLASS = MainView.class;

    private static GameApplication application;

    public static GameApplication getApplication(){
        if(application == null){
            application = new GameApplication();
        }
        return application;
    }

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
    }

    private void start(){
        DataBaseHelper.getInstance().connectDB();
        startView(MAIN_VIEW_CLASS, null);
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
        GameApplication application = GameApplication.getApplication();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(application::start);
    }

    public void startView(Class<? extends BaseView> target, Object param) {
        try {
            BaseView viewInstance = target
                    .getDeclaredConstructor(Object.class)
                    .newInstance(param);
            switchView(viewInstance);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
