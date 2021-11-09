package driver.dao;

import driver.object.CustomerRedeemActivity;
import driver.object.RewardsForLoyaltyProgram;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerRedeemActivityDAO {
    public static List<CustomerRedeemActivity> loadByCustomerLpIdAndReward(String cId, String lpId, String reward) {
        try {
            String loadCategoryCode = "(Select id from reward_category where reward_name='" + reward + "')";
            String query = "select c.id, c.customer_id, c.activity_date, c.redeem_lp_map_id, c.points, r.reward_value"
                    + " from customer_redeem_activity c, rewards_for_loyalty_program r"
                    + " where c.customer_id = '" + cId + "' and c.redeem_lp_map_id=r.reward_lp_map_id"
                    + " and r.reward_category_code=" + loadCategoryCode;
            List<Object[]> objectList = DBHelper.executeQueryUpdated(query);
            List<CustomerRedeemActivity> customerRedeemActivities = new ArrayList<>();
            objectList.forEach(o -> {
                CustomerRedeemActivity c = new CustomerRedeemActivity();
                c.setId((String)o[0]);
                c.setCustomerId((String)o[1]);
                c.setActivityDate((Timestamp)o[2]);
                c.setRedeemLpMapId((String)o[3]);
                c.setPoints(((BigDecimal)o[4]).longValueExact());
                c.setValue(Long.valueOf((String)o[5]));
                customerRedeemActivities.add(c);
            });
            return customerRedeemActivities;
        } catch (SQLException e) {
            System.out.println("Unable to load customer redeem activity!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
