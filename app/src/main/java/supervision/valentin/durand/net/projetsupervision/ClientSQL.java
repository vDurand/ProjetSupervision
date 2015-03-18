package supervision.valentin.durand.net.projetsupervision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.util.Log;

public class ClientSQL {

	private static final String TAG = "ClientSQL";
	private String serveurBDD;
	private String nomBDD;
	private String userBDD;
	private String mdpBDD;
	private String portBDD;
	private String connexionStringBDD;
	private Connection conn = null;
	
	public ClientSQL(String serveur, String port, String bdd, String user, String mdp, int timeout) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		this.setServeurBDD(serveur);
		this.setNomBDD(bdd);
		this.setUserBDD(user);
		this.setMdpBDD(mdp);
		String to = String.valueOf(timeout);
		setConnexionStringBDD("jdbc:jtds:sqlserver://"+getServeurBDD().toString()+":"+port.toString()+"/"+bdd+";encrypt=false;instance=SQLEXPRESS;loginTimeout="+to+";socketTimeout="+to+";");
		// Chargement du drivers
		Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
        System.out.println("con init");
		DriverManager.setLoginTimeout(timeout);
	}

	public ResultSet getTableUsageDD() throws SQLException
	{
		if( conn == null )
			conn = DriverManager.getConnection(this.connexionStringBDD,this.userBDD, this.mdpBDD);
		Log.i(TAG,"open BDD");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select TOP 20 * from UsageDD");
		return result;
	}

    public ResultSet getTableUsageMP() throws SQLException
    {
        if( conn == null )
            conn = DriverManager.getConnection(this.connexionStringBDD,this.userBDD, this.mdpBDD);
        Log.i(TAG,"open BDD");
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("select TOP 20 * from UsageMP");
        return result;
    }

    public ResultSet getTableTEMP() throws SQLException
    {
        if( conn == null )
            conn = DriverManager.getConnection(this.connexionStringBDD,this.userBDD, this.mdpBDD);
        Log.i(TAG,"open BDD");
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("select TOP 20 * from Temperatures");
        return result;
    }

    public void finalize()
    {
      if(conn != null)
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
      
	public String getServeurBDD() {
		return serveurBDD;
	}

	public void setServeurBDD(String serveurBDD) {
		this.serveurBDD = serveurBDD;
	}

	public String getNomBDD() {
		return nomBDD;
	}

	public void setNomBDD(String nomBDD) {
		this.nomBDD = nomBDD;
	}

	public String getMdpBDD() {
		return mdpBDD;
	}

	public void setMdpBDD(String mdpBDD) {
		this.mdpBDD = mdpBDD;
	}

	public String getPortBDD() {
		return portBDD;
	}

	public void setPortBDD(String portBDD) {
		this.portBDD = portBDD;
	}

	@SuppressWarnings("unused")
	private String getConnexionStringBDD() {
		return connexionStringBDD;
	}

	private void setConnexionStringBDD(String connexionStringBDD) {
		this.connexionStringBDD = connexionStringBDD;
	}

	public String getUserBDD() {
		return userBDD;
	}

	public void setUserBDD(String userBDD) {
		this.userBDD = userBDD;
	}
	
	
}
