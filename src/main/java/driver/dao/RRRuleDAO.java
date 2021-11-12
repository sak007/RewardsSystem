package driver.dao;

import driver.object.Brand;
import driver.object.RERule;
import driver.object.RRRule;
import driver.object.Reward;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class RRRuleDAO {
    public RRRuleDAO(){
    }

    public static void saveData(RRRule rrRule){
        try {
            String insertRRRuleQuery = "INSERT INTO rr_rule" + rrRule.getMeta() + " VALUES " + rrRule.toString();

            DBHelper.executeUpdate(insertRRRuleQuery);

            System.out.println("RR Rule added..!!");

        }catch (SQLException e){
            System.out.println("Unable to add RR Rule!");
            System.out.println("RR rule already exists");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }

    }
    public static List<Reward> getMappedRewards(String lpcode){
        try {
            String query = "select * from reward_category where id IN(select reward_category_code from rewards_for_loyalty_program where loyalty_program_code = '" + lpcode + "')";
            List<Object[]> items = DBHelper.executeQueryUpdated(query);
            List<Reward> reward = new ArrayList<>(items.size());
            for(Object[] item:items) {
                Reward rew = new Reward();
                rew.setCode((String) item[0]);
                rew.setName((String) item[1]);
                reward.add(rew);
            }
            return reward;
        }catch(SQLException e){
            return null;
        }
    }

    public static void disableDoppleganger(RRRule rrRule) throws SQLException {
        try {
            String updateQuery2 = "UPDATE rr_rule SET status = 'D' WHERE reward = '" + rrRule.getReward() + "' and lp_code ='" + rrRule.getLpCode() + "'";
//            System.out.println(updateQuery2);
            DBHelper.executeUpdate(updateQuery2);
            System.out.println("Disabled similar rules SUCCESSFULLY!");
        }
        catch(SQLException e){
            System.out.println(" Unable to disable similar rules");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public static void updateRRRule(RRRule rrRule, String lpId){
        boolean rowUpdated = false;
        try {
            Integer version = 0;

            String selectQuery = "select * from rr_rule where rr_rule_code = '" + rrRule.getRrRuleCode()+ "'and lp_code = '"+ lpId +"' and status = 'E'";
//            System.out.println(selectQuery);

            List<Object[]> rs  = DBHelper.executeQueryUpdated(selectQuery);

            if(rs.size() == 0){
                System.out.println("An active reward rule code for your brand does not exists.");
            }else{

                version = ((BigDecimal)rs.get(0)[3]).intValueExact();

                String updateQuery = "UPDATE rr_rule SET status = 'D' WHERE rr_rule_code = '"+ rrRule.getRrRuleCode() + "'";
//              System.out.println(updateQuery);
                DBHelper.executeUpdate(updateQuery);

                rrRule.setVersion(version + 1);
                rrRule.setLpCode(lpId);
                rrRule.setStatus("E");

                RRRuleDAO.disableDoppleganger(rrRule);

                String insertQuery = "INSERT INTO rr_rule"+ rrRule.getMeta() +" VALUES"+ rrRule.toString();
//              System.out.println(insertQuery);
                DBHelper.executeUpdate(insertQuery);
            }
        }catch (SQLException e) {
            System.out.println("Unable to Update RR Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }
    }
}
