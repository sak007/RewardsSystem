package driver.admin;

import driver.dao.BrandDAO;

import java.util.Scanner;

public class BrandHelper {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Brand brand = getBrandDetails();
        System.out.println("1. Add Brand\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                BrandDAO.saveData(brand);
                System.out.println("Brand Added!");
            case 2:
                LandingPage.run();
                break;
        }
    }

    private static Brand getBrandDetails() {
        // TODO: 10/29/21  
        Brand brand = new Brand();
        return brand;
    }

    public static void show(Long id) {
        Brand brand = BrandDAO.loadById(id);
        brand.display();
        // TODO: 10/30/21  
    }

}
