package gsi.reyst.attempt.rl.presenters;

import android.util.Log;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import gsi.reyst.attempt.rl.DI.ComponentHolder;
import gsi.reyst.attempt.rl.R;
import gsi.reyst.attempt.rl.models.Model;
import gsi.reyst.attempt.rl.views.ActivityView;
import gsi.reyst.attempt.rl.views.BaseFragment;
import gsi.reyst.attempt.rl.views.Fragment1;
import gsi.reyst.attempt.rl.views.Fragment2;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ActivityPresenterImpl implements ActivityPresenter {

    private static final String TAG = "LOG_AP";
    private BaseFragment[] mFragments;

    private int mCurrentIndex = -1;

    private ActivityView mView;

    private CompositeDisposable mDisp;

    private Observable<Long> mTestReplayObservable;


    @Inject
    Model mModel;

    private Disposable mDisposable;

    @Override
    public void onCreate(ActivityView view) {
        mView = view;

        mFragments = new BaseFragment[2];
        mFragments[0] = new Fragment1();
        mFragments[1] = new Fragment2();

        ComponentHolder.getInstance().getAppComponent().injectTo(this);

        mTestReplayObservable = Observable
                .interval(1000, TimeUnit.MILLISECONDS)
                .replay(2)
                .autoConnect();

        mDisp = new CompositeDisposable();

        mModel.attachListeners();
        //noinspection StringBufferReplaceableByString
        mDisposable = mModel.getRxLocation()
                .doOnNext(data -> Log.d(TAG, new StringBuilder().append(data.first).append(", ").append(data.second).toString()))
                .subscribe(data -> mView.showData(data.first, data.second));
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mModel.removeListeners();
        }
    }

    @Override
    public void changeFragment() {
        mCurrentIndex++;
        if (mCurrentIndex >= mFragments.length) mCurrentIndex = 0;

        mView.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mFragments[mCurrentIndex])
                .commit();
    }

    @Override
    public void onStop() {
        mDisp.clear();
    }

    @Override
    public void addSubscriber() {
        mDisp.add(mTestReplayObservable
                .map(data -> String.format(Locale.getDefault(), "S #%d - value = %d", mDisp.size() + 1, data))
                .subscribe(
                        data -> Log.d(TAG, data),
                        th -> Log.e(TAG, th.getLocalizedMessage()),
                        () -> Log.d(TAG, "onCompleted"),
                        subs -> Log.d(TAG, "onSubscribe")));
    }
}
