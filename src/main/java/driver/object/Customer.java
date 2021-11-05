package driver.object;

public class Customer {
    private String id;
    private String name;
    private Long phone;
    private String loyaltyProgramId;
    private String address;

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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String setLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(String loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void display() {
        System.out.println("Customer Details");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Loyalty Program Id: " + loyaltyProgramId);
        System.out.println("Address: " + address);
    }

    public String getMeta() {
        return "(id, name, phone, address)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + id.toString();
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + name;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + phone.toString();
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + address;
        res = res + "'";
        res = res + ")";
        return res;
    }

}
