package driver.object;

import java.sql.Date;
import java.util.UUID;

public class CustomerActivity {
    private String id;
    private String customerId;
    private Date activityDate;
    private String activityLpId;
    private String customerRedeemActivityId;
    private Long points;

    public CustomerActivity() {
        id = UUID.randomUUID().toString().replace("-","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityLpId() {
        return activityLpId;
    }

    public void setActivityLpId(String activityLpId) {
        this.activityLpId = activityLpId;
    }

    public String getCustomerRedeemActivityId() {
        return customerRedeemActivityId;
    }

    public void setCustomerRedeemActivityId(String customerRedeemActivityId) {
        this.customerRedeemActivityId = customerRedeemActivityId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getMeta() {
        return "(id, customer_id, activity_lp_map_id, customer_redeem_activity_id, points)";
    }

    public String toString() {
        String res = "(";
        if (id != null) {
            res = res + "'" + id + "'";
        } else {
            res = res + id;
        }
        res = res + ",";
        if (customerId != null) {
            res = res + "'" + customerId + "'";
        } else {
            res = res + customerId;
        }
        res = res + ",";
        if (activityLpId != null) {
            res = res + "'" + activityLpId + "'";
        } else {
            res = res + activityLpId;
        }
        res = res + ",";
        if (customerRedeemActivityId != null) {
            res = res + "'" + customerRedeemActivityId + "'";
        } else {
            res = res + customerRedeemActivityId;
        }
        res = res + ",";
        if (points != null) {
            res = res + "'" + points + "'";
        } else {
            res = res + points;
        }
        res = res + ")";
        return res;
    }

}
