package driver.dao;

import driver.admin.Brand;

public class BrandDAO {
    public static void saveData(Brand brand) {
        // TODO: 10/29/21  
        //save brand data in db
    }

    public static Brand loadById(Long id) {
        // TODO: 10/29/21
        // load brand details from table
        Brand brand = new Brand();
        brand.setId(id);
        return brand;
    }
}
