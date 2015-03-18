package supervision.valentin.durand.net.projetsupervision;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by kepha_000 on 18/03/2015.
 */

public class SetPreferencesFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
        .replace(android.R.id.content,
        new PreferencesFragments()).commit();
    }
}
