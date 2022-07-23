import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.Resultset;

public class DatabaseConnection {

	Connection connection;
	
	public DatabaseConnection() {
		MysqlDataSource source = new MysqlDataSource();
		source.setServerName("Localhost");
		source.setUser("root");
		source.setPassword("");
		source.setDatabaseName("janji_jywa");
		
		try {
			connection = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet executeQuery(String sql) {
		try {
			Statement st = connection.createStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void execute(String sql) {
		try {
			Statement st = connection.createStatement();
			 st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepare(String sql) {
		try {
			return connection.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
