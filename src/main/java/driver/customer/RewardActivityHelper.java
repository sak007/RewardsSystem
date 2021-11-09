package driver.customer;

import driver.brands.ActivityCategory;
import driver.dao.ActivitiesForLoyaltyProgramDAO;
import driver.dao.CustomerActivityDAO;
import driver.dao.LoyaltyProgramDAO;
import driver.object.CustomerActivity;
import driver.object.LoyaltyProgram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RewardActivityHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run(String customerId) {
        List<LoyaltyProgram> loyaltyProgramList = LoyaltyProgramDAO.getActiveLoyaltyProgramsForCustomer(customerId);
        if (loyaltyProgramList.size() == 0) {
            System.out.println("Sorry, no Loyalty programs Available!");
            System.out.println("1. Go Back");
            scanner.nextInt();
            CustomerLandingPage.run(customerId);
        } else {
            for (int i=1; i <= loyaltyProgramList.size(); i++) {
                System.out.println(i + ". " + loyaltyProgramList.get(i-1).getProgramName());
            }
            Integer option = scanner.nextInt();
            CustomerActivity customerActivity = new CustomerActivity();
            customerActivity.setCustomerId(customerId);
            displayActivityCategoriesByLp(loyaltyProgramList.get(option - 1), customerActivity);
        }
    }

    private static void displayActivityCategoriesByLp(LoyaltyProgram loyaltyProgram, CustomerActivity customerActivity) {
        String lpId = loyaltyProgram.getLpId();
        List<ActivityCategory> activityCategoryList = ActivitiesForLoyaltyProgramDAO.loadActivityCategoriesByLp(lpId);
        int i = 1;
        for (ActivityCategory a : activityCategoryList) {
            System.out.println(i + ". " + a.getActivityName());
            i++;
        }
        if (i == 1) {
            System.out.println("No Activity categories available to select!");
        }
        System.out.println(i + ". Go Back");
        Integer option = scanner.nextInt();
        if (option < i) {
            String aId = activityCategoryList.get(option - 1).getId();
            String activityLpId = ActivitiesForLoyaltyProgramDAO.getIdByActivityAndLp(aId, lpId);
            customerActivity.setActivityLpId(activityLpId);
            String activity = activityCategoryList.get(option - 1).getActivityName();
            switch (activity) {
                case "Purchase":
                    PurchaseHelper.run(customerActivity, lpId);
                    break;
                case "Review":
                    System.out.println("Enter review comments:");
                    String review = scanner.nextLine();
                    // save review details if necessary
                case "Refer":
                default:
                    CustomerActivityDAO.saveData(customerActivity);
            }
        }
        CustomerLandingPage.run(customerActivity.getCustomerId());

    }

}
