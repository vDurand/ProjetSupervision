package supervision.valentin.durand.net.projetsupervision;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickBtnDiskUsage(View v){
        new Thread(new Runnable() {
            public void run() {
                try{
                    SnmpTarget target = new SnmpTarget();
                    target.setTargetHost("82.233.223.249");
                    target.setTargetPort(161);
                    target.setCommunity("DataCenterVDR");
                    SnmpOID oid = new SnmpOID("1.3.6.1.2.1.25.2.3.1.6.1");
                    target.setSnmpOID(oid);
                    MainActivity.this.result = target.snmpGet();
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
                    SnmpTarget target = new SnmpTarget();
                    target.setTargetHost("82.233.223.249");
                    target.setTargetPort(161);
                    target.setCommunity("DataCenterVDR");
                    target.setObjectID("1.3.6.1.2.1.25.3.3.1.2.(k+1)");
                    MainActivity.this.result = target.snmpGet();
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
        txtTEMP.setText("15");
    }

    public void onClickBtnHddStat(View v){

    }

    public void onClickBtnCpuStat(View v){

    }

    public void onClickBtnTempStat(View v){

    }
}
