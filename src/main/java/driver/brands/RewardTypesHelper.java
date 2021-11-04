package driver.brands;

import java.util.Scanner;

public class RewardTypesHelper {
    public static void display(){
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose from one of the options below:\n" + "1)Gift Card\n" +
                "2)Free Product\n" + "3) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        switch (input){
            case 1:
                //Purchase
                giftCard();
                break;
            case 2:
                //Leave a Review
                freeProduct();
                break;
            case 3:
                // Go Back
                break;
        }
    }

    public static void giftCard(){

    }

    public static void freeProduct(){

    }
}
