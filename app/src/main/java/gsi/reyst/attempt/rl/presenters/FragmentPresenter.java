package gsi.reyst.attempt.rl.presenters;

import gsi.reyst.attempt.rl.views.FragmentView;

public interface FragmentPresenter {

    void init(FragmentView view);

    void onStop();
}
