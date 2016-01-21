
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			ResultSet result = statement.executeQuery("SELECT * FROM Personnage;");
			while ( result.next()){
			    System.out.println("Le personnage " + result.getString("pseudo") +" est en (" + result.getInt("positionX")+";" + result.getInt("positionY") + ")" );	    	
			}
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
		try {
			ResultSet result = statement.executeQuery("SELECT positionX FROM Personnage WHERE pseudo = " + pseudo + ";");
			return result.getInt("positionX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getPositionY(String pseudo) {
		try {
			ResultSet result = statement.executeQuery("SELECT positionY FROM Personnage WHERE pseudo = " + pseudo + ";");
			return result.getInt("positionY");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
