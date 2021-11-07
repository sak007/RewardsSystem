package driver.dao;

import driver.MainMenu;
import driver.object.Wallet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WalletDAO {
    public static List<Wallet> showDetailsByCustomerId(String customerId) {

        try {
            List<Wallet> walletList = new ArrayList<>();
            String query = "select * from wallet where customer_id= '" + customerId + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            while (rs.next()) {
                Wallet wallet = new Wallet();
                wallet.setActivity(rs.getString("activity"));
                wallet.setDateActivity(rs.getDate("date_activity"));
                wallet.setPoints(rs.getInt("points"));
                wallet.setReRuleCode(rs.getString("re_rule_code"));
                wallet.setLoyaltyProgramCode(rs.getString("loyalty_program_code"));
                wallet.setCustomerId(rs.getString("customer_id"));
                walletList.add(wallet);
            }
            return walletList;
        }
        catch (SQLException e) {
            System.out.println("Unable to print Customer wallet details!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static int deductPoints(String customerId,String lpProgramName,int currentPoints,int points) {
        try {
            int newPoints=currentPoints-points;
            String query="update wallet set points="+newPoints+"\n" +
                    "where customer_id='"+customerId+"' and loyalty_program_code=(select id from loyalty_program where program_name='"+lpProgramName+"')";

            return DBHelper.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Unable to update wallet!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return -1;
        }
    }
}
