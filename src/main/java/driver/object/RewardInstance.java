package driver.object;
import java.sql.Date;

public class RewardInstance {
    String reward_id;
    String brand_id;
    int value;
    Date expiryDate;

    //Getter methods
    public Date getExpiryDate() {return expiryDate;}
    public int getValue() {return value;}
    public String getBrand_id() {return brand_id;}
    public String getReward_id() {return reward_id;}

    //Setter methods
    public void setBrand_id(String brand_id) {this.brand_id = brand_id;}
    public void setExpiryDate(Date expiryDate) {this.expiryDate = expiryDate;}
    public void setReward_id(String reward_id) {this.reward_id = reward_id;}
    public void setValue(int value) {this.value = value;}
}

