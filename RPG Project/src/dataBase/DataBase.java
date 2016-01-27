package dataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.interface_Graphique_Créateur.PanelPrincipal.Level;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DataBase {

	private MysqlDataSource datas;
	private Connection connection;
	private static DataBase instance;
	
	public static DataBase getBDD(){
		if (instance == null)
			instance = new DataBase();
		return instance;
	}
	
	private DataBase(){
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
	
	private PreparedStatement prepareStatement(String req){
		PreparedStatement s = null;
		try {
			s = this.connection.prepareStatement(req);
		} catch (SQLException e) {
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
	
	public void addLevel(Level level){
		try {
			String req = "INSERT INTO Carte(fichier, nom, background) VALUES (?,?,?);";
			PreparedStatement s = this.prepareStatement(req);
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(level.getFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.setBlob(1, inputStream);
	        s.setString(2, level.getName());
	        s.setInt(3, level.getBackground());

	        int row = s.executeUpdate();
	        if (row > 0) {
	            System.out.println("Level bien ajouté");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
