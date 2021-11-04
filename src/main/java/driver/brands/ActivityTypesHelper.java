package driver.brands;

import java.util.Scanner;

public class ActivityTypesHelper {
    public static void display(){
        Scanner scanner = new Scanner(System.in);
        String display_string = "Choose from one of the options below:\n" + "1)Purchase\n" +
                "2)Leave a review\n" + "3)Refer a friend\n" + "4) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        switch (input){
            case 1:
                //Purchase
                purchase();
                break;
            case 2:
                //Leave a Review
                review();
                break;
            case 3:
                //Refer a friend
                refer();
                break;
            case 4:
                //Go Back
                break;
        }
    }

    public static void purchase(){

    }

    public static void review(){

    }

    public static void refer(){

    }
}
