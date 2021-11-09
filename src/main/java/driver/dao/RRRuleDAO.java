package driver.dao;

import driver.object.Brand;
import driver.object.RERule;
import driver.object.RRRule;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class RRRuleDAO {
    public RRRuleDAO(){
    }

    public static void saveData(RRRule rrRule){
        try {
            String insertRRRuleQuery = "INSERT INTO rr_rule" + rrRule.getMeta() + " VALUES " + rrRule.toString();

            boolean insertStatus = DBHelper.executeUpdate(insertRRRuleQuery) > 0;

            if(insertStatus){
                System.out.println("RR Rule added..!!");
            }else{
                System.out.println("There was an issue while inserting RR Rule");
            }


        }catch (SQLException e){
            System.out.println("Unable to add RR Rule!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }

    }

    public static void updateRRRule(RRRule rrRule, String lpId){
        boolean rowUpdated = false;
        try {
            Integer version = 0;
            Integer points = 0;
            String rrRuleCode ="";
            String lpCode = "";
//            String selectQuery = "SELECT * FROM rr_rule rra WHERE rra.rr_rule_code IN (select rrlp.rr_rule_code from rr_rule_for_lp rrlp where rrlp.lp_code IN (select lp.id from Loyalty_program lp where lp.brand_id IN (select b.id from brand b where b.id = '"+brandId+"'))) and rra.status='E' ";

            String selectQuery = "select * from rr_rule where rr_rule_code = '" + rrRule.getRrRuleCode()+ "'and lp_code = '"+ lpId +"' and status = 'E'";
            System.out.println(selectQuery);

            List<Object[]> rs  = DBHelper.executeQueryUpdated(selectQuery);

            if(rs.size() == 0){
                System.out.println("An active reward rule code for your brand does not exists.");
            }else{

                version = ((BigDecimal)rs.get(0)[3]).intValueExact();

                String updateQuery = "UPDATE rr_rule SET status = 'D' WHERE rr_rule_code = '"+ rrRule.getRrRuleCode() + "'";
                System.out.println(updateQuery);
                DBHelper.executeUpdate(updateQuery);

                rrRule.setVersion(version + 1);
                rrRule.setLpCode(lpId);
                rrRule.setStatus("E");

                String insertQuery = "INSERT INTO rr_rule"+ rrRule.getMeta() +" VALUES"+ rrRule.toString();
                System.out.println(insertQuery);
                DBHelper.executeUpdate(insertQuery);
            }
        }catch (SQLException e) {
            System.out.println("Unable to Update RR Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }

    }

}
