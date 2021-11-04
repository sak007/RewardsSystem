package driver.brands;

public class RRRule {
    private String rrRuleCode;
    private String reward;
    private Integer numPoints;
    private Integer version;

    public RRRule() {
    }

    public RRRule(String rrRuleCode, String reward, Integer numPoints, Integer version) {
        this.rrRuleCode = rrRuleCode;
        this.reward = reward;
        this.numPoints = numPoints;
        this.version = version;
    }

    public String getRrRuleCode() {
        return rrRuleCode;
    }

    public void setRrRuleCode(String rrRuleCode) {
        this.rrRuleCode = rrRuleCode;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Integer getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(Integer numPoints) {
        this.numPoints = numPoints;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
