package SQLClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Statement statement;
    private boolean isLoggedIn = false;
    
    public void login(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        
        statement  = connection.createStatement();
        isLoggedIn = true;
    }
    
    public ResultSet executeQuery(String query) throws SQLException { 
        return statement.executeQuery(query);
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
