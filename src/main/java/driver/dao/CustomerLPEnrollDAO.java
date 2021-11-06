package driver.dao;

import driver.object.Customer;

import java.sql.SQLException;

public class CustomerLPEnrollDAO {
    public static void saveData(String customerId, String loyaltyProgramId) {
        try {
            String query = "Insert into customer_lp_enroll values ('"+customerId+"','"+loyaltyProgramId+"')";
            DBHelper.executeUpdate(query);
            System.out.println("Customer Enrolled to Loyalty program successfully!");
        } catch (SQLException e) {
            System.out.println("Customer is already enrolled in the chosen Loyalty Program");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
