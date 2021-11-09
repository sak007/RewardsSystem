package driver.object;

import driver.brands.ActivityCategory;

public class RERule {
    private String reRuleCode;
    private String activityCategoryCode;
    private String lpCode;
    private Integer numPoints;
    private Integer version;
    private String status;


    public RERule() {
    }

    public RERule(String reRuleCode, String activityCategoryCode, Integer numPoints, Integer version) {
        this.reRuleCode = reRuleCode;
        this.activityCategoryCode = activityCategoryCode;
        this.numPoints = numPoints;
        this.version = version;
    }

    public String getLpCode() {
        return lpCode;
    }

    public String getReRuleCode() {
        return reRuleCode;
    }

    public void setReRuleCode(String reRuleCode) {
        this.reRuleCode = reRuleCode;
    }

    public String getActivityCategoryCode() {
        return activityCategoryCode;
    }

    public void setActivityCategoryCode(String activityCategoryCode) {
        this.activityCategoryCode = activityCategoryCode;
    }

    public Integer getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(Integer numPoints) {
        this.numPoints = numPoints;
    }

    public void setLpCode(String lpCode) {
        this.lpCode = lpCode;
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
        return "(re_rule_code, activity_category_code, lp_code, nums_points, version)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + reRuleCode;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + activityCategoryCode;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + lpCode;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + numPoints;
        res = res + "'";
        res = res + ",";

        res = res + "'";
        res = res + version;
        res = res + "'";
        res = res + ")";
        return res;
    }

}
