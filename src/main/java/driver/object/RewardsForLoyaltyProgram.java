package driver.object;

public class RewardsForLoyaltyProgram {
    private String reward_lp_map_id;
    private String loyalty_program_code;
    private String reward_category_code;

    public String getLoyalty_program_code() {
        return loyalty_program_code;
    }

    public String getReward_lp_map_id() {
        return reward_lp_map_id;
    }

    public String getReward_category_code() {
        return reward_category_code;
    }

    public void setLoyalty_program_code(String loyalty_program_code) {
        this.loyalty_program_code = loyalty_program_code;
    }

    public void setReward_lp_map_id(String reward_lp_map_id) {
        this.reward_lp_map_id = reward_lp_map_id;
    }

    public void setReward_category_code(String reward_category_code) {
        this.reward_category_code = reward_category_code;
    }

    public String getMeta() {
        return "(reward_lp_map_id, loyalty_program_code, reward_category_code)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + reward_lp_map_id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + loyalty_program_code;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + reward_category_code;
        res = res + "'";
        res = res + ")";
        return res;
    }
}
