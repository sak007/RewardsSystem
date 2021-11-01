package driver.admin;

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
        System.out.println("Reward Name: " + name);
        System.out.println("Reward Code: " + code);
    }

}
