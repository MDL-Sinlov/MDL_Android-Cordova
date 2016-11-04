package mdl.sinlov.android.cordova.demo.plugin;

import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * <pre>
 *     sinlov
 *
 *     /\__/\
 *    /`    '\
 *  ≈≈≈ 0  0 ≈≈≈ Hello world!
 *    \  --  /
 *   /        \
 *  /          \
 * |            |
 *  \  ||  ||  /
 *   \_oo__oo_/≡≡≡≡≡≡≡≡o
 *
 * </pre>
 * Created by sinlov on 16/11/3.
 */
public class WeiBoLoginPlugin extends CordovaPlugin {

    static String TAG = "LifeCyclePlugin";
    public static final String BACKBUTTONMULTIPAGE_URL = "file:///android_asset/www/backbuttonmultipage/index.html";

    String calls = "";

    @Override
    public Boolean shouldAllowNavigation(String url) {
        return true;
    }

    @Override
    public Boolean shouldAllowRequest(String url) {
        return true;
    }

    @Override
    public Boolean shouldOpenExternalUrl(String url) {
        return super.shouldOpenExternalUrl(url);
    }

    @Override
    public Boolean shouldAllowBridgeAccess(String url) {
        return true;
    }

    @Override
    public void onStart() {
        calls += "start,";
        LOG.d(TAG, "onStart");
    }

    @Override
    public void onPause(boolean multitasking) {
        calls += "pause,";
        LOG.d(TAG, "onPause");
    }

    @Override
    public void onResume(boolean multitasking) {
        calls += "resume,";
        LOG.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        calls += "stop,";
        LOG.d(TAG, "onStop");
    }

    public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("start")) {
            String className = args.isNull(0) ? WeiBoCordovaActivity.class.getCanonicalName() : args.getString(0);
            String startUrl = args.getString(1);
            JSONObject extraPrefs = args.getJSONObject(2);
            this.startActivity(className, startUrl, extraPrefs);
            callbackContext.success();
            return true;
        }
        return false;
    }

    public void startActivity(String className, String startUrl, JSONObject extraPrefs) throws JSONException {
        try {
            if (!startUrl.contains(":")) {
                startUrl = "file:///android_asset/www/" + startUrl;
            }
            Intent intent = new Intent(this.cordova.getActivity(), Class.forName(className));
            intent.putExtra("testStartUrl", startUrl);
            Iterator<String> iter = extraPrefs.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                intent.putExtra(key, extraPrefs.getString(key));
            }
            LOG.d(TAG, "Starting activity %s", className);
            this.cordova.getActivity().startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.e(TAG, "Error starting activity %s", className);
        }
    }
}
