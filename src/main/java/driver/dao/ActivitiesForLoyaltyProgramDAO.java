package driver.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.Brand;

public class ActivitiesForLoyaltyProgramDAO {
    public static void saveData(ActivitiesForLoyaltyProgram activityLp){
        try {
            String query = "Insert into activities_for_loyalty_program" + activityLp.getMeta() + " values" + activityLp.toString();
            System.out.print(query);
            DBHelper.executeUpdate(query);
            System.out.println("The chosen activity has been mapped to the Loyalty Program!");
        } catch (SQLException e) {
            System.out.println("Unable to map activity to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static ActivitiesForLoyaltyProgram loadById(String Id){
        try {
            int i;
            String query = "Select * from activities_for_loyalty_program where id = '" + Id + "'";
            List<Object[]> rs =  DBHelper.executeQueryUpdated(query);

            ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();

            for(Object[] object:rs){
                activityLp.setId((String)object[0]);
                activityLp.setLoyalty_program_code((String)object[1]);
                activityLp.setActivity_category_code((String)object[2]);
            }

            return activityLp;
        }
        catch (SQLException e) {
            System.out.println("Unable to load Brand");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

}
