package driver.dao;

import driver.object.Brand;
import driver.object.RERule;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RERuleDAO {

    public RERuleDAO(){
    }

    public static void saveData(RERule reRule){
        try{
            String insertReRulequery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES "+ reRule.toString();

            boolean insertStatus = DBHelper.executeUpdate(insertReRulequery) > 0;

            if(insertStatus){
                System.out.println("RE Rule added..!!");
            }else{
                System.out.println("There was an issue while inserting RE Rule");
            }

        }catch(SQLException e){
            System.out.println("Unable to add RE Rule!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void updateReRule(RERule reRule,String lpId){
        boolean rowUpdated = false;
        Integer version = 0;
        try{
            String selectQuery = "select * from re_rule where re_rule_code = '" + reRule.getReRuleCode()+ "'and lp_code = '"+ lpId +"' and status = 'E'";
            System.out.println(selectQuery);

            List<Object[]> rs  = DBHelper.executeQueryUpdated(selectQuery);

            if(rs.size() == 0){
                System.out.println("An active reward rule code for your brand does not exists.");
            }else{
                version = ((BigDecimal)rs.get(0)[3]).intValueExact();
                String updateQuery = "UPDATE re_rule SET status = 'D' WHERE re_rule_code = '"+ reRule.getReRuleCode() + "'";
                System.out.println(updateQuery);
                DBHelper.executeUpdate(updateQuery);

                reRule.setVersion(version + 1);
                reRule.setLpCode(lpId);
                reRule.setStatus("E");


                String insertQuery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES"+ reRule.toString();
                System.out.println(insertQuery);
                DBHelper.executeUpdate(insertQuery);
            }

        } catch (SQLException e) {
            System.out.println("Unable to Update RE Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }
    }
}
