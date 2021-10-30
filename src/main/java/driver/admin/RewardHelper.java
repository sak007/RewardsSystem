package driver.admin;

import driver.dao.RewardDAO;

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
                System.out.println("Reward Added!");
            case 2:
                LandingPage.run();
                break;
        }
    }

    private static Reward getRewardDetails() {
        // TODO: 10/29/21
        Reward reward = new Reward();
        System.out.print("Enter Reward Name: ");
        reward.setName(scanner.next());
        System.out.print("Enter Reward Code: ");
        reward.setCode(scanner.next());
        return reward;
    }

}
