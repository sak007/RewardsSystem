package driver.dao;

import driver.object.Activity;
import driver.object.LoyaltyProgram;
import driver.object.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoyaltyProgramDAO {
    public static void saveData(LoyaltyProgram loyaltyProg) {
        try {
            String query = "Insert into Loyalty_program" + loyaltyProg.getMeta() + " values" + loyaltyProg.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Loyalty Program  Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }


    public static LoyaltyProgram loadByBrandId(String brand_id) {
        try {
            String query = "Select * from brand where brand_id = '" + brand_id + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
            if (rs.next()) {
                loyaltyProgram.setLpId(rs.getString("code"));
                loyaltyProgram.setBrandId(rs.getString("brand_id"));
                loyaltyProgram.setProgramName(rs.getString("program_name"));
                loyaltyProgram.setReRuleCode(rs.getString("re_rule_code"));
                loyaltyProgram.setRrRuleCode(rs.getString("rr_rule_code"));
                loyaltyProgram.setState(rs.getString("state"));
                loyaltyProgram.setTierType(rs.getString("tier_type"));
            }
            return loyaltyProgram;
        } catch (SQLException e) {
            System.out.println("Unable to load Activity from ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
