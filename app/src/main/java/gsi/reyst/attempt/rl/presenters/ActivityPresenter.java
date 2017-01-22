package gsi.reyst.attempt.rl.presenters;


import gsi.reyst.attempt.rl.views.ActivityView;

public interface ActivityPresenter {

    void onCreate(ActivityView view);

    void onDestroy();

    void changeFragment();

    void onStop();

    void addSubscriber();

}
