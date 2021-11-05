package driver.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Collections;
import java.util.Properties;

public class DBHelper {
    static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    public static Connection connect() {
        Properties prop = new Properties();
        String propFileName = "application.properties";
        try(InputStream inputStream= UserDAO.class.getClassLoader().getResourceAsStream(propFileName)) {

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Class.forName("oracle.jdbc.OracleDriver");

            String user = prop.getProperty("user");
            String passwd = prop.getProperty("pwd");

            String sql="select * from temp";

            return DriverManager.getConnection(jdbcURL, user, passwd);
        }
        catch(Throwable oops) {
            oops.printStackTrace();
            return null;
        }
    }

    public static void executeUpdate(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

}
