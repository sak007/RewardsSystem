package driver.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.Brand;

public class ActivitiesForLoyaltyProgramDAO {
    public static void saveData(ActivitiesForLoyaltyProgram activityLp){
        try {
            String query = "Insert into activities_for_loyalty_program" + activityLp.getMeta() + " values" + activityLp.toString();
            DBHelper.executeUpdate(query);
            System.out.println("The chosen activity has been mapped to the Loyalty Program!");
        } catch (SQLException e) {
            System.out.println("Unable to map activity to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static ActivitiesForLoyaltyProgram loadById(String Id){
        try {
            String query = "Select * from activities_for_loyalty_program where id = '" + Id + "'";
            ResultSet rs = DBHelper.executeQuery(query);
            ActivitiesForLoyaltyProgram activityLp = new ActivitiesForLoyaltyProgram();
            if (rs.next()) {
                activityLp.setId(rs.getString("id"));
                activityLp.setLoyalty_program_code(rs.getString("loyalty_program_code"));
                activityLp.setActivity_category_code(rs.getString("activity_category_code"));
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