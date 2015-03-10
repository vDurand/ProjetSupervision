package supervision.valentin.durand.net.projetsupervision;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private String HDDusage = "0";
    private String CPUusage = "0";
    private String TEMPusage = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtHDD =(TextView)findViewById(R.id.DiskUsageTxt);
        txtHDD.setText(HDDusage);
        TextView txtCPU =(TextView)findViewById(R.id.CpuUsageTxt);
        txtCPU.setText(CPUusage);
        TextView txtTEMP =(TextView)findViewById(R.id.TempUsageTxt);
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
}
