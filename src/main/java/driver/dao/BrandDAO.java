package driver.dao;

import driver.object.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            Brand brand = new Brand();

            for(Object[] object:rs) {
                brand.setId((String) object[0]);
                brand.setName((String) object[1]);
                brand.setAddress((String) object[2]);
                brand.setJoinDate((String) object[3]);
            }

            return brand;
        } catch (SQLException e) {
            System.out.println("Unable to load Brand");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
