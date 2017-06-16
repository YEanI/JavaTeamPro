package app;

import com.google.gson.Gson;
import view.BaseView;
import view.GameResultView;

/**
 * Created by hyeji on 2017-06-16.
 */
public class ViewCaller {
    final Class<? extends BaseView> target;
    private String bundleJson;

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
}
