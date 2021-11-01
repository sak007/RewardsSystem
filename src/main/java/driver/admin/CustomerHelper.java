package driver.admin;

import driver.dao.CustomerDAO;

import java.util.Scanner;

public class CustomerHelper {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = getCustomerDetails();
        System.out.println("1. Add Customer\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                CustomerDAO.saveData(customer);
                System.out.println("Customer Added!");
            case 2:
                LandingPage.run();
                break;
        }

    }

    private static Customer getCustomerDetails() {
        // TODO: 10/29/21  
        Customer customer = new Customer();
        return customer;
    }

    public static void show(Long id) {
        Customer customer = CustomerDAO.loadById(id);
        customer.display();
        // TODO: 10/30/21  
    }

}
