package driver.object;

import java.util.Date;

public class Wallet {
    private String id;
    private Date dateActivity;
    private int points;
    private String activity;
    private String loyaltyProgramCode;
    private String customerId;
    private String reRuleCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateActivity() {
        return dateActivity;
    }

    public void setDateActivity(Date dateActivity) {
        this.dateActivity = dateActivity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLoyaltyProgramCode() {
        return loyaltyProgramCode;
    }

    public void setLoyaltyProgramCode(String loyaltyProgramCode) {
        this.loyaltyProgramCode = loyaltyProgramCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReRuleCode() {
        return reRuleCode;
    }

    public void setReRuleCode(String reRuleCode) {
        this.reRuleCode = reRuleCode;
    }

    public void display() {
        System.out.println("Customer Wallet Details");
        System.out.println("Customer: " + customerId);
        System.out.println("Activity: " + activity);
        System.out.println("Points: " + points);
        System.out.println("Loyalty Program Id: " + loyaltyProgramCode);
        System.out.println("Date of Activity: " + dateActivity);
    }

}
