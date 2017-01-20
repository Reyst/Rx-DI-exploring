package gsi.reyst.attempt.rl.DI.components;

import dagger.Subcomponent;
import gsi.reyst.attempt.rl.DI.modules.FragmentModule;
import gsi.reyst.attempt.rl.views.Fragment1;
import gsi.reyst.attempt.rl.views.Fragment2;

@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void injectTo(Fragment1 fragment);

    void injectTo(Fragment2 fragment);

}
