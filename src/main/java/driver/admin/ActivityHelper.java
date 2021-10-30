package driver.admin;

import driver.dao.ActivityDAO;

import java.util.Scanner;

public class ActivityHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run() {
        Activity activity = getActivityDetails();
        System.out.println("1. Add Activity Type\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                ActivityDAO.saveData(activity);
                System.out.println("Activity Added!");
            case 2:
                LandingPage.run();
                break;
        }
    }

    private static Activity getActivityDetails() {
        // TODO: 10/29/21
        Activity activity = new Activity();
        System.out.print("Enter Activity Name: ");
        activity.setName(scanner.next());
        System.out.print("Enter Activity Code: ");
        activity.setCode(scanner.next());
        return activity;
    }

}
