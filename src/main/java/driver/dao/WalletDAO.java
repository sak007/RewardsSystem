package driver.dao;

import java.sql.SQLException;
import java.util.List;

public class WalletDAO {
    public static void showDetailsByCustomerId(String customerId) {

        try {
            String query = "select c.name,c.phone,c.address,w.points,w.loyalty_program_code,lp.program_name\n" +
                    "from customer c join wallet w on c.id=w.customer_id\n" +
                    "join loyalty_program lp on w.loyalty_program_code=lp.id\n" +
                    "where c.id='" + customerId + "'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Customer Name"+"\t"+"Phone"+"\t"+"Address"+"\t"+"\t"+"\t"+"Wallet Points"+"\t"+"LP Code"+"\t"+"LP Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString()+"\t"+object[2].toString()+"\t"+object[3].toString()+"\t"+object[4].toString()+"\t"+object[5].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to print Customer wallet details!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void showActivityDetailsByCustomerId(String customerId) {

        try {
            String query = "select b.id,b.name,ac.activity_name,sum(ca.points),ca.activity_date \n" +
                    "from customer_activity ca join activities_for_loyalty_program alp on ca.activity_lp_map_id=alp.activity_lp_map_id \n" +
                    "join loyalty_program lp on lp.id=alp.loyalty_program_code \n" +
                    "join brand b on b.id=lp.brand_id \n" +
                    "join activity_category ac on ac.id=alp.activity_category_code \n" +
                    "where ca.customer_id='"+customerId+"'\n" +
                    "group by b.id,b.name,ac.activity_name,ca.activity_date \n" +
                    "order by ca.activity_date";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Brand ID"+"\t"+"Brand Name"+"\t"+"Activity Name"+"\t"+"Points Gained"+"\t"+"Date of Activity");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString()+"\t"+object[2].toString()+"\t"+object[3].toString()+"\t"+object[4].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to print Customer activity details!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void showRedeemDetailsByCustomerId(String customerId) {

        try {
            String query = "select b.id,b.name,rc.reward_name,sum(cra.points),cra.activity_date \n" +
                    "from customer_redeem_activity cra join rewards_for_loyalty_program rlp on cra.redeem_lp_map_id=rlp.reward_lp_map_id \n" +
                    "join loyalty_program lp on lp.id=rlp.loyalty_program_code \n" +
                    "join brand b on b.id=lp.brand_id \n" +
                    "join reward_category rc on rc.id=rlp.reward_category_code \n" +
                    "where cra.customer_id='"+customerId+"'\n" +
                    "group by b.id,b.name,rc.reward_name,cra.activity_date\n" +
                    "order by cra.activity_date";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Brand ID"+"\t"+"Brand Name"+"\t"+"Reward Name"+"\t"+"Points Redeemed"+"\t"+"Date of Activity");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString()+"\t"+object[2].toString()+"\t"+object[3].toString()+"\t"+object[4].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to print Customer redeem details!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

}
