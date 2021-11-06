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
        getWalletDetails(customerId);
        System.out.println("1.Go Back");
        Integer option = scanner.nextInt();
        if (option == 1) {
            CustomerLandingPage.run(customerId);
        }
    }
    public static void getWalletDetails(String customerId){
        List<Wallet> walletList;
        walletList=WalletDAO.showDetailsByCustomerId(customerId);
        System.out.println(walletList);
        walletList.get(0).display();
    }

}
