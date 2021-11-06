package driver.dao;

import driver.object.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDAO {

    public static void saveData(Brand brand) {
        try {
            String query = "Insert into brand" + brand.getMeta() + " values" + brand.toString();
            DBHelper.executeUpdate(query);
            if (brand.getPassword() != null) {
                UserDAO.updatePassword(brand.getUserName(), brand.getPassword());
            }
            System.out.println("Brand Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Brand!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Brand loadById(String id) {
        try {
            String query = "Select * from brand where id = '" + id + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            Brand brand = new Brand();
            if (rs.next()) {
                brand.setId(rs.getString("id"));
                brand.setName(rs.getString("name"));
                brand.setUserName(rs.getString("user_name"));
                brand.setAddress(rs.getString("address"));
                brand.setJoinDate(rs.getString("join_date"));
            }
            return brand;
        } catch (SQLException e) {
            System.out.println("Unable to load Brand");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
