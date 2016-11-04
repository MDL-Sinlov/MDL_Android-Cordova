package mdl.sinlov.android.cordovabridge;

import android.app.DialogFragment;
import android.os.Bundle;

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
public class LoadingDialogFragment extends DialogFragment {

    public static LoadingDialogFragment newInstance(boolean b, String title) {
        LoadingDialogFragment instance = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("alert-title", title);
        instance.setArguments(bundle);
        instance.setHasOptionsMenu(b);
        return instance;
    }

    public static LoadingDialogFragment newInstance(boolean b, String title, String message) {
        LoadingDialogFragment instance = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("alert-title", title);
        bundle.putString("alert-message", message);
        instance.setArguments(bundle);
        instance.setHasOptionsMenu(b);
        return instance;
    }
}
