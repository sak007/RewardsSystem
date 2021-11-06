package driver.admin;

import driver.dao.CustomerDAO;
import driver.object.Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run() throws SQLException {
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

    private static Customer getCustomerDetails() {
        Customer customer = new Customer();
        System.out.println("Enter Customer Id");
        String id = scanner.next();
        customer.setId(id);
        System.out.println("Enter Customer Name");
        String name = scanner.next();
        customer.setName(name);
        System.out.println("Enter Customer Phone Number");
        Long phone = scanner.nextLong();
        customer.setPhone(phone);
        //System.out.println("Enter Customer Loyalty Program Id");
        //String loyaltyProgramId = scanner.next();
        //customer.setLoyaltyProgramId(loyaltyProgramId);
        System.out.println("Enter Customer Address");
        String address = scanner.next();
        customer.setAddress(address);
        return customer;
    }

    public static void show(String id) {
        Customer customer = CustomerDAO.loadById(id);
        customer.display();
    }

}
