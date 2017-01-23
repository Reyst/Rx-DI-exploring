package gsi.reyst.attempt.rl.models;

import android.util.Pair;

import io.reactivex.Observable;

public interface Model {

    Observable<Pair<String, String>> getRxLocation();

    void removeListeners();

    void attachListeners();

}
