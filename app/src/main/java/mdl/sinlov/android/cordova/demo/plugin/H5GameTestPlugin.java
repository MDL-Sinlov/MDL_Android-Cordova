package mdl.sinlov.android.cordova.demo.plugin;

import org.apache.cordova.CordovaPlugin;

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
 * Created by sinlov on 16/11/4.
 */
public class H5GameTestPlugin extends CordovaPlugin {

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
}
