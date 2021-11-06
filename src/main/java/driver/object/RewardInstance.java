package driver.object;
import java.sql.Date;

public class RewardInstance {
    String instance_id;
    String reward_id;
    String brand_id;
    String value;
    Date expiryDate;

    //Getter methods

    public String getInstance_id() {return instance_id;}
    public Date getExpiryDate() {return expiryDate;}
    public String getValue() {return value;}
    public String getBrand_id() {return brand_id;}
    public String getReward_id() {return reward_id;}

    //Setter methods
    public void setInstance_id(String instance_id) {this.instance_id = instance_id;}
    public void setBrand_id(String brand_id) {this.brand_id = brand_id;}
    public void setExpiryDate(Date expiryDate) {this.expiryDate = expiryDate;}
    public void setReward_id(String reward_id) {this.reward_id = reward_id;}
    public void setValue(String value) {this.value = value;}

    public String getMeta() {return "(instance_id, reward_id, brand_id, value)";}

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + instance_id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + reward_id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + brand_id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + value;
        res = res + "'";
        res = res + ")";
        return res;
    }
}

