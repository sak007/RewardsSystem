package driver.dao;

import driver.admin.Customer;

public class CustomerDAO {

    public static void saveData(Customer brand) {
        // TODO: 10/29/21
        //save customer data in db
    }

    public static Customer loadById(Long id) {
        // TODO: 10/29/21
        // load customer details from table
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
