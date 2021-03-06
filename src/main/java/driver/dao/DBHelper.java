package driver.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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


    public static int executeUpdate(String query) throws SQLException {
        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
        ) {
            return stmt.executeUpdate(query);
        }
    }

    public static List<Object[]> executeQueryUpdated(String query) throws SQLException {
        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            List<Object[]> resultList = new ArrayList();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            while(rs.next()) {
                Object[] items = new Object[count];
                for(int i=0; i< count; i++) {
                    items[i] = rs.getObject(i+1);
                }
                resultList.add(items);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}