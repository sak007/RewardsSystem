package driver.admin;

import driver.Login;
import driver.SignUp;
import driver.dao.BrandDAO;
import driver.dao.CustomerDAO;
import driver.object.Brand;
import driver.object.Customer;

import java.util.Scanner;

public class BrandHelper {
    static Scanner scanner = new Scanner(System.in);
    public static void run() {
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

    public static void signUp() {
        Brand brand = getBrandDetails();
        System.out.println("1. SignUp\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                BrandDAO.saveData(brand);
                Login.run();
                break;
            case 2:
                SignUp.run();
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
        System.out.println("Enter Brand Username");
        String username = scanner.next();
        brand.setUserName(username);
        System.out.println("Enter Brand Password");
        String password = scanner.next();
        brand.setPassword(password);
        System.out.println("Enter Brand Address");
        String address = scanner.next();
        brand.setAddress(address);
        return brand;
    }

    public static void show(String id) {
        System.out.println("1. Show Brand Info\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                Brand brand = BrandDAO.loadById(id);
                brand.display();
                System.out.println("1. Go Back");
                scanner.nextInt();
            case 2:
                AdminLandingPage.run();
        }
    }

}
