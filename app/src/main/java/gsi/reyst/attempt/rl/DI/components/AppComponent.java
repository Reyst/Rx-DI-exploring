package gsi.reyst.attempt.rl.DI.components;

import javax.inject.Singleton;

import dagger.Component;
import gsi.reyst.attempt.rl.DI.modules.AppModule;
import gsi.reyst.attempt.rl.DI.modules.FragmentModule;
import gsi.reyst.attempt.rl.presenters.ActivityPresenterImpl;
import gsi.reyst.attempt.rl.presenters.FragmentPresenterImpl;
import gsi.reyst.attempt.rl.views.LocationInfoActivity;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void injectTo(LocationInfoActivity activity);

    void injectTo(FragmentPresenterImpl presenter);

    void injectTo(ActivityPresenterImpl presenter);

    FragmentComponent add(FragmentModule module);

}
