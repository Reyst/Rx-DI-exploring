package gsi.reyst.attempt.rl.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gsi.reyst.attempt.rl.DI.ComponentHolder;
import gsi.reyst.attempt.rl.DI.modules.FragmentModule;
import gsi.reyst.attempt.rl.R;
import gsi.reyst.attempt.rl.presenters.FragmentPresenter;

public class Fragment1 extends BaseFragment {

    @Inject
    FragmentPresenter mPresenter;

    @BindView(R.id.tv_lat_value)
    TextView mTextViewLatitude;

    @BindView(R.id.tv_lon_value)
    TextView mTextViewLongitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind(this, view);

        setFragmentName("Frag1");
        ComponentHolder.getInstance().getFragmentComponent().injectTo(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.init(this);
    }

    @Override
    public void showData(String strLat, String strLon) {
        mTextViewLatitude.setText(strLat);
        mTextViewLongitude.setText(strLon);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

}
