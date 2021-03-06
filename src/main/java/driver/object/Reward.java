package driver.object;

public class Reward {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void display() {
        System.out.println("Reward Details");
        System.out.println("Code: " + code);
        System.out.println("Name: " + name);
    }

    public String getMeta() {
        return "(id, reward_name)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + code;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + name;
        res = res + "'";
        res = res + ")";
        return res;
    }

}
