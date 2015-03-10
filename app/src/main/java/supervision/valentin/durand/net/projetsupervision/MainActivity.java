package supervision.valentin.durand.net.projetsupervision;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private String HDDusage = "0";
    private String CPUusage = "0";
    private String TEMPusage = "0";
    private TextView txtHDD;
    private TextView txtCPU;
    private TextView txtTEMP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHDD = (TextView)findViewById(R.id.DiskUsageTxt);
        txtCPU = (TextView)findViewById(R.id.CpuUsageTxt);
        txtTEMP = (TextView)findViewById(R.id.TempUsageTxt);

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
        txtHDD.setText("100");
    }

    public void onClickBtnCpuUsage(View v){
        txtCPU.setText("20");
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
