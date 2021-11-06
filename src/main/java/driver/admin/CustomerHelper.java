package driver.admin;

import driver.Login;
import driver.SignUp;
import driver.dao.BrandDAO;
import driver.dao.CustomerDAO;
import driver.object.Brand;
import driver.object.Customer;

import java.util.Scanner;

public class CustomerHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        Customer customer = getCustomerDetails();
        System.out.println("1. Add Customer\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                CustomerDAO.saveData(customer);
            case 2:
                AdminLandingPage.run();
                break;
        }

    }

    public static void signUp() {
        Customer customer = getCustomerDetails();
        System.out.println("1. SignUp\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                CustomerDAO.saveData(customer);
                Login.run();
                break;
            case 2:
                SignUp.run();
                break;
        }
    }

    private static Customer getCustomerDetails() {
        Customer customer = new Customer();
        System.out.println("Enter Customer Id");
        String id = scanner.nextLine();
        customer.setId(id);
        System.out.println("Enter Customer Name");
        String name = scanner.nextLine();
        customer.setName(name);
        System.out.println("Enter Customer Username");
        String username = scanner.nextLine();
        customer.setUserName(username);
        System.out.println("Enter Customer Password");
        String password = scanner.nextLine();
        customer.setPassword(password);
        System.out.println("Enter Customer Phone Number");
        Long phone = scanner.nextLong();
        customer.setPhone(phone);
        //System.out.println("Enter Customer Loyalty Program Id");
        //String loyaltyProgramId = scanner.next();
        //customer.setLoyaltyProgramId(loyaltyProgramId);
        System.out.println("Enter Customer Address");
        String address = scanner.nextLine();
        customer.setAddress(address);
        return customer;
    }

    public static void show(String id) {
        System.out.println("1. Show Customer Info\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                Customer customer = CustomerDAO.loadById(id);
                customer.display();
                System.out.println("1. Go Back");
                scanner.nextInt();
            case 2:
                AdminLandingPage.run();
        }

    }

}
