package gsi.reyst.attempt.rl.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.subjects.Subject;

public class DataGenerator implements LocationListener {

    private static final String TAG = "LOG_";

    private Subject<Location> mLatestLocation;

    public DataGenerator(Subject<Location> subject) {
        mLatestLocation = subject;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG + location.getProvider(), "onLocationChanged: " + location.toString());
        mLatestLocation.onNext(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG + provider, "onStatusChanged: " + extras.toString());

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG + provider, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG + provider, "onProviderDisabled");
    }

}
