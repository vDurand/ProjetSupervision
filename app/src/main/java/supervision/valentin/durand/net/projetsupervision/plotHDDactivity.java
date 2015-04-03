package supervision.valentin.durand.net.projetsupervision;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;
import java.util.Arrays;
import java.util.ArrayList;

public class plotHDDactivity extends ActionBarActivity {

    private static final String LISTE_DD_KEY = "";
    private XYPlot plot;
    public ArrayList<Lecture> arrayF;
    private Float[] abscisse;
    private Float[] ordonne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        arrayF = (ArrayList<Lecture>) intent.getSerializableExtra("stockage");

        setContentView(R.layout.hdd_graph);

        ordonne = new Float[arrayF.size()];
        for (int i = 0; i < arrayF.size(); i++) {
            ordonne[i] = Float.parseFloat(arrayF.get(i).mesure)/1000;
        }

        abscisse = new Float[ordonne.length];
        int step = 100 / ordonne.length;
        for (int i = 0; i < ordonne.length; i++) {
            if (i > 0) {
                abscisse[i] = step + abscisse[i - 1].floatValue();
            } else {
                Float fX = new Float(step);
                abscisse[i] = fX.floatValue();
            }
        }

        plot = (XYPlot) findViewById(R.id.hdd_graph);
        plot.setRangeLabel("%");
        plot.setDomainLabel("Temps relatif");
        plot.setRangeBoundaries(20000000, 30000000, BoundaryMode.FIXED);
        plot.getBorderPaint().setColor(Color.BLACK);
        plot.getBackgroundPaint().setColor(Color.BLACK);
        plot.setDrawingCacheBackgroundColor(Color.BLACK);
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

        XYSeries graphTemp = new SimpleXYSeries(Arrays.asList(abscisse), Arrays.asList(ordonne), "Occupation du disque dur");
        plot.addSeries(graphTemp, new LineAndPointFormatter(Color.rgb(255, 0, 0), Color.rgb(0, 0, 0), Color.argb(20, 255, 0, 0), new PointLabelFormatter(Color.BLACK)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}
