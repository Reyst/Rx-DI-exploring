package gsi.reyst.attempt.rl.presenters;

import android.util.Log;

import javax.inject.Inject;

import gsi.reyst.attempt.rl.DI.ComponentHolder;
import gsi.reyst.attempt.rl.models.Model;
import gsi.reyst.attempt.rl.views.FragmentView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class FragmentPresenterImpl implements FragmentPresenter {

    private static final String TAG = "LOG_fp";

    private FragmentView mView;

    @Inject
    Model mModel;

    private Disposable mDisposable;

    @Override
    public void init(FragmentView view) {

        ComponentHolder.getInstance().getAppComponent().injectTo(this);

        mView = view;
        //noinspection StringBufferReplaceableByString
        mDisposable = mModel.getRxLocation()
                .doOnNext(data -> Log.d(TAG + "_" + mView.getName(), new StringBuilder().append(data.first).append(", ").append(data.second).toString()))
                .subscribe(data -> mView.showData(data.first, data.second));
    }

    @Override
    public void onStop() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            //mModel.destroy();
        }
    }

}
