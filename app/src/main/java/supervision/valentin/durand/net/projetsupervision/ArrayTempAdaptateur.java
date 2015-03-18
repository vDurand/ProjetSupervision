package supervision.valentin.durand.net.projetsupervision;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayTempAdaptateur extends ArrayAdapter<Lecture> {

    private ArrayList<Lecture> objets;
    private int item_id;

    public ArrayTempAdaptateur(Context context, int textViewResourceId, ArrayList<Lecture> objects)
    {
        super(context, textViewResourceId, objects);
        this.objets = objects;
        this.item_id = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Vue à mettre à jour
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(this.item_id, null);
        }
        Lecture fcourant = objets.get(position);
        if (fcourant != null) {
            TextView tv_nf = (TextView) v.findViewById(R.id.nf);
            TextView tv_nomF = (TextView) v.findViewById(R.id.nomF);
            TextView tv_statut = (TextView) v.findViewById(R.id.statut);
            TextView tv_ville = (TextView) v.findViewById(R.id.ville);
            ImageView icone = (ImageView) v.findViewById(R.id.imageDescription);
            if (tv_nf != null) tv_nf.setText(Integer.toString(position+1));
            if (tv_nomF != null) tv_nomF.setText(fcourant.Value);
            if (tv_statut != null) tv_statut.setText(fcourant.Date+" ");
            if (tv_ville != null) tv_ville.setText(" "+fcourant.Bay);
            if (icone != null) icone.setImageResource(R.drawable.imagetempsmll);
        }
        return v;
    }
}