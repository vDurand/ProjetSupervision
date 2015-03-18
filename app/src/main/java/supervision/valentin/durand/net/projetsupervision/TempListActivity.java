package supervision.valentin.durand.net.projetsupervision;

        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.speech.tts.TextToSpeech.OnInitListener;
        import android.widget.ListView;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;

public class TempListActivity extends ActionBarActivity implements OnInitListener {
    public ListView listeView;
    public ArrayList<Lecture> arrayF;
    public ArrayTempAdaptateur arrayTempAdapt;
    static final private String TABLE_F_KEY = "Table_F";
    private ClientSQL clientBDD;
    private String ip = "82.233.223.249";
    private String port = "1433";
    private String bdd = "Supervision";
    private String username = "supervision";
    private String password = "Password1234";
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
System.out.println(message);
        setContentView(R.layout.temp_stat);

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


        this.listeView = (ListView)findViewById(R.id.listTemp);
        int layoutID = R.layout.item_perso;

        if(savedInstanceState!=null){
            this.arrayF = (ArrayList<Lecture>) savedInstanceState.getSerializable(TempListActivity.TABLE_F_KEY);
        }
        else{
            this.arrayF = new ArrayList<Lecture>();
            if(message.equals("3"))
            LoadTemp();
            if(message.equals("1"))
            LoadHdd();
        }
        this.arrayTempAdapt = new ArrayTempAdaptateur(this,layoutID,arrayF);
        this.listeView.setAdapter(this.arrayTempAdapt);
    }

    public void LoadTemp(){
        if(!this.arrayF.isEmpty()){
            this.arrayF.clear();
        }
        new Thread(new Runnable() {
            public void run() {
                try{
                    ResultSet resultat = clientBDD.getTableTEMP();
                    while(resultat.next()){
                        String date = "date: "+resultat.getString("date");
                        String value = resultat.getString("temp")+" °C";
                        String bay = "machine: "+resultat.getString("MachineName");
                        TempListActivity.this.arrayF.add(new Lecture(date, value, bay));
                    }
                }
                catch(SQLException e) {
                    System.err.println("Exception: " + e.getMessage());
                }
                runOnUiThread(new Runnable(){
                    public void run(){
                        TempListActivity.this.arrayTempAdapt.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void LoadHdd(){
        if(!this.arrayF.isEmpty()){
            this.arrayF.clear();
        }
        new Thread(new Runnable() {
            public void run() {
                try{
                    ResultSet resultat = clientBDD.getTableUsageDD();
                    while(resultat.next()){
                        String date = "date: "+resultat.getString("date");
                        System.out.println(date);
                        String value = "mesure";
                        for(int i = 1; i<10; i++){
                            value += " : "+resultat.getString("usageMP"+i);
                        }
                        String bay = "nb proc: "+resultat.getString("nbProcs");
                        TempListActivity.this.arrayF.add(new Lecture(date, value, bay));
                    }
                }
                catch(SQLException e) {
                    System.err.println("Exception: " + e.getMessage());
                }
                runOnUiThread(new Runnable(){
                    public void run(){
                        TempListActivity.this.arrayTempAdapt.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onInit(int status) {

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TempListActivity.TABLE_F_KEY, this.arrayF);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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
