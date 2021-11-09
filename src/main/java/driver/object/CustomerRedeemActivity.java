package driver.object;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomerRedeemActivity {
    private String id;
    private String customerId;
    private Timestamp activityDate;
    private String redeemLpMapId;
    private Long points;
    private Long value;

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

    public Timestamp getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Timestamp activityDate) {
        this.activityDate = activityDate;
    }

    public String getRedeemLpMapId() {
        return redeemLpMapId;
    }

    public void setRedeemLpMapId(String redeemLpMapId) {
        this.redeemLpMapId = redeemLpMapId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
