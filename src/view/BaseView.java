package view;

import app.GameApplication;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-09.
 */
public abstract class BaseView {

    private final Object param;

    public BaseView(Object param) {
        this.param = param;
    }

    public abstract JPanel getContentPanel();

    void startView(Class<? extends BaseView> target, Object param) {
        GameApplication.getApplication().startView(target, param);
    }

    void startView(Class<? extends BaseView> target) {
        GameApplication.getApplication().startView(target, null);
    }

    public abstract void onViewChanged();

}
