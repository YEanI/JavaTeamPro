package view;

import app.GameApplication;
import app.ViewCaller;
import data.GameInfo;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by minchul on 2017-06-21.
 */
public class GameResultViewTest {
    @Test
    public void onViewChanged() throws Exception {
        GameInfo gameInfo = new GameInfo();
        GameApplication application = GameApplication.getApplication();

        ViewCaller viewCaller = new ViewCaller(GameResultView.class);
        viewCaller.setBundleJson(gameInfo);


    }

}