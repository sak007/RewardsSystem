package driver.dao;

import driver.object.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDAO {

    public static void saveData(Brand brand) {
        try {
            String query = "Insert into brand" + brand.getMeta() + " values" + brand.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Brand Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Customer!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Brand loadById(String id) {
        try {
            String query = "Select * from brand where id = '" + id + "'";
            Object[] items = DBHelper.executeQueryUpdated(query);
            Brand brand = new Brand();

            brand.setId((String)items[0]);
            brand.setName((String)items[1]);
            brand.setAddress((String)items[2]);
            brand.setJoinDate((String)items[3]);

            return brand;
        } catch (SQLException e) {
            System.out.println("Unable to load Brand");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
