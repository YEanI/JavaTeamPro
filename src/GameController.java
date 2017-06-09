import view.BaseView;
import view.GameView;
import view.MainView;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * Created by minchul on 2017-06-09.
 */
public class GameController implements MainView.MainViewListener{

    private JFrame frame;
    private final MainView mainView;
    private final GameView gameView;

    public GameController(){
        mainView = new MainView(GameController.this);
        gameView = new GameView();

        frame = new JFrame("JavaTeamProject");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    private void start(){
        switchView(mainView);

    }

    private void switchView(BaseView currentView) {
        frame.setVisible(false);
        frame.setContentPane(currentView.getContentPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void onClickStartGame() {
        switchView(gameView);
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.start();
    }

}
