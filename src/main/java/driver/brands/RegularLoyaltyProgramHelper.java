package driver.brands;

import java.util.Scanner;

public class RegularLoyaltyProgramHelper {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose from one of the options below:\n" + "1)Activity Types\n" +
                                "2)Reward Types\n" + "3)Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        switch (input){
            case 1:
                //Activity Type
                ActivityTypesHelper.display();
                break;
            case 2:
                //Reward Type
                RewardTypesHelper.display();
                break;
        }

    }
}
