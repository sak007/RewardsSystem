package driver.brands;

import driver.dao.ActivitiesForLoyaltyProgramDAO;
import driver.dao.ActivityDAO;
import driver.dao.LoyaltyProgramDAO;
import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.Activity;
import driver.object.LoyaltyProgram;

import javax.accessibility.AccessibleIcon;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ActivityTypesHelper {
    public static void display(String tier_type){
        Scanner scanner = new Scanner(System.in);

        // Print dynamic available list of activity categories
        List<Activity> activities = ActivityDAO.getList();
        int display_i = 0;
        int activity_index = 0;
        String display_string = "Choose from one of the options below:\n";
        for(Activity act:activities){
            display_string = display_string + (display_i + 1) + ") " + act.getName() + "\n";
            display_i++;
        }
        display_string = display_string + (display_i + 1) + ") Go Back\n";

        System.out.println(display_string);
        Integer input = scanner.nextInt();
        String test_brand_id = "4";
        String uniqId;
        System.out.println("Input DISPLAY:" + input + "adddddd" + display_i);
        if(input == display_i + 1){
            if (tier_type == "Regular") {
                RegularLoyaltyProgramHelper.display();
            }
            else{
                TieredLoyaltyProgramHelper.display();
            }
        }
        else{
            activity_index = input - 1;
            Activity activity = activities.get(activity_index);
            LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
            if (loyaltyProgram == null){
                // Ideally should never come here
                System.out.println("Loyalty Program Not found!");
            }
            ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();
            uniqId = UUID.randomUUID().toString().replace("-","");
            activityLp.setId(uniqId);
            activityLp.setActivity_category_code(activity.getCode());
            activityLp.setLoyalty_program_code(loyaltyProgram.getLpId());

            //Save
            ActivitiesForLoyaltyProgramDAO.saveData(activityLp);
            ActivityTypesHelper.display(tier_type);
        }
    }
}
