package driver.brands;
import java.util.Scanner;

public class TieredLoyaltyProgramHelper {
    public static void display(String brand_id) {
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose one from below:\n" + "1)Tiers set up\n" +
                                "2)Activity types\n" + "3)Reward Types\n 4)Go Back\n";
        System.out.println(display_string);
        Integer input_lp_type = scanner.nextInt();
        switch (input_lp_type){
            case 1:
                TiersHelper.display(brand_id);
                break;
            case 2:
                ActivityTypesHelper.display("Tier", brand_id);
                break;
            case 3:
                RewardTypesHelper.display("Tier", brand_id);
                break;
            case 4:
                LoyaltyProgramHelper.add(brand_id);
                break;
        }
    }

    public static void setup(){

    }
}
