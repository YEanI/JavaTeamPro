package app;

import com.google.gson.Gson;
import view.BaseView;

/**
 * Created by hyeji on 2017-06-16.
 */
public class ViewCaller {
    private BaseView prevView;
    final Class<? extends BaseView> target;
    private String bundleJson;
    private int intValue = 0;

    public ViewCaller(BaseView prevView, Class<? extends BaseView> viewClass) {
        this.prevView = prevView;
        this.target = viewClass;

    }

    public void setBundleJson(Object object){
        Gson gson = new Gson();
        this.bundleJson = gson.toJson(object);
    }

    public String getBundleJson() {
        return bundleJson;
    }

    public void setInt(int value) {
        this.intValue = value;
    }
    public int getInt(){
        return intValue;
    }
}
