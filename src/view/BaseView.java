package view;

import app.GameApplication;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-09.
 */
public abstract class BaseView {
    public abstract JPanel getContentPanel();

    void startView(Class<? extends BaseView> viewClass){
        GameApplication.getInstance().startView(viewClass);
    }
}
