package supervision.valentin.durand.net.projetsupervision;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        }
        this.arrayTempAdapt = new ArrayTempAdaptateur(this,layoutID,arrayF);
        this.listeView.setAdapter(this.arrayTempAdapt);
        LoadTemp();
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
                        String date = resultat.getString("date");
                        System.out.println(date);
                        String value = resultat.getString("temp");
                        String bay = resultat.getString("MachineName");
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
