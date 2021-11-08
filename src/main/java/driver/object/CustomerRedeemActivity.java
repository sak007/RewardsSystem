package driver.object;

import java.sql.Date;

public class CustomerRedeemActivity {
    private String id;
    private String customerId;
    private Date activityDate;
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

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
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
