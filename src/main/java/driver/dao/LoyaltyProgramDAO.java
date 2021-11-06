package driver.dao;

import driver.object.Activity;
import driver.object.LoyaltyProgram;
import driver.object.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoyaltyProgramDAO {
    public static void saveData(LoyaltyProgram loyaltyProg) {
        try {
            String query = "Insert into Loyalty_program" + loyaltyProg.getMeta() + " values" + loyaltyProg.toString();
            System.out.println(query);
            DBHelper.executeUpdate(query);
            System.out.println("Inserted\n");

            System.out.println("Loyalty Program  Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }


    public static LoyaltyProgram loadByBrandId(String brand_id) {
        try {
            String query = "Select * from Loyalty_program where brand_id = '" + brand_id + "'";
            System.out.println(query);
            List<Object[]> rs =  DBHelper.executeQueryUpdated(query);
            LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
            for(Object[] object:rs) {
                loyaltyProgram.setLpId((String) object[0]);
                loyaltyProgram.setProgramName((String) object[1]);
                loyaltyProgram.setBrandId((String) object[2]);
                loyaltyProgram.setTierType((String) object[3]);
                loyaltyProgram.setState((String) object[4]);
            }
            return loyaltyProgram;
        } catch (SQLException e) {
            System.out.println("Unable to load Loyalty Program from ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
