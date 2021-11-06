package driver.dao;

import driver.object.Brand;
import driver.object.RERule;
import driver.object.RRRule;

import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class RRRuleDAO {

    private static final String INSERT_RR_RULE = "INSERT INTO rr_rule"+"(rr_rule_code, reward, num_points, instances, version, status) VALUES"+"(?,?,?,?,?,?)";
    private static final String UPDATE_RR_RULE = "UPDATE rr_rule SET num_points = ?, instances = ?, version = ?, status = ? WHERE reward = ? AND rr_rule_code = (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code = (select lp.id from Loyalty_program lp where lp.brand_id = (select b.id from brand b where b.id = ?)))";
    private static final String SELECT_RR_RULE_BY_RRC = "SELECT * FROM rr_rule_code WHERE reward = ? AND rr_rule_code = (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code = (select lp.id from Loyalty_program lp where lp.brand_id = (select b.id from brand b where b.id = ?)))";

    public RRRuleDAO(){
    }

   /* protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, user, passwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }*/

    public static void saveData(RRRule rrRule){
        boolean checkInsert = false;

        try(Connection connection = DBHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RR_RULE);) {
            preparedStatement.setString(1,rrRule.getRrRuleCode());
            preparedStatement.setString(2,rrRule.getReward());
            preparedStatement.setInt(3,rrRule.getNumPoints());
            preparedStatement.setInt(4,rrRule.getInstances());
            preparedStatement.setInt(5,rrRule.getVersion());
            preparedStatement.setString(6,rrRule.getStatus());
            checkInsert = preparedStatement.executeUpdate() > 0;

            if (checkInsert){
                System.out.println("RR Rule added..!!");
            }else{
                System.out.println("There was an issue while inserting RR Rule");
            }

        }catch (SQLException e) {
            System.out.println("Unable to add RR Rule!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static boolean updateRRRule(RRRule rrRule, String brandId){
        boolean rowUpdated = false;
        try(Connection connection = DBHelper.connect();
            PreparedStatement preparedStatementSelect = connection.prepareStatement(SELECT_RR_RULE_BY_RRC);
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_RR_RULE);) {

            Integer version = 0;
            Integer instances = 0;
            Integer points = 0;

            preparedStatementSelect.setString(1,rrRule.getReward());
            preparedStatementSelect.setString(2,brandId);

            ResultSet rs  = preparedStatementSelect.executeQuery();
            if(rs.next() == false){
                System.out.println("The entered reward name for your brand does not exists.");
            }else{
                do{
                    //get rerule version, set it in rerule object and pass object for update
                    version = rs.getInt("version");
                    instances = rs.getInt("instances");
                    points = rs.getInt("num_points");
                }while(rs.next());
            }

            //Disable the previous version of RR rule
            preparedStatementUpdate.setInt(1,points);
            preparedStatementUpdate.setInt(2,instances);
            preparedStatementUpdate.setInt(3,version);
            preparedStatementUpdate.setString(4,"D");
            preparedStatementUpdate.setString(5,rrRule.getReward());
            preparedStatementUpdate.setString(6,brandId);

            preparedStatementUpdate.executeUpdate();

            version = version + 1;

            rrRule.setRrRuleCode(UUID.randomUUID().toString().replace("-",""));
            rrRule.setVersion(version);
            rrRule.setStatus("E");

            saveData(rrRule);

        }catch (SQLException e) {
            System.out.println("Unable to Update RR Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }

        return rowUpdated;
    }

}
