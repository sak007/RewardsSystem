package driver.dao;

import driver.object.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerLPEnrollDAO {
    public static void saveData(String customerId, List<String> loyaltyProgram) {
        try {
            String query = "Insert into customer_lp_enroll values ('"+customerId+"','"+loyaltyProgram.get(0)+"')";
            DBHelper.executeUpdate(query);
            System.out.println("Customer Enrolled to Loyalty program successfully!");
        } catch (SQLException e) {
            System.out.println("Customer is already enrolled in the chosen Loyalty Program");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
