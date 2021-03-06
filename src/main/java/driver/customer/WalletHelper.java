package driver.customer;

import driver.admin.AdminLandingPage;
import driver.dao.BrandDAO;
import driver.dao.WalletDAO;
import driver.object.Brand;
import driver.object.Wallet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WalletHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run(String customerId)  {
        System.out.println("Customer Wallet Details");
        WalletDAO.showDetailsByCustomerId(customerId);
        System.out.println("\n");
        System.out.println("Customer Reward Activity Details");
        WalletDAO.showActivityDetailsByCustomerId(customerId);
        System.out.println("\n");
        System.out.println("Customer Reward Redeem Details");
        WalletDAO.showRedeemDetailsByCustomerId(customerId);
        System.out.println("\n");
        System.out.println("1.Go Back");
        Integer option = scanner.nextInt();
        if (option == 1) {
            CustomerLandingPage.run(customerId);
        }
    }

}
