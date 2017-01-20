package gsi.reyst.attempt.rl.DI.modules;

import dagger.Module;
import dagger.Provides;
import gsi.reyst.attempt.rl.presenters.FragmentPresenter;
import gsi.reyst.attempt.rl.presenters.FragmentPresenterImpl;

@SuppressWarnings("WeakerAccess")
@Module
public class FragmentModule {

    @Provides
    public FragmentPresenter provideFragmentPresenter() {
        return new FragmentPresenterImpl();
    }

}
