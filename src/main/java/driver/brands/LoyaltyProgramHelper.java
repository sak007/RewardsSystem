package driver.brands;

import driver.admin.Brand;

import java.util.Scanner;

public class LoyaltyProgramHelper {
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the Loyalty Program\n");
        String input_lp_name = scanner.nextLine();
        String display_string = "Choose the type of the Loyalty Program\n 1) Regular\n" + "2) Tier\n" + "Go Back\n";
        System.out.println(display_string);
        Integer input_lp_type = scanner.nextInt();
        switch (input_lp_type){
            case 1:
                //Regular - Go to Brand Regular Page
                RegularLoyaltyProgramHelper.display();
                break;
            case 2:
                //Tiered - Go to Brand Tier Page
                TieredLoyaltyProgramHelper.display();
                break;
        }
    }

    public static void validate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    }
}
