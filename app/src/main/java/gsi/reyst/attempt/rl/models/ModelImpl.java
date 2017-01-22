package gsi.reyst.attempt.rl.models;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.util.Pair;

import java.util.Locale;

import javax.inject.Inject;

import gsi.reyst.attempt.rl.utils.DataGenerator;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;


public class ModelImpl implements Model {

    private static final String TAG = "LOG_ModelImpl";

    private LocationManager mLocationManager;

    private Observable<Pair<String, String>> mObservable;

    private DataGenerator mGPS;
    private DataGenerator mNET;

    @SuppressWarnings("MissingPermission")
    public ModelImpl(LocationManager locationManager) {

        Log.d(TAG, "Constructor");

        mLocationManager = locationManager;

        PublishSubject<Location> mLatestLocation = PublishSubject.create();

        mGPS = new DataGenerator(mLatestLocation);
        mNET = new DataGenerator(mLatestLocation);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000, 10f, mGPS);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5_000, 5f, mNET);

        mObservable = mLatestLocation
                .doOnSubscribe(consumer -> Log.d(TAG, consumer.getClass().getSimpleName()))
                .map(location -> {
                    Log.d(TAG, "map");
                    return new Pair<>(
                    String.format(Locale.getDefault(), "%.4f", location.getLatitude()),
                    String.format(Locale.getDefault(), "%.4f", location.getLongitude()));
                })
                //.publish()
                .replay(1)
                .autoConnect()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<Pair<String, String>> getRxLocation() {
        return mObservable;
    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void destroy() {
        mLocationManager.removeUpdates(mGPS);
        mLocationManager.removeUpdates(mNET);
    }
}
