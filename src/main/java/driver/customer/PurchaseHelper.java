package driver.customer;

import driver.dao.CustomerActivityDAO;
import driver.dao.CustomerRedeemActivityDAO;
import driver.dao.RewardsForLoyaltyProgramDAO;
import driver.object.CustomerActivity;
import driver.object.CustomerRedeemActivity;
import driver.object.RewardsForLoyaltyProgram;

import java.util.List;
import java.util.Scanner;

public class PurchaseHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run(CustomerActivity customerActivity, String lpId) {
        System.out.println("Enter Purchase Value: ");
        Long purchaseValue = scanner.nextLong();
        List<CustomerRedeemActivity> customerRedeemActivityList = CustomerRedeemActivityDAO.loadByCustomerLpIdAndReward(customerActivity.getCustomerId(), lpId, "Gift Card");
        if (!customerRedeemActivityList.isEmpty()) {
            System.out.println("Select Gift Cards to use");
            for (int i=1; i<=customerRedeemActivityList.size(); i++) {
                CustomerRedeemActivity r = customerRedeemActivityList.get(i-1);
                System.out.println(i + ". Gift Card " + i + ": " + r.getValue());
            }
            System.out.println(customerRedeemActivityList.size() + 1 + ". None");
            Integer option = scanner.nextInt();
            if (option <= customerRedeemActivityList.size()) {
                CustomerRedeemActivity selectedGC = customerRedeemActivityList.get(option - 1);
                customerActivity.setCustomerRedeemActivityId(selectedGC.getId());
                if (selectedGC.getValue() >= purchaseValue) {
                    customerActivity.setPoints(0L);
                }
            }
        }
        CustomerActivityDAO.saveData(customerActivity);
    }
}
