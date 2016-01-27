
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class BDD {

	private MysqlDataSource datas;
	private Connection connection;
	private static BDD instance;
	
	public static BDD getBDD(){
		if (instance == null)
			instance = new BDD();
		return instance;
	}
	
	private BDD(){
		this.datas = new MysqlDataSource();
		this.datas.setUser("sql4103891");
		this.datas.setPassword("wJi4nQxM7J");
		this.datas.setServerName("sql4.freemysqlhosting.net");
		this.datas.setDatabaseName("sql4103891");
		try {
			this.connection = this.datas.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Statement createStatement(){
		Statement s = null;
		try {
			s = this.connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public int getPositionX(String pseudo) {
		int positionX = 0;
		try {
			ResultSet result = this.createStatement().executeQuery("SELECT * FROM Personnage WHERE pseudo = \"" + pseudo + "\";");
			result.next();
			positionX = result.getInt("positionX");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positionX;
	}
	
	public int getPositionY(String pseudo) {
		int positionY = 0;
		try {
			ResultSet result = this.createStatement().executeQuery("SELECT * FROM Personnage WHERE pseudo = \"" + pseudo + "\";");
			result.next();
			positionY = result.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positionY;
	}
	
	public void mettreAJourPersonnage(Personnage p) {
		try {
			this.createStatement().executeUpdate("UPDATE Personnage SET positionX = " + p.getPositionX() + ", positionY = " + p.getPositionY() + " WHERE pseudo = \"" + p.getPseudo() + "\";");
			System.out.println("Mise à jour de " + p );
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void ajouterPersonnages(Map<String, Personnage> personnages) {
		try {
			ResultSet result = this.createStatement().executeQuery("SELECT * FROM Personnage;");
			while (result.next()){
				personnages.put(result.getString(1), new Personnage(result.getString(1), result.getInt(2), result.getInt(3)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
