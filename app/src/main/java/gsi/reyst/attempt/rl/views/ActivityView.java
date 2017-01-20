package gsi.reyst.attempt.rl.views;

import android.app.FragmentManager;

public interface ActivityView {

    void showData(String strLat, String strLon);

    FragmentManager getFragmentManager();

}
