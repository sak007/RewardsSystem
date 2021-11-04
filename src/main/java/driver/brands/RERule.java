package driver.brands;

import driver.brands.ActivityCategory;

public class RERule {
    private String reRuleCode;
    private ActivityCategory activityCategoryCode;
    private Integer numPoints;
    private Integer version;

    public RERule() {
    }

    public RERule(String reRuleCode, ActivityCategory activityCategoryCode, Integer numPoints, Integer version) {
        this.reRuleCode = reRuleCode;
        this.activityCategoryCode = activityCategoryCode;
        this.numPoints = numPoints;
        this.version = version;
    }

    public String getReRuleCode() {
        return reRuleCode;
    }

    public void setReRuleCode(String reRuleCode) {
        this.reRuleCode = reRuleCode;
    }

    public ActivityCategory getActivityCategoryCode() {
        return activityCategoryCode;
    }

    public void setActivityCategoryCode(ActivityCategory activityCategoryCode) {
        this.activityCategoryCode = activityCategoryCode;
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
