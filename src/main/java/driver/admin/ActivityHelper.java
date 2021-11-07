package driver.admin;

import driver.dao.ActivityDAO;
import driver.object.Activity;

import java.sql.SQLException;
import java.util.Scanner;

public class ActivityHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run(){
        Activity activity = getActivityDetails();
        System.out.println("1. Add Activity Type\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                ActivityDAO.saveData(activity);
            case 2:
                AdminLandingPage.run();
                break;
        }
    }

    private static Activity getActivityDetails() {
        Activity activity = new Activity();
        System.out.print("Enter Activity Code: ");
        activity.setCode(scanner.nextLine());
        System.out.print("Enter Activity Name: ");
        activity.setName(scanner.nextLine());
        return activity;
    }

}
