package driver.dao;

import driver.object.Activity;
import driver.object.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDAO {

    public static void saveData(Activity activity) {
        try {
            String query = "Insert into activity_category" + activity.getMeta() + " values" + activity.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Activity Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Activity!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Activity loadById(String id) {
        try {
            System.out.println("WHAAAAT!?!!?!?!\n");
            String query = "Select * from activity_category where id = '" + id + "'";
            System.out.println(query);
            ResultSet rs = DBHelper.executeQuery(query);
            Activity activity = new Activity();
            if (rs.next()) {
                activity.setCode(rs.getString("id"));
                activity.setName(rs.getString("code"));
            }
            rs.close();
            return activity;
        } catch (SQLException e) {
            System.out.println("Unable to load Activity from ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }


    public static Activity loadByName(String name) {
        try {
            String query = "Select * from activity_category where activity_name = '" + name + "'";
            System.out.println(query);
            ResultSet rs = DBHelper.executeQuery(query);
            System.out.println(rs.getFetchSize());
            Activity activity = new Activity();
            if (rs.next()) {
                activity.setCode(rs.getString("id"));
                activity.setName(rs.getString("activity_name"));
            }
            rs.close();
            System.out.println("ACTIVITY!!!: "+ activity.getCode() + activity.getName());
            return activity;
        } catch (SQLException e) {
            System.out.println("Unable to load Activity from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
