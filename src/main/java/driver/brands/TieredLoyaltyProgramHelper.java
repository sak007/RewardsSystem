package driver.brands;
import java.util.Scanner;

public class TieredLoyaltyProgramHelper {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose one from below:\n" + "Tiers set up\n" +
                                "Activity types\n" + "Reward Types\n";
        System.out.println(display_string);
        Integer input_lp_type = scanner.nextInt();
        switch (input_lp_type){
            case 1:
                TiersHelper.display();
                break;
            case 2:
                ActivityTypesHelper.display();
                break;
            case 3:
                RewardTypesHelper.display();
                break;
        }
    }

    public static void setup(){

    }
}
