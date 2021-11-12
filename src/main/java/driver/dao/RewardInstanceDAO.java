package driver.dao;

import driver.object.Reward;
import driver.object.RewardInstance;

import java.sql.SQLException;

public class RewardInstanceDAO {
    public static void saveData(RewardInstance reward_instance) {
        try {
            String query = "Insert into reward_instance" + reward_instance.getMeta() + " values" + reward_instance.toString();
            System.out.println("Inserting INSTANCE!!!!!!\n");
            //System.out.println(query);
            DBHelper.executeUpdate(query);
            System.out.println("Reward Instance Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Reward Instance!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
