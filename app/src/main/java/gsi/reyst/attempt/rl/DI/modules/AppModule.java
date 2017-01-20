package gsi.reyst.attempt.rl.DI.modules;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gsi.reyst.attempt.rl.models.Model;
import gsi.reyst.attempt.rl.models.ModelImpl;
import gsi.reyst.attempt.rl.presenters.ActivityPresenter;
import gsi.reyst.attempt.rl.presenters.ActivityPresenterImpl;

@SuppressWarnings("WeakerAccess")
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public LocationManager provideLocationManager() {
        return (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    public Model provideModel(LocationManager manager) {
        return new ModelImpl(manager);
    }

    @Provides
    @Singleton
    public ActivityPresenter provideActivityPresenter() {
        return new ActivityPresenterImpl();
    }

}
