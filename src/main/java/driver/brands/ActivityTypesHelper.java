package driver.brands;

import driver.dao.ActivitiesForLoyaltyProgramDAO;
import driver.dao.ActivityDAO;
import driver.dao.LoyaltyProgramDAO;
import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.Activity;
import driver.object.LoyaltyProgram;

import java.util.Scanner;
import java.util.UUID;

public class ActivityTypesHelper {
    public static void display(String tier_type){
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose from one of the options below:\n" + "1)Purchase\n" +
                "2)Leave a review\n" + "3)Refer a friend\n" + "4) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        String test_brand_id = "3";
        String uniqId;
        switch (input){
            case 1:
                //NOTE: If already added purchase, Say So.

                //Purchase

                //Find the activity category obj for with Purchase as name.
                //Find the loyalty program of this brand

                Activity activity = ActivityDAO.loadByName("Purchase");
                LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
                if (loyaltyProgram == null){
                    // Ideally should never come here
                    System.out.println("Loyalty Program Not found!");
                }
                //Add it's ID as the code in the mapping table object
                ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();
                uniqId = UUID.randomUUID().toString().replace("-","");
                activityLp.setId(uniqId);
                activityLp.setActivity_category_code(activity.getCode());
                activityLp.setLoyalty_program_code(loyaltyProgram.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLp);
                ActivityTypesHelper.display(tier_type);
                break;
            case 2:
                //NOTE: If already added Review, Say So.
                //Leave a Review

                //Find the activity category obj for with Review as name.
                //Find the loyalty program of this brand
                Activity activity_review = ActivityDAO.loadByName("Review");
                LoyaltyProgram loyaltyProgram_review = LoyaltyProgramDAO.loadByBrandId(test_brand_id);

                if (loyaltyProgram_review == null){
                    // Ideally should never come here
                    System.out.println("Loyalty Program Not found!");
                }

                //Add it's ID as the code in the mapping table
                ActivitiesForLoyaltyProgram activityLpReview = new ActivitiesForLoyaltyProgram();
                uniqId = UUID.randomUUID().toString().replace("-","");
                activityLpReview.setId(uniqId);
                activityLpReview.setActivity_category_code(activity_review.getCode());
                activityLpReview.setLoyalty_program_code(loyaltyProgram_review.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLpReview);
                ActivityTypesHelper.display(tier_type);
                break;
            case 3:
                //NOTE: If already added Refer, Say So.
                //Refer a friend

                //Find the activity category obj for with Review as name.
                //Find the loyalty program of this brand
                Activity activity_refer = ActivityDAO.loadByName("Refer");
                LoyaltyProgram loyaltyProgram_refer = LoyaltyProgramDAO.loadByBrandId(test_brand_id);

                //Add it's ID as the code in the mapping table
                ActivitiesForLoyaltyProgram activityLpRefer = new ActivitiesForLoyaltyProgram();
                activityLpRefer.setActivity_category_code(activity_refer.getCode());
                activityLpRefer.setLoyalty_program_code(loyaltyProgram_refer.getLpId());

                //Save
                ActivitiesForLoyaltyProgramDAO.saveData(activityLpRefer);
                ActivityTypesHelper.display(tier_type);
                break;
            case 4:
                //Go Back
                if (tier_type == "Regular") {
                    RegularLoyaltyProgramHelper.display();
                }
                else{
                    TieredLoyaltyProgramHelper.display();
                }
                break;
        }
    }

//    public static void purchase(){
//        //Add purchase activity type for the LP of the brand
//        ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();
//
//        ActivitiesForLoyaltyProgramDAO.saveData(activityLp);
//    }
//
//    public static void review(){
//
//    }
//
//    public static void refer(){
//
//    }
}
