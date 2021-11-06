package driver.admin;

import driver.dao.BrandDAO;
import driver.object.Brand;

import java.sql.SQLException;
import java.util.Scanner;

public class BrandHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run() throws SQLException {
        Brand brand = getBrandDetails();
        System.out.println("1. Add Brand\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                BrandDAO.saveData(brand);
            case 2:
                AdminLandingPage.run();
                break;
        }
    }

    private static Brand getBrandDetails() {
        Brand brand = new Brand();
        System.out.println("Enter Brand Id");
        String id = scanner.next();
        brand.setId(id);
        System.out.println("Enter Brand Name");
        String name = scanner.next();
        brand.setName(name);
        System.out.println("Enter Brand Address");
        String address = scanner.next();
        brand.setAddress(address);
        return brand;
    }

    public static void show(String id) {
        Brand brand = BrandDAO.loadById(id);
        brand.display();
    }

}
