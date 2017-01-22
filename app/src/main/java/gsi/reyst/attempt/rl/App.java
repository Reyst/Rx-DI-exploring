package gsi.reyst.attempt.rl;

import android.app.Application;

import gsi.reyst.attempt.rl.DI.ComponentHolder;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ComponentHolder holder = ComponentHolder.getInstance();
        holder.init(getApplicationContext());

    }
}
