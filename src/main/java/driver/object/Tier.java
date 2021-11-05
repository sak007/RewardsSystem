package driver.object;

public class Tier {
    String Id;
    String name;
    Integer points;
    Integer multiplier;
    String lp_program_id;

    //Getter methods
    public String getId() {return Id;}
    public Integer getMultiplier() {return multiplier;}
    public Integer getPoints() {return points;}
    public String getLp_program_id() {return lp_program_id;}
    public String getName() {return name;}

    //Setter methods
    public void setId(String id) {Id = id;}
    public void setLp_program_id(String lp_program_id) {this.lp_program_id = lp_program_id;}
    public void setMultiplier(Integer multiplier) {this.multiplier = multiplier;}
    public void setName(String name) {this.name = name;}
    public void setPoints(Integer points) {this.points = points;}

    public String getMeta() {
        return "(id, name, points, multiplier, lp_program_id)";
    }

    public String toString() {
        String res = "(";
        res = res + "'";
        res = res + Id;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + name;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + points;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + multiplier;
        res = res + "'";
        res = res + ",";
        res = res + "'";
        res = res + lp_program_id;
        res = res + "'";
        res = res + ")";
        return res;
    }

}