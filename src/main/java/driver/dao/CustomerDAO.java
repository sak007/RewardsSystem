package driver.dao;

import driver.object.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO {

    public static void saveData(Customer customer) {
        try {
            String query = "Insert into customer" + customer.getMeta() + " values" + customer.toString();
            DBHelper.executeUpdate(query);
            if (customer.getPassword() != null) {
                UserDAO.updatePassword(customer.getUserName(), customer.getPassword());
            }
            System.out.println("Customer Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Customer!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Customer loadById(String id) {
        try {
            String query = "Select * from customer where id = '" + id + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            Customer customer = new Customer();
            if (rs.next()) {
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(Long.parseLong(rs.getString("phone")));
                customer.setLoyaltyProgramId(rs.getString("lp_program_id"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
            }
            rs.close();
            return customer;
        } catch (SQLException e) {
            System.out.println("Unable to load Customer");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
    public static String getCustomerIdByUserName(String username){
        String query="select id from customer where user_name='"+username+"'";
        try {
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            String customerId = "";
            for(Object[] object:rs){
                customerId = object[0].toString();
            }
            return customerId;
        }
        catch (SQLException e) {
            System.out.println("Unable to fetch Customer id");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
