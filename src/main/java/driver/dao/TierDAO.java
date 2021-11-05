package driver.dao;

import driver.object.Activity;
import driver.object.Tier;

import java.sql.SQLException;

public class TierDAO {
    public static void saveData(Tier tier) {
        try {
            String query = "Insert` into tier" + tier.getMeta() + " values" + tier.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Tier added for the LoyaltyProgram!");
        } catch (SQLException e) {
            System.out.println("Unable to add Tier!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
../dao/TierDAO.java