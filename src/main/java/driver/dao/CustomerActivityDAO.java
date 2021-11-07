package driver.dao;

import driver.object.CustomerActivity;

import java.sql.SQLException;

public class CustomerActivityDAO {

    public static void saveData(CustomerActivity customerActivity) {
        try {
            String query = "Insert into customer_activity" + customerActivity.getMeta() + " values" + customerActivity.toString();
            System.out.println(query);
            DBHelper.executeUpdate(query);
            System.out.println("Customer Activity Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Customer Activity!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

}
