package supervision.valentin.durand.net.projetsupervision;

import java.io.Serializable;

public class Lecture implements Serializable{
    public String Date;
    public String Value;
    public String Bay;
    private static final long serialVersionUID = 12L;

    public Lecture(String Date,String Value,String Bay) {
        this.Date = Date;
        this.Value = Value;
        this.Bay = Bay;
    }
    @Override
    public String toString() {
        return (this.Date+ "\t : "+this.Value+ "\t : "+this.Bay);
    }
}