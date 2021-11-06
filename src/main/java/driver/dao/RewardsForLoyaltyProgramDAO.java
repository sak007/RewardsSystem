package driver.dao;

import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.RewardsForLoyaltyProgram;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RewardsForLoyaltyProgramDAO {
    public static void saveData(RewardsForLoyaltyProgram rewardLp){
        try {
            String query = "Insert into rewards_for_loyalty_program" + rewardLp.getMeta() + " values" + rewardLp.toString();
            DBHelper.executeUpdate(query);
            System.out.println("The chosen reward has been mapped to the Loyalty Program!");
        } catch (SQLException e) {
            System.out.println("Unable to map activity to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
