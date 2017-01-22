package gsi.reyst.attempt.rl.DI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import gsi.reyst.attempt.rl.DI.components.AppComponent;
import gsi.reyst.attempt.rl.DI.components.DaggerAppComponent;
import gsi.reyst.attempt.rl.DI.components.FragmentComponent;
import gsi.reyst.attempt.rl.DI.modules.AppModule;
import gsi.reyst.attempt.rl.DI.modules.FragmentModule;

public class ComponentHolder {

    private static ComponentHolder sInstance;

    private AppComponent mAppComponent;

    public static ComponentHolder getInstance() {

        if (sInstance == null) {
            sInstance = new ComponentHolder();
        }

        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public FragmentComponent getFragmentComponent() {
        return mAppComponent.add(new FragmentModule());
    }

    public void init(Context context) {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(context)).build();
    }

}
