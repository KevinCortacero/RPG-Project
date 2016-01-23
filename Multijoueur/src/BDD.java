
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class BDD {

	private MysqlDataSource datas;
	private static Statement statement;
	
	public BDD(){
		this.datas = new MysqlDataSource();
		this.datas.setUser("sql4103891");
		this.datas.setPassword("wJi4nQxM7J");
		this.datas.setServerName("sql4.freemysqlhosting.net");
		this.datas.setDatabaseName("sql4103891");
		try {
			Connection c = this.datas.getConnection();
			BDD.createStatement(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createStatement(Connection c){
		try {
			statement = c.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getPositionX(String pseudo) {
		int positionX = 0;
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM Personnage WHERE pseudo = \"" + pseudo + "\";");
			result.next();
			positionX = result.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positionX;
	}
	
	public static int getPositionY(String pseudo) {
		int positionY = 0;
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM Personnage WHERE pseudo = \"" + pseudo + "\";");
			result.next();
			positionY = result.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positionY;
	}
	
	public static void mettreAJourPersonnage(Personnage p) {
		try {
			statement.executeUpdate("UPDATE Personnage SET positionX = " + p.getPositionX() + ", positionY = " + p.getPositionY() + " WHERE pseudo = \"" + p.getPseudo() + "\";");
			System.out.println("Mise � jour de " + p );
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void ajouterPersonnages(Map<String, Personnage> personnages) {
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM Personnage;");
			while (result.next()){
				personnages.put(result.getString(1), new Personnage(result.getString(1), result.getInt(2), result.getInt(3)));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
