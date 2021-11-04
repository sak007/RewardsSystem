package driver.brands;

import java.sql.Date;

public class RewardInstance {
    private String rewardId;
    private String brandId; // will have to refer to class Brand
    private Integer value;
    private Date expiryDate;

    public RewardInstance() {
    }

    public RewardInstance(String rewardId, String brandId, Integer value, Date expiryDate) {
        this.rewardId = rewardId;
        this.brandId = brandId;
        this.value = value;
        this.expiryDate = expiryDate;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
