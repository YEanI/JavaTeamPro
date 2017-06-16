package app;

import view.BaseView;
import view.GameView;
import view.MainView;

import javax.swing.*;

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
        frame.setLocationRelativeTo(null);

    }

    private void start(){
        startView(MainView.class);
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
            viewInstance.bundleJson = viewCaller.getBundleJson();
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
        newView.onSwiched();
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
