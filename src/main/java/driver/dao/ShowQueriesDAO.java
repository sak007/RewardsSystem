package driver.dao;

import driver.MainMenu;
import driver.object.Wallet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ShowQueriesDAO {
    public static void run() {
        System.out.println("1. List all customers that are not part of Brand02’s program.\n" +
                "2. List customers that have joined a loyalty program but have not participated in any activity\n" +
                "in that program (list the customerid and the loyalty program id).\n" +
                "3. List the rewards that are part of Brand01 loyalty program.\n" +
                "4. List all the loyalty programs that include “Refer a friend” as an activity in at least one of\n" +
                "their reward rules.\n" +
                "5. For Brand01, list for each activity type in their loyalty program, the number instances that\n" +
                "have occurred.\n" +
                "6. List customers of Brand01 that have redeemed at least twice.\n" +
                "7. All brands where total number of points redeemed overall is less than 500 points\n" +
                "8. For Customer C0003, and Brand02, number of activities they have done in the period of\n" +
                "08/1/2021 and 9/30/2021");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the number corresponding to above queries:");
        int opt=scanner.nextInt();
        switch (opt){
            case 1:runQuery1();
                MainMenu.run();
                break;
            case 2:runQuery2();
                MainMenu.run();
                break;
            case 3:runQuery3();
                MainMenu.run();
                break;
            case 4:runQuery4();
                MainMenu.run();
                break;
            case 5:runQuery5();
                MainMenu.run();
                break;
            case 6:runQuery6();
                MainMenu.run();
                break;
            case 7:runQuery7();
                MainMenu.run();
                break;
            case 8:runQuery8();
                MainMenu.run();
                break;
        }
    }
    public static void  runQuery1(){
        try {
            String query = "select c.id,c.name\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "join loyalty_program lp on cle.loyalty_program_code=lp.id\n" +
                    "join brand b on lp.brand_id=b.id\n" +
                    "minus\n" +
                    "select c.id,c.name\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "join loyalty_program lp on cle.loyalty_program_code=lp.id\n" +
                    "join brand b on lp.brand_id=b.id\n" +
                    "where b.name='Brand02'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Customer ID"+"\t"+"Customer Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery2(){
        try {
            String query = "select c.id,c.name\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "minus\n" +
                    "select c.id,c.name\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "join customer_activity ca on c.id=ca.customer_id\n" +
                    "group by c.id,c.name\n" +
                    "having count(*)>0";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Customer ID"+"\t"+"Customer Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery3(){
        try {
            String query = "select rc.reward_name\n" +
                    "from reward_category rc join rewards_for_loyalty_program rlp on rc.id=rlp.reward_category_code\n" +
                    "join loyalty_program lp on lp.id=rlp.loyalty_program_code\n" +
                    "join brand b on lp.brand_id=b.id\n" +
                    "where b.name='Brand01'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Reward Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery4(){
        try {
            String query = "select DISTINCT lp.program_name\n" +
                    "from loyalty_program lp join re_rule_for_lp relp on lp.id=relp.lp_code\n" +
                    "join re_rule re on re.re_rule_code=relp.re_rule_code\n" +
                    "join activity_category ac on ac.id=re.activity_category_code\n" +
                    "where ac.activity_name='Refer a friend'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Loyalty Program Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery5(){
        try {
            String query = "select ac.activity_name,count(*)\n" +
                    "from brand b join loyalty_program lp on lp.brand_id=b.id\n" +
                    "join customer_lp_enroll cle on cle.loyalty_program_code=lp.id\n" +
                    "join customer_activity ca on ca.customer_id=cle.customer_id\n" +
                    "join activities_for_loyalty_program alp on alp.activity_lp_map_id=ca.activity_lp_map_id\n" +
                    "join activity_category ac on alp.activity_category_code=ac.id\n" +
                    "where b.name='Brand01'\n" +
                    "group by ac.activity_name;";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Activity Name"+"\t"+"Count");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery6(){
        try {
            String query = "select c.id,c.name\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "join loyalty_program lp on cle.loyalty_program_code=lp.id\n" +
                    "join brand b on b.id=lp.brand_id\n" +
                    "where b.name='Brand01'\n" +
                    "group by c.id,c.name\n" +
                    "having 2<= (\n" +
                    "select count(cra.customer_id)\n" +
                    "from customer_redeem_activity cra\n" +
                    "where cra.customer_id=c.id)";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Customer ID"+"\t"+"Customer Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString()+"\t"+object[1].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery7(){
        try {
            String query = "select b.name\n" +
                    "from customer_redeem_activity cre join rewards_for_loyalty_program rlp on cre.redeem_lp_map_id=rlp.reward_lp_map_id\n" +
                    "join loyalty_program lp on lp.id=rlp.loyalty_program_code\n" +
                    "join brand b on b.id=lp.brand_id\n" +
                    "group by b.name\n" +
                    "having sum(cre.points)<=500";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Brand Name");
            for(Object[] object:rs){
                System.out.println(object[0].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void  runQuery8(){
        try {
            String query = "select count(*)\n" +
                    "from customer c join customer_lp_enroll cle on c.id=cle.customer_id\n" +
                    "join loyalty_program lp on cle.loyalty_program_code=lp.id\n" +
                    "join brand b on b.id=lp.brand_id\n" +
                    "join customer_activity ca on ca.customer_id=cle.customer_id\n" +
                    "where c.name='C0003' and b.name='Brand02' and ca.activity_date between '01-AUG-21' and '30-SEP-21'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            System.out.println("Count");
            for(Object[] object:rs){
                System.out.println(object[0].toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Unable to execute query!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
