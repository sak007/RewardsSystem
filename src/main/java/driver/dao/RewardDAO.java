package driver.dao;

import driver.object.Activity;
import driver.object.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RewardDAO {

    public static void saveData(Reward reward) {
        try {
            String query = "Insert into reward_category" + reward.getMeta() + " values" + reward.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Reward Type Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Reward Type!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Reward loadByName(String reward_name) {
        try {
            String query = "Select * from reward_category where reward_name = '" + reward_name + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            Reward reward = new Reward();
            if (rs.next()) {
                reward.setCode(rs.getString("id"));
                reward.setName(rs.getString("reward_name"));
            }
            return reward;
        } catch (SQLException e) {
            System.out.println("Unable to load Reward from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
