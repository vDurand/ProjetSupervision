package supervision.valentin.durand.net.projetsupervision;

import java.io.Serializable;

public class Lecture implements Serializable{
    public String Date;
    public String Value;
    public String Bay;
    public int what;
    private static final long serialVersionUID = 12L;

    public Lecture(String Date,String Value,String Bay, int what) {
        this.Date = Date;
        this.Value = Value;
        this.Bay = Bay;
        this.what = what;
    }
    @Override
    public String toString() {
        return (this.Date+ "\t : "+this.Value+ "\t : "+this.Bay);
    }
}