package mdl.sinlov.android.demo.cordova;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.cordova.demo.plugin.WeiBoCordovaActivity;

public class MainActivity extends MDLTestActivity {

    @BindView(R.id.btn_main_skip_weibo)
    Button btnMainSkipWeibo;
    @BindView(R.id.activity_main)
    ScrollView activityMain;

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

    @OnClick(R.id.btn_main_skip_weibo)
    public void onClick() {
        skip2Activity(WeiBoCordovaActivity.class);
    }
}
