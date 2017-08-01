package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SimpleConnection {

	private static Connection connection = null;
	
	public static Connection GetSimpleDBConnection() {
		if (connection == null) {
			String dbURL = "jdbc:oracle:thin:equipe2/M7gwNk5p@big-data-3.logti.etsmtl.ca:1521:log660";
			
			try {
				connection = DriverManager.getConnection(dbURL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}
