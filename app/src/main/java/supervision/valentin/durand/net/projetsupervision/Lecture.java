package supervision.valentin.durand.net.projetsupervision;

import java.io.Serializable;

public class Lecture implements Serializable{
    public String Date;
    public String Value;
    public String Bay;
    public int what;
    public String mesure;
    private static final long serialVersionUID = 12L;

    public Lecture(String Date,String Value,String Bay, int what, String mesure) {
        this.Date = Date;
        this.Value = Value;
        this.Bay = Bay;
        this.what = what;
        this.mesure = mesure;
    }
    @Override
    public String toString() {
        return (this.Date+ "\t : "+this.Value+ "\t : "+this.Bay);
    }
}