package view;

import app.GameApplication;
import app.ViewCaller;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-09.
 */
public abstract class BaseView {
    public String bundleJson;

    public abstract JPanel getContentPanel();

    void startView(Class<? extends BaseView> viewClass){
        GameApplication.getInstance().startView(viewClass);
    }

    void startView(ViewCaller viewCaller){
        GameApplication.getInstance().startView(viewCaller);
    }

    public abstract void onSwiched();
}
