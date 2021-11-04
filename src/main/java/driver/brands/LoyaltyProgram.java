package driver.brands;

public class LoyaltyProgram {
    private int lpId;
    private String programName;
    private int activityCode; // References activity category's ID
    private int brandId; // References brand's ID
    private String tierType;
    private String state;
    private String reRuleCode; // References reRuleCode's rerulecode
    private String rrRuleCode; // References rrRulesCode's rrrulecode
}
