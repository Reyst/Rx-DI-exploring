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
import gsi.reyst.attempt.rl.R;
import gsi.reyst.attempt.rl.presenters.FragmentPresenter;
import gsi.reyst.attempt.rl.utils.Util;

public class Fragment2 extends BaseFragment {

    @Inject
    FragmentPresenter mPresenter;

    @BindView(R.id.tv_fragment_location)
    TextView mTextViewResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        ButterKnife.bind(this, view);

        setFragmentName("Frag2");
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
        mTextViewResult.setText(Util.prepareString(strLat, strLon));
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
