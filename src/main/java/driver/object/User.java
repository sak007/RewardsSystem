package driver.object;

public class User {
    private String name;
    private String password;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeta() {
        return "(user_name, password, role_name)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + name;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + password;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + type;
        res = res + "'";
        res = res + ")";
        return res;
    }
}
