package view;

import app.GameApplication;
import app.ViewCaller;
import javafx.application.Application;

import javax.swing.*;

/**
 * Created by minchul on 2017-06-09.
 */
public abstract class BaseView {
    final private ViewCaller viewCaller;

    public BaseView(ViewCaller viewCaller){
        this.viewCaller = viewCaller;
    }

    public abstract JPanel getContentPanel();

    void startView(ViewCaller viewCaller){
        GameApplication.getApplication().startView(viewCaller);
    }
    void startView(Class<? extends BaseView> target){
        final ViewCaller newViewCaller = new ViewCaller(target);
        GameApplication.getApplication().startView(newViewCaller);
    }
    public abstract void onViewChanged();

    protected ViewCaller getViewCaller(){
        return viewCaller;
    }
}
