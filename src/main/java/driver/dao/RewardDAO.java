package driver.dao;

import driver.object.Activity;
import driver.object.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            Reward reward = new Reward();
            for(Object[] object:rs) {
                reward.setCode((String) object[0]);
                reward.setName((String) object[1]);
            }
            return reward;
        } catch (SQLException e) {
            System.out.println("Unable to load Reward from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }


    public static List<Reward> getList() {
        try {
            String query = "Select * from reward_category";
            System.out.println(query);
            List<Object[]> items = DBHelper.executeQueryUpdated(query);

            List<Reward> reward = new ArrayList<>(items.size());
            for(Object[] item:items) {
                Reward rew = new Reward();
                System.out.println("ANSWER: " + (String) item[0] + " and " + (String) item[1]);
                rew.setCode((String) item[0]);
                rew.setName((String) item[1]);
                reward.add(rew);
            }
            return reward;
        } catch (SQLException e) {
            System.out.println("Unable to get the list of available rewards");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
