package supervision.valentin.durand.net.projetsupervision;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by kepha_000 on 18/03/2015.
 */
public class PreferencesFragments extends PreferenceFragment {
    // Ctes string, clés de sauvegarde des préférences
    public static final String PREFKEY_IPSERVEUR_SQL ="PREFKEY_IPSERVEUR_SQL";
    public static final String PREFKEY_PORTSERVEUR_SQL="PREFKEY_PORTSERVEUR_SQL";
    public static final String PREFKEY_USERNAME_SQL ="PREFKEY_USERNAME_SQL";
    public static final String PREFKEY_PASSWORD_SQL ="PREFKEY_PASSWORD_SQL";
    public static final String PREFKEY_IPSERVEUR_SNMP ="PREFKEY_IPSERVEUR_SNMP";
    public static final String PREFKEY_PORTSERVEUR_SNMP ="PREFKEY_PORTSERVEUR_SNMP";
    public static final String PREFKEY_COMMUNITY_SNMP ="PREFKEY_COMMUNITY_SNMP";
    public static final String PREFKEY_IPSERVEUR_SONDE ="PREFKEY_IPSERVEUR_SONDE";
    public static final String PREFKEY_PORTSERVEUR_SONDE ="PREFKEY_PORTSERVEUR_SONDE";
    public static final String PREFKEY_COMMUNITY_SONDE ="PREFKEY_COMMUNITY_SONDE";

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        addPreferencesFromResource(R.xml.userpreferences);
    }
}
