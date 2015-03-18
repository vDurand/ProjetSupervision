package supervision.valentin.durand.net.projetsupervision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.snmp2.SnmpOID;

import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {
    private String HDDusage = "0";
    private String CPUusage = "0";
    private String TEMPusage = "0";
    private TextView txtHDD;
    private TextView txtCPU;
    private TextView txtTEMP;
    static final private int MENU_PREFERENCES = Menu.FIRST;
    static final private int CODE_REQUETE_PREFERENCES = 1;

    private String ip = "82.233.223.249";
    private String port = "1433";
    private String bdd = "Supervision";
    private String username = "supervision";
    private String password = "Password1234";
    private ClientSQL clientBDD;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHDD = (TextView)findViewById(R.id.DiskUsageTxt);
        txtCPU = (TextView)findViewById(R.id.CpuUsageTxt);
        txtTEMP = (TextView)findViewById(R.id.TempUsageTxt);

        try {
            clientBDD = new ClientSQL(ip, port, bdd, username, password, 5);
        }
        catch (SQLException e) {
            System.err.println("Caught SQLException: " + e.getMessage());
        }
        catch (InstantiationException e) {
            System.err.println("Caught InstantiationException: " + e.getMessage());
        }
        catch (IllegalAccessException e) {
            System.err.println("Caught IllegalAccessException: " + e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.err.println("Caught ClassNotFoundException: " + e.getMessage());
        }


        txtHDD.setText(HDDusage);
        txtCPU.setText(CPUusage);
        txtTEMP.setText(TEMPusage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_PREFERENCES, Menu.NONE, R.string.menu_preferences);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case (MENU_PREFERENCES): {
                Class c = SetPreferencesFragmentActivity.class;
                Intent i = new Intent(this, c);
                startActivityForResult(i, CODE_REQUETE_PREFERENCES);
                return true; }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUETE_PREFERENCES) this.updateAttributsFromPreferences();
    }

    public void updateAttributsFromPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = prefs.getString( PreferencesFragments.PREFKEY_IPSERVEUR_SQL, "82.233.223.249");
        port = prefs.getString( PreferencesFragments.PREFKEY_PORTSERVEUR_SQL, "1433");
        username = prefs.getString( PreferencesFragments.PREFKEY_USERNAME_SQL, "supervision");
        password = prefs.getString( PreferencesFragments.PREFKEY_PASSWORD_SQL, "Password1234");
    }

    public void onClickBtnDiskUsage(View v){
        new Thread(new Runnable() {
            public void run() {
                try{
                    MainActivity.this.result = "Capacité : ";
                    SnmpTarget target = new SnmpTarget();
                    target.setTargetHost("82.233.223.249");
                    target.setTargetPort(161);
                    target.setCommunity("DataCenterVDR");
                    SnmpOID oidCap = new SnmpOID(".1.3.6.1.2.1.25.2.3.1.5.1");
                    SnmpOID oidUti = new SnmpOID(".1.3.6.1.2.1.25.2.3.1.6.1");
                    target.setSnmpOID(oidCap);
                    MainActivity.this.result += target.snmpGet();
                    target.setSnmpOID(oidUti);
                    MainActivity.this.result += " B et Utilisation : "+target.snmpGet()+" B";
                }
                catch(Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
                runOnUiThread(new Runnable(){
                    public void run(){
                        MainActivity.this.txtHDD.setText(MainActivity.this.result);
                    }
                });
            }
        }).start();
    }

    public void onClickBtnCpuUsage(View v){
        new Thread(new Runnable() {
            public void run() {
                try{
                    MainActivity.this.result = "Utilisation CPU en %";
                    for(int i = 2; i<10; i++){
                        SnmpTarget target = new SnmpTarget();
                        target.setTargetHost("82.233.223.249");
                        target.setTargetPort(161);
                        target.setCommunity("DataCenterVDR");
                        SnmpOID oid = new SnmpOID(".1.3.6.1.2.1.25.3.3.1.2."+i);
                        target.setSnmpOID(oid);
                        MainActivity.this.result += " : "+target.snmpGet();
                    }

                }
                catch(Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
                runOnUiThread(new Runnable(){
                    public void run(){
                        MainActivity.this.txtCPU.setText(MainActivity.this.result);
                    }
                });
            }
        }).start();
    }

    public void onClickBtnTempUsage(View v){
        new Thread(new Runnable() {
            public void run() {
                try{
                    MainActivity.this.result = "Temp en °C : ";
                    SnmpTarget target = new SnmpTarget();
                    target.setTargetHost("82.233.223.249");
                    target.setTargetPort(1610);
                    target.setCommunity("DataCenterVDR");
                    SnmpOID oid = new SnmpOID(".1.3.6.1.4.1.21796.4.1.3.1.4.1");
                    target.setSnmpOID(oid);
                    MainActivity.this.result += target.snmpGet();

                }
                catch(Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
                runOnUiThread(new Runnable(){
                    public void run(){
                        MainActivity.this.txtTEMP.setText(MainActivity.this.result);
                    }
                });
            }
        }).start();
    }

    public void onClickBtnHddStat(View v){

    }

    public void onClickBtnCpuStat(View v){

    }

    public void onClickBtnTempStat(View v){

    }
}
