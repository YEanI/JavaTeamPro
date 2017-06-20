package view;

import app.GameApplication;
import app.ViewCaller;
import javafx.application.Application;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-09.
 */
public abstract class BaseView {
    final protected GameApplication application;
    final protected ViewCaller viewCaller;

    public BaseView(GameApplication application, ViewCaller viewCaller){
        this.application = application;
        this.viewCaller = viewCaller;
    }
    public abstract JPanel getContentPanel();

    void startView(Class<? extends BaseView> viewClass){
        application.startView(viewClass);
    }

    void startView(ViewCaller viewCaller){
        application.startView(viewCaller);
    }

    public abstract void onViewChanged();

    public GameApplication getApplication(){
        return application;
    }
    public ViewCaller getViewCaller(){
        return viewCaller;
    }
}
