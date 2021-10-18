import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Sample {

    static final String jdbcURL
            = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    public static void main(String[] args) {
        Properties prop = new Properties();
        String propFileName = "application.properties";
        try(InputStream inputStream= Sample.class.getClassLoader().getResourceAsStream(propFileName)) {

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Class.forName("oracle.jdbc.OracleDriver");

            String user = prop.getProperty("user");
            String passwd = prop.getProperty("pwd");

            String sql="select * from temp";

            try (Connection conn =DriverManager.getConnection(jdbcURL, user, passwd);
                 Statement stmt = conn.createStatement();
                 ResultSet rs=stmt.executeQuery(sql)){

                while (rs.next()) {
                    String name = rs.getString("name");
                    System.out.println(name);
                }

            }

            catch(SQLException e) {
                System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            }

        }
        catch(Throwable oops) {
            oops.printStackTrace();
        }

    }

}



