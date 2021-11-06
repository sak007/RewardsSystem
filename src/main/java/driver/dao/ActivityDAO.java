package driver.dao;

import driver.object.Activity;
import driver.object.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            Activity activity = new Activity();

            for(Object[] object:rs) {
                activity.setCode((String) object[0]);
                activity.setName((String) object[1]);
            }

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
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
//            System.out.println(rs.getFetchSize());
            Activity activity = new Activity();

            for(Object[] object:rs) {
                System.out.println("ANSWER: " + (String) object[0] + " and " + (String) object[1]);
                activity.setCode((String) object[0]);
                activity.setName((String) object[1]);
            }

            System.out.println("ACTIVITY!!!: "+ activity.getCode() + activity.getName());
            return activity;
        } catch (SQLException e) {
            System.out.println("Unable to load Activity from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
