package driver.brands;

public class RewardCategory {
    private String id;
    private String rewardName;
    private String brandId; // should refer to brand class
    private String lpProgramId; // should refer to Loyalty Program Class

    public RewardCategory() {
    }

    public RewardCategory(String id, String rewardName, String brandId, String lpProgramId) {
        this.id = id;
        this.rewardName = rewardName;
        this.brandId = brandId;
        this.lpProgramId = lpProgramId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getLpProgramId() {
        return lpProgramId;
    }

    public void setLpProgramId(String lpProgramId) {
        this.lpProgramId = lpProgramId;
    }
}
