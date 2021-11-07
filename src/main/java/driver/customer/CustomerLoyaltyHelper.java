package driver.customer;

import driver.dao.CustomerLPEnrollDAO;
import driver.dao.LoyaltyProgramDAO;

import java.util.Map;
import java.util.Scanner;

public class CustomerLoyaltyHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run(String customerId){
        Map<String,String> programNames= LoyaltyProgramDAO.getLoyaltyProgramList();
        for(Map.Entry<String,String> entry : programNames.entrySet()){
            System.out.println(entry.getKey()+". "+entry.getValue());
        }
        System.out.println("1. Enroll in Loyalty program\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Please Enter the number corresponding to Loyalty Program name");
                String ans=scanner.next();
                if(programNames.containsKey(ans)) {
                    CustomerLPEnrollDAO.saveData(customerId, ans);
                    CustomerLandingPage.run(customerId);
                }
                break;

            case 2:
                CustomerLandingPage.run(customerId);
                break;
        }
    }

}
