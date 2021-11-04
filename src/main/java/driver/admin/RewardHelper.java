package driver.admin;

import driver.dao.RewardDAO;
import driver.object.Reward;

import java.util.Scanner;

public class RewardHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        Reward reward = getRewardDetails();
        System.out.println("1. Add Reward\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                RewardDAO.saveData(reward);
            case 2:
                AdminLandingPage.run();
                break;
        }
    }

    private static Reward getRewardDetails() {
        Reward reward = new Reward();
        System.out.print("Enter Reward Code: ");
        reward.setCode(scanner.next());
        System.out.print("Enter Reward Name: ");
        reward.setName(scanner.next());
        return reward;
    }

}
