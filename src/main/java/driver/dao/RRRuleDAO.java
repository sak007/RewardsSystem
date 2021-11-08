package driver.dao;

import driver.object.Brand;
import driver.object.RERule;
import driver.object.RRRule;

import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class RRRuleDAO {

    private static final String INSERT_RR_RULE = "INSERT INTO rr_rule"+"(rr_rule_code, reward, num_points, version, status) VALUES"+"(?,?,?,?,?)";
    private static final String UPDATE_RR_RULE = "UPDATE rr_rule SET num_points = ?, version = ?, status = ? WHERE reward = ? AND rr_rule_code = (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code = (select lp.id from Loyalty_program lp where lp.brand_id = (select b.id from brand b where b.id = ?)))";
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
            preparedStatement.setInt(4,rrRule.getVersion());
            preparedStatement.setString(5,rrRule.getStatus());
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

    public static void updateRRRule(RRRule rrRule, String brandId){
        boolean rowUpdated = false;
        try {

            Integer version = 0;
            Integer points = 0;
            String rrRuleCode ="";
            String lpCode = "";

            String selectQuery = "SELECT * FROM rr_rule rra WHERE rra.rr_rule_code IN (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code IN (select lp.id from Loyalty_program lp where lp.brand_id IN (select b.id from brand b where b.id = '"+brandId+"'))) and rra.status='E' ";

            System.out.println(selectQuery);

            ResultSet rs = DBHelper.executeQuery(selectQuery);

            if(rs.next() == false){
                System.out.println("The entry for your brand does not exists.");
            }else{
                do{
                    //get rerule version, set it in rerule object and pass object for update
                    version = rs.getInt("version");
                    rrRuleCode = rs.getString("rr_rule_code");
                    System.out.println("RRrulecode:"+rrRuleCode);
                }while(rs.next());
            }

            System.out.println("V:"+version);
            rrRule.setVersion(version);
            rrRule.setStatus("D");

            String updateQuery = "UPDATE rr_rule SET status = '"+rrRule.getStatus()+"' WHERE reward = '"+rrRule.getReward()+"' AND version ="+rrRule.getVersion()+" AND rr_rule_code IN (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code IN (select lp.id from Loyalty_program lp where lp.brand_id IN (select b.id from brand b where b.id = '"+brandId+"')))";
            System.out.println(updateQuery);

            DBHelper.executeUpdate(updateQuery);

            version = version + 1;

            rrRule.setRrRuleCode(UUID.randomUUID().toString().replace("-",""));
            rrRule.setVersion(version);
            rrRule.setStatus("E");

            String rrlpQuery = "select * from rr_rule_for_lp where rr_rule_code='"+rrRuleCode+"'";
            System.out.println(rrlpQuery);

            ResultSet rs2 = DBHelper.executeQuery(rrlpQuery);

            if(rs2.next()){
                lpCode = rs2.getString("lp_code");
            }

            String insertLPRR = "insert into rr_rule_for_lp values ('"+lpCode+"','"+rrRule.getRrRuleCode()+"')";
            System.out.println(insertLPRR);
            saveData(rrRule);

            DBHelper.executeUpdate(insertLPRR);

        }catch (SQLException e) {
            System.out.println("Unable to Update RR Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }

        //return rowUpdated;
    }

}
