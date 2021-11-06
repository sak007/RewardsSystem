package driver.brands;

import java.util.Scanner;

public class ReRulesHelper {
    static final String jdbcURL
            = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    public static void add(){
        Scanner scanner = new Scanner(System.in);
        String test_brand_int = "2";

        String display_string = " A. Enter Brand reward rule code:\n";
        System.out.print(display_string);
        String brand_re_rule_code = scanner.next();
        display_string = "B. Enter Activity category: \n";
        String activity = scanner.next();
        display_string = "C. number of points: \n";
        Integer points = scanner.nextInt();

        display_string = "Choose one from below:\n 1) addReRule \n2)Go Back\n";
        Integer chosen_option = scanner.nextInt();
        switch (chosen_option){
            case 1:
                //AddReRule

                break;
            case 2:
                //Go back
                break;
        }

    }
}
