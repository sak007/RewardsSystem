package driver.object;

public class ActivitiesForLoyaltyProgram {
        private String activity_lp_map_id;
        private String loyalty_program_code;
        private String activity_category_code;

    public String getActivity_category_code() {
        return activity_category_code;
    }

    public String getId() {
        return activity_lp_map_id;
    }

    public String getLoyalty_program_code() {
        return loyalty_program_code;
    }

    public void setActivity_category_code(String activity_category_code) {
        this.activity_category_code = activity_category_code;
    }

    public void setId(String id) {
        this.activity_lp_map_id = id;
    }

    public void setLoyalty_program_code(String loyalty_program_code) {
        this.loyalty_program_code = loyalty_program_code;
    }

    public String getMeta() {
        return "(activity_lp_map_id, loyalty_program_code, activity_category_code)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + activity_lp_map_id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + loyalty_program_code;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + activity_category_code;
        res = res + "'";
        res = res + ")";
        return res;
    }


}
