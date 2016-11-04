package mdl.sinlov.android.cordova.demo.plugin;


import mdl.sinlov.android.cordovabridge.MDLCordovaActivity;
import mdl.sinlov.android.cordovabridge.utils.ResourceUtil;

public class WeiBoCordovaActivity extends MDLCordovaActivity {

    @Override
    public String setIndex() {
        String weibo_index = ResourceUtil.getStrByRes(getApplicationContext(), "str_index_weibo_login");
//        return "file:///android_asset/www/index.html";
        return weibo_index;
    }
}
