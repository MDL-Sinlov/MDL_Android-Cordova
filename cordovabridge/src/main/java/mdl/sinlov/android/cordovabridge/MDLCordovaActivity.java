package mdl.sinlov.android.cordovabridge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.LinearLayout;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

import mdl.sinlov.android.cordovabridge.utils.NetworkState;


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
public abstract class MDLCordovaActivity extends Activity {

    private static final String ERROR_URL = "file:///android_asset/404.html";
    public static final String TAG = "MDL:Cordova:Activity";
    protected SystemWebView webView;
    protected LinearLayout llMDLCordova;
    protected CordovaWebView cordovaWebView;
    protected CordovaInterfaceImpl cordovaInterface;
    private LoadingDialogFragment loading;

    protected void loadUrl(String url) {
        if (cordovaWebView != null) {
            Log.d(TAG, "loadUrl: "  + url);
            cordovaWebView.loadUrl(url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdl_cordova);
        llMDLCordova = (LinearLayout) findViewById(R.id.mdl_cd_cordova);
        webView = (SystemWebView) findViewById(R.id.mdl_cd_web_view);
        init();
        loadUrl(setIndex());
    }

    public abstract String setIndex();

    private void init() {
        loading = LoadingDialogFragment.newInstance(false, getString(R.string.loading));
        loading.setCancelable(false);
        initWebView();
    }

    private void initWebView() {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        cordovaInterface = new CordovaInterfaceImpl(this) {
            @Override
            public Object onMessage(String id, Object data) {
                Log.d(TAG, "onMessage: id#" + id);
                if ("onPageStarted".equals(id)) {
                    showRequestLoading();
                    return true;
                }
                if ("onPageFinished".equals(id)) {
                    hideRequestLoading();
                    return true;
                }
                if ("onReceivedError".equals(id)) {
                    cordovaWebView.loadUrl(ERROR_URL);
                    return true;
                }
                return super.onMessage(id, data);
            }
        };
        if (NetworkState.isNetworkAvailable(this)) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(webView));
        if (!cordovaWebView.isInitialized()) {
            cordovaWebView.init(cordovaInterface, parser.getPluginEntries(), parser.getPreferences());
        }
    }

    private void hideRequestLoading() {
        synchronized (loading) {
            if (loading.isAdded()) {
                loading.dismiss();
            }
        }
    }

    private void showRequestLoading() {
        synchronized (loading) {
            if (!loading.isAdded()) {
                loading.show(getFragmentManager(), TAG);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (cordovaWebView != null) {
            cordovaWebView.handleResume(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cordovaWebView != null) {
            cordovaWebView.handlePause(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (llMDLCordova != null && webView != null) {
            llMDLCordova.removeView(webView);
            webView.removeAllViews();
        }
        if (cordovaWebView != null) {
            cordovaWebView.handleDestroy();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (cordovaWebView != null) {
            cordovaWebView.handleStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cordovaWebView != null) {
            cordovaWebView.handleStop();
        }
    }
}
