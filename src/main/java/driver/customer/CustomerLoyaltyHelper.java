package driver.customer;

import driver.dao.CustomerLPEnrollDAO;
import driver.dao.LoyaltyProgramDAO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerLoyaltyHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run(String customerId){
        List<List<String>> programNames= LoyaltyProgramDAO.getLoyaltyProgramList();
        for(int i=0;i< programNames.size();i++){
            System.out.println((i+1)+"\t"+programNames.get(i).get(0)+"\t"+programNames.get(i).get(1));
        }
        System.out.println("Please Enter the number corresponding to Loyalty Program");
        int ans=scanner.nextInt();
        System.out.println("1. Enroll in Loyalty program\n2. Go Back");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                for(int i=0;i< programNames.size();i++){
                    if(ans==(i+1)) {
                        CustomerLPEnrollDAO.saveData(customerId, programNames.get(i));
                        CustomerLandingPage.run(customerId);
                        break;
                    }
                }
                CustomerLandingPage.run(customerId);
                break;

            case 2:
                CustomerLandingPage.run(customerId);
                break;
        }
    }

}
