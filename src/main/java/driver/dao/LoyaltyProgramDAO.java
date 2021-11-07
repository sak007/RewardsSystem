package driver.dao;

import driver.object.Activity;
import driver.object.LoyaltyProgram;
import driver.object.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LoyaltyProgramDAO {
    public static void saveData(LoyaltyProgram loyaltyProg) {
        try {
            String query = "Insert into Loyalty_program" + loyaltyProg.getMeta() + " values" + loyaltyProg.toString();
            System.out.println(query);
            DBHelper.executeUpdate(query);
            System.out.println("Inserted\n");

            System.out.println("Loyalty Program  Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }


    public static LoyaltyProgram loadByBrandId(String brand_id) {
        try {
            String query = "Select * from Loyalty_program where brand_id = '" + brand_id + "'";
            System.out.println(query);
            List<Object[]> items = DBHelper.executeQueryUpdated(query);
            LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
            for(Object[] item:items) {
                loyaltyProgram.setLpId((String) item[0]);
                loyaltyProgram.setProgramName((String) item[1]);
                loyaltyProgram.setBrandId((String) item[2]);
                loyaltyProgram.setTierType((String) item[3]);
                loyaltyProgram.setState((String) item[4]);
            }

            return loyaltyProgram;
        } catch (SQLException e) {
            System.out.println("Unable to load Loyalty Program from ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
    public static Map<String,String> getLoyaltyProgramList(){
        Map<String,String> programNames=new HashMap<>();
        String query = "Select * from loyalty_program where state='ACTIVE'";
        try {
            List<Object[]> rs= DBHelper.executeQueryUpdated(query);
            for(Object[] object:rs){
                programNames.put(object[0].toString(),object[1].toString());
            }
            return programNames;
        }
        catch (SQLException e){
            System.out.println("Unable to load Loyalty Program Names");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static List<LoyaltyProgram> getActiveLoyaltyPrograms() {
        try {
            String query = "Select * from loyalty_program where state='ACTIVE'";
            List<LoyaltyProgram> loyaltyProgramList = new ArrayList<>();
            List<Object[]> list = DBHelper.executeQueryUpdated(query);
            list.stream().forEach(l -> {
                LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
                loyaltyProgram.setLpId((String) l[0]);
                loyaltyProgram.setProgramName((String) l[1]);
                loyaltyProgram.setBrandId((String) l[2]);
                loyaltyProgram.setTierType((String) l[3]);
                loyaltyProgram.setState((String) l[4]);
                loyaltyProgramList.add(loyaltyProgram);
            });
            return loyaltyProgramList;
        } catch (SQLException e){
            System.out.println("Unable to load Loyalty Program");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<LoyaltyProgram> getActiveLoyaltyProgramsForCustomer(String customerId) {
        try {
            String loadLpIdForCustomer = "Select loyalty_program_code from customer_lp_enroll where customer_id='" + customerId + "'";
            String query = "Select * from loyalty_program where state='ACTIVE' and id in (" + loadLpIdForCustomer + ")";
            List<LoyaltyProgram> loyaltyProgramList = new ArrayList<>();
            List<Object[]> list = DBHelper.executeQueryUpdated(query);
            list.stream().forEach(l -> {
                LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
                loyaltyProgram.setLpId((String) l[0]);
                loyaltyProgram.setProgramName((String) l[1]);
                loyaltyProgram.setBrandId((String) l[2]);
                loyaltyProgram.setTierType((String) l[3]);
                loyaltyProgram.setState((String) l[4]);
                loyaltyProgramList.add(loyaltyProgram);
            });
            return loyaltyProgramList;
        } catch (SQLException e){
            System.out.println("Unable to load Loyalty Program");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

}