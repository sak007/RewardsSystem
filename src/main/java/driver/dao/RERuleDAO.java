package driver.dao;

import driver.brands.ActivityCategory;
import driver.object.Activity;
import driver.object.Brand;
import driver.object.RERule;
import driver.object.Reward;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RERuleDAO {

    public RERuleDAO(){
    }

    public static void saveData(RERule reRule){
        try{

            String insertReRulequery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES "+ reRule.toString();

            DBHelper.executeUpdate(insertReRulequery);

            System.out.println("RE Rule added..!!");

        }catch(SQLException e){
            System.out.println("Unable to add RE Rule!");
            System.out.println("RE Rule code already exists for this LP");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static List<Activity> getMappedActivities(String lpcode){
        try {
            String query = "select * from activity_category where id IN(select activity_category_code from activities_for_loyalty_program where loyalty_program_code = '" + lpcode + "')";
//            System.out.println(query);
            List<Object[]> items = DBHelper.executeQueryUpdated(query);
            List<Activity> activity = new ArrayList<>(items.size());
            for(Object[] item:items) {
                Activity act = new Activity();
//                System.out.println("ANSWER: " + (String) item[0] + " and " + (String) item[1]);
                act.setCode((String) item[0]);
                act.setName((String) item[1]);
                activity.add(act);
            }
            return activity;
        }catch(SQLException e){
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static int disableDoppleganger(RERule reRule) throws SQLException {
        try {
            String updateQuery2 = "UPDATE re_rule SET status = 'D' WHERE activity_category_code = '" + reRule.getActivityCategoryCode() + "' and lp_code ='" + reRule.getLpCode() + "'";
//            System.out.println(updateQuery2);
            int ret = DBHelper.executeUpdate(updateQuery2);
            System.out.println("Disabled similar rules SUCCESSFULLY!");
            return ret;
        }
        catch(SQLException e){
            System.out.println(" Unable to disable similar rules");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public static void updateReRule(RERule reRule,String lpId){
        boolean rowUpdated = false;
        Integer version = 0;
        try{
            String selectQuery = "select * from re_rule where re_rule_code = '" + reRule.getReRuleCode()+ "'and lp_code = '"+ lpId +"' and status = 'E'";
//            System.out.println(selectQuery);

            List<Object[]> rs  = DBHelper.executeQueryUpdated(selectQuery);

            if(rs.size() == 0){
                System.out.println("An active reward rule code for your brand does not exists.");
            }else{
                version = ((BigDecimal)rs.get(0)[3]).intValueExact();
                String updateQuery = "UPDATE re_rule SET status = 'D' WHERE re_rule_code = '"+ reRule.getReRuleCode() + "'";
//                System.out.println(updateQuery);
                DBHelper.executeUpdate(updateQuery);

                reRule.setVersion(version + 1);
                reRule.setLpCode(lpId);
                reRule.setStatus("E");

                RERuleDAO.disableDoppleganger(reRule);

                String insertQuery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES"+ reRule.toString();
//                System.out.println(insertQuery);
                DBHelper.executeUpdate(insertQuery);
            }

        } catch (SQLException e) {
            System.out.println("Unable to Update RE Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }
    }
}
