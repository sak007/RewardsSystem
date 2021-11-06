package driver.object;

import java.util.Date;

public class Brand {
    private String id;
    private String name;
    private String username;
    private String address;
    private String joinDate;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void display() {
        System.out.println("Brand Details");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Join Date: " + joinDate);
        System.out.println("Address: " + address);
    }

    public String getMeta() {
        return "(id, name, address, user_name)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + name;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + address;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + username;
        res = res + "'";
        res = res + ")";
        return res;
    }
}
