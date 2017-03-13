package mdl.sinlov.android.demo.cordova;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.cordova.demo.plugin.BBSTestActivity;
import mdl.sinlov.android.cordova.demo.plugin.H5GameTestActivity;
import mdl.sinlov.android.cordova.demo.plugin.WeiBoCordovaActivity;

public class MainActivity extends MDLTestActivity {

    @BindView(R.id.btn_main_skip_weibo)
    Button btnMainSkipWeibo;
    @BindView(R.id.activity_main)
    ScrollView activityMain;
    @BindView(R.id.btn_main_skip_h5_game)
    Button btnMainSkipH5Game;
    @BindView(R.id.btn_main_skip_bbs)
    Button btnMainSkipBbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindView(Bundle savedInstanceState) {

    }

    @Override
    protected void bindListener() {

    }

    @OnClick({R.id.btn_main_skip_weibo, R.id.btn_main_skip_h5_game, R.id.btn_main_skip_bbs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_skip_weibo:
                skip2Activity(WeiBoCordovaActivity.class);
                break;
            case R.id.btn_main_skip_h5_game:
                skip2Activity(H5GameTestActivity.class);
                break;
            case R.id.btn_main_skip_bbs:
                skip2Activity(BBSTestActivity.class);
                break;
        }
    }
}
