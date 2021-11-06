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
            String query = "Select * from activity_category where id = '" + id + "'";
            System.out.println(query);
            Object[] items = DBHelper.executeQueryUpdated(query);
            Activity activity = new Activity();

            activity.setCode((String)items[0]);
            activity.setName((String)items[1]);

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
            Object[] items = DBHelper.executeQueryUpdated(query);
//            System.out.println(rs.getFetchSize());
            Activity activity = new Activity();
            System.out.println("ANSWER: " + (String)items[0] + " and " + (String)items[1]);
            activity.setCode((String)items[0]);
            activity.setName((String)items[1]);

            System.out.println("ACTIVITY!!!: "+ activity.getCode() + activity.getName());
            return activity;
        } catch (SQLException e) {
            System.out.println("Unable to load Activity from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
