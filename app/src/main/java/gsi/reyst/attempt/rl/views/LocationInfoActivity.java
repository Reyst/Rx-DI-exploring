package gsi.reyst.attempt.rl.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gsi.reyst.attempt.rl.DI.ComponentHolder;
import gsi.reyst.attempt.rl.R;
import gsi.reyst.attempt.rl.presenters.ActivityPresenter;
import gsi.reyst.attempt.rl.presenters.FragmentPresenter;
import gsi.reyst.attempt.rl.utils.Util;

public class LocationInfoActivity extends AppCompatActivity implements ActivityView {

    @Inject
    ActivityPresenter mPresenter;

    @BindView(R.id.tv_activity_location)
    TextView mTextViewResult;

    @BindView(R.id.btn_next)
    Button mBtnNext;

    @BindView(R.id.btn_test_replay)
    Button mBtnTestReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initApp();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, Util.PERMISSION_REQUEST_CODE);
        }

    }

    private void initApp() {
        ComponentHolder.getInstance().getAppComponent().injectTo(this);
        mPresenter.onCreate(this);
        mPresenter.changeFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean granted = false;

        switch (requestCode) {
            case Util.PERMISSION_REQUEST_CODE: {

                for (int grantResult : grantResults) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        granted = true;
                        break;
                    }
                }

                if (granted) {
                    initApp();
                } else {
                    Toast.makeText(this, "Permission denied to ACCESS_LOCATION!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    public void showData(String strLat, String strLon) {
        mTextViewResult.setText(Util.prepareString(strLat, strLon));
    }

    @OnClick(R.id.btn_next)
    void onNextClick() {
        mPresenter.changeFragment();
    }

    @OnClick(R.id.btn_test_replay)
    void onTextReplayClick() {
        mPresenter.addSubscriber();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
