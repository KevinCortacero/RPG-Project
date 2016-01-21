import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try{	
			
		    conn = DriverManager.getConnection("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4103891", "sql4103891", "wJi4nQxM7J");
		    Statement statement = conn.createStatement(); 
		    /*ResultSet result = statement.executeQuery("SELECT * FROM perso;");
		    
		    while ( result.next()){
		    	System.out.println("Le perso " + result.getString("nom") +" a "+result.getInt("vie")+"points de vie" );	    	
		    }*/
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage() +"\n");
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
		    if ( conn != null )
		        try {
		        	conn.close();
		        } catch ( SQLException ignore ) {
		        }
		}
		
		
	}

}
