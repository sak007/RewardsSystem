package driver.object;

public class RRRule {
    private String rrRuleCode;
    private String reward;
    private Integer numPoints;
    private Integer version;
    private String status;

    public RRRule() {
    }

    public RRRule(String rrRuleCode, String reward, Integer numPoints, Integer version) {
        this.rrRuleCode = rrRuleCode;
        this.reward = reward;
        this.numPoints = numPoints;
        this.version = version;
    }

    public RRRule(String rrRuleCode, String reward, Integer numPoints, Integer version, String status) {
        this.rrRuleCode = rrRuleCode;
        this.reward = reward;
        this.numPoints = numPoints;
        this.version = version;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeta() {
        return "(rr_rule_code, reward, num_points, version, status)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + rrRuleCode;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + reward;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + numPoints;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + version;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + status;
        res = res + "'";
        res = res + ")";
        return res;
    }
}
