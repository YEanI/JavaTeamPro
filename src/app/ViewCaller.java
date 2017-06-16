package app;

import com.google.gson.Gson;
import view.BaseView;

/**
 * Created by hyeji on 2017-06-16.
 */
public class ViewCaller {
    final Class<? extends BaseView> target;
    private String bundleJson;
    private int intValue = 0;

    public ViewCaller(Class<? extends BaseView> viewClass) {
        target = viewClass;

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
