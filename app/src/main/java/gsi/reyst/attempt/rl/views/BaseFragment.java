package gsi.reyst.attempt.rl.views;

import android.app.Fragment;

public abstract class BaseFragment extends Fragment implements FragmentView {

    private String mFragmentName;

    public void setFragmentName(String name) {
        this.mFragmentName = name;
    }

    @Override
    public String getName() {
        return mFragmentName;
    }
}
