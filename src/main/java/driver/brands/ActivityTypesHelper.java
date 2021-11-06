package driver.brands;

import driver.dao.ActivitiesForLoyaltyProgramDAO;
import driver.dao.ActivityDAO;
import driver.dao.LoyaltyProgramDAO;
import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.Activity;
import driver.object.LoyaltyProgram;

import java.util.Scanner;

public class ActivityTypesHelper {
    public static void display(){
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose from one of the options below:\n" + "1)Purchase\n" +
                "2)Leave a review\n" + "3)Refer a friend\n" + "4) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        String test_brand_id = "1";
        switch (input){
            case 1:
                //NOTE: If already added purchase, Say So.

                //Purchase

                //Find the activity category obj for with Purchase as name.
                //Find the loyalty program of this brand

                Activity activity = ActivityDAO.loadByName("Purchase");
                LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);

                //Add it's ID as the code in the mapping table object
                ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();
                activityLp.setActivity_category_code(activity.getCode());
                activityLp.setActivity_category_code(loyaltyProgram.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLp);
                break;
            case 2:
                //NOTE: If already added Review, Say So.
                //Leave a Review

                //Find the activity category obj for with Review as name.
                //Find the loyalty program of this brand
                Activity activity_review = ActivityDAO.loadByName("Review");
                LoyaltyProgram loyaltyProgram_review = LoyaltyProgramDAO.loadByBrandId("test_brand_id");

                //Add it's ID as the code in the mapping table
                ActivitiesForLoyaltyProgram activityLpReview = new ActivitiesForLoyaltyProgram();
                activityLpReview.setActivity_category_code(activity_review.getCode());
                activityLpReview.setActivity_category_code(loyaltyProgram_review.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLpReview);
                break;
            case 3:
                //NOTE: If already added Refer, Say So.
                //Refer a friend

                //Find the activity category obj for with Review as name.
                //Find the loyalty program of this brand
                Activity activity_refer = ActivityDAO.loadByName("Refer");
                LoyaltyProgram loyaltyProgram_refer = LoyaltyProgramDAO.loadByBrandId("test_brand_id");

                //Add it's ID as the code in the mapping table
                ActivitiesForLoyaltyProgram activityLpRefer = new ActivitiesForLoyaltyProgram();
                activityLpRefer.setActivity_category_code(activity_refer.getCode());
                activityLpRefer.setActivity_category_code(loyaltyProgram_refer.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLpRefer);
                break;
            case 4:
                //Go Back
                break;
        }
    }

    public static void purchase(){
        //Add purchase activity type for the LP of the brand
        ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();

        ActivitiesForLoyaltyProgramDAO.saveData(activityLp);
    }

    public static void review(){

    }

    public static void refer(){

    }
}