package driver.dao;

import driver.object.Brand;
import driver.object.RERule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RERuleDAO {

    public RERuleDAO(){
    }

    public static void saveData(RERule reRule){
        try{
            String insertReRulequery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES "+ reRule.toString();

            boolean insertStatus = DBHelper.executeUpdate(insertReRulequery) > 0;

            if(insertStatus){
                System.out.println("RE Rule added..!!");
            }else{
                System.out.println("There was an issue while inserting RE Rule");
            }

        }catch(SQLException e){
            System.out.println("Unable to add RE Rule!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void updateReRule(RERule reRule,String brandId){
        boolean rowUpdated = false;
        try{
            String selectQuery = "SELECT * FROM re_rule rer WHERE rer.status='E' AND rer.activity_category_code = '"+reRule.getActivityCategoryCode()+ "' and rer.re_rule_code IN (select relp.re_rule_code from re_rule_for_lp relp where relp.lp_code IN (select lp.id from Loyalty_program lp where lp.brand_id IN (select b.id from brand b where b.id = '"+brandId+"')))";
            System.out.println(selectQuery);

            ResultSet rs = DBHelper.executeQuery(selectQuery);

            Integer version = 0;
            String status = "D";
            String ruleCode = "";
            String lpCode = "";

            if(rs.next() == false){
                System.out.println("The entered activity code for your brand does not exists.");
            }else{
                    //get rerule version, set it in rerule object and pass object for update
                    version = rs.getInt("version");
                    ruleCode = rs.getString("re_rule_code");
                    System.out.println("Inner version"+version);
            }

            version = version + 1;
            System.out.println("V:"+version);


            String updateQuery = "UPDATE re_rule SET status = '"+status+"' WHERE activity_category_code = '"+reRule.getActivityCategoryCode()+"' and re_rule_code IN (select relp.re_rule_code from re_rule_for_lp relp where relp.lp_code IN (select lp.id from Loyalty_program lp where lp.brand_id IN (select b.id from brand b where b.id = '"+brandId+"')))";
            System.out.println(updateQuery);

            DBHelper.executeUpdate(updateQuery);

            reRule.setVersion(version);
            reRule.setStatus("E");
            reRule.setReRuleCode(UUID.randomUUID().toString().replace("-",""));

            String insertQuery = "INSERT INTO re_rule"+ reRule.getMeta() +" VALUES"+ reRule.toString();
            System.out.println(insertQuery);

            String rrlpQuery = "select * from re_rule_for_lp where re_rule_code='"+ruleCode+"'";
            System.out.println(rrlpQuery);

            ResultSet rs2 = DBHelper.executeQuery(rrlpQuery);

            if(rs2.next()){
                lpCode = rs2.getString("lp_code");
            }

            String insertLPRR = "insert into re_rule_for_lp values ('"+lpCode+"','"+reRule.getReRuleCode()+"')";
            System.out.println(insertLPRR);

            DBHelper.executeUpdate(insertQuery);

            DBHelper.executeUpdate(insertLPRR);

        } catch (SQLException e) {
            System.out.println("Unable to Update RE Rule !");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            e.printStackTrace();
        }

    }



}
