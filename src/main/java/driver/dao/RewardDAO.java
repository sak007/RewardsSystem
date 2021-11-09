package driver.dao;

import driver.object.Activity;
import driver.object.Reward;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RewardDAO {

    public static void saveData(Reward reward) {
        try {
            String query = "Insert into reward_category" + reward.getMeta() + " values" + reward.toString();
            DBHelper.executeUpdate(query);
            System.out.println("Reward Type Added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Reward Type!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static Reward loadByName(String reward_name) {
        try {
            String query = "Select * from reward_category where reward_name = '" + reward_name + "'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            Reward reward = new Reward();
            for(Object[] object:rs) {
                reward.setCode((String) object[0]);
                reward.setName((String) object[1]);
            }
            return reward;
        } catch (SQLException e) {
            System.out.println("Unable to load Reward from name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static List<Reward> getList() {
        try {
            String query = "Select * from reward_category";
            System.out.println(query);
            List<Object[]> items = DBHelper.executeQueryUpdated(query);

            List<Reward> reward = new ArrayList<>(items.size());
            for(Object[] item:items) {
                Reward rew = new Reward();
                System.out.println("ANSWER: " + (String) item[0] + " and " + (String) item[1]);
                rew.setCode((String) item[0]);
                rew.setName((String) item[1]);
                reward.add(rew);
            }
            return reward;
        } catch (SQLException e) {
            System.out.println("Unable to get the list of available rewards");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static List<List<String>> fetchApplicableRewards(String customerId){
        try {
            List<List<String>> rewards = new ArrayList<>();
            String query = "select rc.reward_name,lp.program_name\n" +
                    "from customer_lp_enroll cle join rewards_for_loyalty_program rlp\n" +
                    "on cle.loyalty_program_code=rlp.loyalty_program_code join reward_category rc\n" +
                    "on rlp.reward_category_code=rc.id join loyalty_program lp on lp.id=rlp.loyalty_program_code\n" +
                    "where cle.customer_id='" + customerId + "'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            for(Object[] object:rs){
                List<String> rewardLP=new ArrayList<>();
                rewardLP.add(object[0].toString());
                rewardLP.add(object[1].toString());
                rewards.add(rewardLP);
            }
            return rewards;
        }
        catch (SQLException e) {
            System.out.println("Unable to fetch Rewards by Customer ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static int fetchAvailablePoints(String customerId, String lpProgramName){
        try {
            int points=0;
            String query = "select points from wallet where customer_id='"+customerId+"' and loyalty_program_code=(select id from loyalty_program where program_name='"+lpProgramName+"')";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            for(Object[] object:rs){
                points=((BigDecimal)object[0]).intValueExact();
            }
            return points;
        }
        catch (SQLException e) {
            System.out.println("Unable to fetch Rewards by Customer ID");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return 0;
        }
    }

    public static List<Integer> fetchActualRewardDetails(String customerId,String rewardName, String lpProgramName){
        try {
            int instances=0,points=0;
            List<Integer> list=new ArrayList<>();
            String query = "select rlp.reward_count,rr.num_points\n" +
                    "from customer_lp_enroll cle join loyalty_program lp on lp.id=cle.loyalty_program_code\n" +
                    "join rr_rule rr on rr.lp_code=lp.id\n" +
                    "join reward_category rc on rr.reward=rc.id  \n" +
                    "join rewards_for_loyalty_program rlp on rc.id=rlp.reward_category_code and rlp.loyalty_program_code=lp.id\n" +
                    "where cle.customer_id='" + customerId + "' and lp.program_name='"+lpProgramName+"' and rc.reward_name='"+rewardName+"' and rr.status='E'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            for(Object[] object:rs){
                instances=((BigDecimal)object[0]).intValueExact();
                points=((BigDecimal)object[1]).intValueExact();
            }
            list.add(instances);list.add(points);
            return list;
        }
        catch (SQLException e) {
            System.out.println("Unable to fetch Actual Rewards by Customer ID, Program name and Reward name");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static int insertRedeemActivity(String customerId,String rewardName, String lpProgramName,int points) {
        try {
            String reward_lp_map_id="";
            String query="select rlp.reward_lp_map_id \n" +
                    "from customer_lp_enroll cle join loyalty_program lp on cle.loyalty_program_code=lp.id\n" +
                    "join rewards_for_loyalty_program rlp on lp.id=rlp.loyalty_program_code \n" +
                    "join reward_category rc on rlp.reward_category_code=rc.id\n" +
                    "where cle.customer_id='" + customerId + "' and lp.program_name='"+lpProgramName+"' and rc.reward_name='"+rewardName+"'";
            List<Object[]> rs = DBHelper.executeQueryUpdated(query);
            for(Object[] object:rs){
                reward_lp_map_id=object[0].toString();
            }
            String id= UUID.randomUUID().toString().replace("-","");
            String insertquery = "insert into customer_redeem_activity values('"+id+"','"+customerId+"',sysdate,'"+reward_lp_map_id+"','"+points+"')";
            return DBHelper.executeUpdate(insertquery);
        } catch (SQLException e) {
            System.out.println("Unable to save redeem activity!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return -1;
        }
    }

}
