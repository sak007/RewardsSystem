package driver.object;

import java.util.Date;

public class Brand {
    private String id;
    private String name;
    private String address;
    private String joinDate;

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

    public void display() {
        System.out.println("Brand Details");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Join Date: " + joinDate);
        System.out.println("Address: " + address);
    }

    public String getMeta() {
        return "(id, name, address)";
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
        res = res + ")";
        return res;
    }
}
