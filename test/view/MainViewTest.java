package view;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Created by minchul on 2017-06-15.
 */
public class MainViewTest {
    private final MainView mainView;
    private Method getLocationMethod;

    public MainViewTest() {
        mainView = new MainView(null);
        try {
            getLocationMethod = MainView.class.getDeclaredMethod("getLocation", int.class, double.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        getLocationMethod.setAccessible(true);
    }

    @Test
    public void locationTest() throws Exception {
        final int progress = 100;
        final int index = 1;
        double location = (double) getLocationMethod.invoke(mainView, index, progress);
        assertEquals(0, location, 0);
    }
}