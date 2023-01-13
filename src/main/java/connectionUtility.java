import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionUtility {

	
	public static Connection getConnectionFromFile(String filename) throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("name");
		String password = prop.getProperty("pass");
	
		
		Connection con = DriverManager.getConnection(url, username, password);
		
		
		return con;
	}
}
