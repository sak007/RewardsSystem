package driver.object;

public class LoyaltyProgram {
    private String lpId;
    private String programName;
//    private int activityCode; // References activity category's ID
    private String brandId; // References brand's ID
    private String tierType;
    private String state;

    //Get commands
    public String getLpId() {return lpId;}
    public String getProgramName() {return programName;}
//    public int getActivityCode(){ return activityCode;}
    public String getBrandId() {return brandId;}
    public String getTierType() {return tierType;}
    public String getState() {return state;}


    //Set commands
//    public void setActivityCode(int activityCode) {this.activityCode = activityCode;}

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setLpId(String lpId) {this.lpId = lpId;}
    public void setProgramName(String programName) {this.programName = programName;}
    public void setState(String state) {this.state = state;}
    public void setTierType(String tierType) {this.tierType = tierType;}


    public String getMeta() {
        return "(id, program_name, brand_id, tier_type, state)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + lpId;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + programName;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + brandId;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + tierType;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + state;
        res = res + "'";
        res = res + ")";
        return res;
    }
}
