package driver.dao;

import driver.Login;
import driver.MainMenu;
import driver.UserType;
import driver.object.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {

    public static List<User> loadUserByType(String type) throws SQLException {
        try {
            List<User> userList = new ArrayList();
            String query = "select * from actor where role_name = '" + type + "'";
            List<Object[]> resultList = DBHelper.executeQueryUpdated(query);
            resultList.forEach(o -> {
                User usr = new User();
                usr.setName((String)o[0]);
                usr.setPassword((String)o[1]);
                usr.setType((String)o[2]);
                userList.add(usr);
            });
            return userList;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<User> loadUsers() {
        try {
            List<User> userList = new ArrayList();
            String query = "select * from actor";
            List<Object[]> resultList = DBHelper.executeQueryUpdated(query);
            resultList.forEach(o -> {
                User usr = new User();
                usr.setName((String)o[0]);
                usr.setPassword((String)o[1]);
                usr.setType((String)o[2]);
                userList.add(usr);
            });
            return userList;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Boolean validate(String usr, String pwd) {
        try {
            String query = "select * from actor where user_name = '" + usr + "' and password = '" + pwd + "'";
            List<Object[]> resultList = DBHelper.executeQueryUpdated(query);

            if (resultList.size() > 0) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Boolean.FALSE;
        }

    }

    public static UserType getType(String usr) {
        try {
            String query = "select * from actor where user_name = '" + usr + "'";
            List<Object[]> resultList = DBHelper.executeQueryUpdated(query);

            while (resultList.size() > 0) {
                String role = (String)resultList.get(0)[2];
                role = role.toLowerCase();
                switch (role) {
                    case "admin":
                        return UserType.ADMIN;
                    case "brand":
                        return UserType.BRAND;
                    case "customer":
                        return UserType.CUSTOMER;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static void createUser(User usr) {
        try {
            String query = "Insert into actor" + usr.getMeta() + " values" + usr.toString();
            DBHelper.executeUpdate(query);
            System.out.println("User Added!");
            Login.run();
        } catch (SQLException e) {
            MainMenu.run();
            System.out.println("Unable to add User!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static void updatePassword(String userName, String password) throws SQLException {
        String query = "Update actor set password='" + password + "' where user_name='" + userName + "'";
        DBHelper.executeUpdate(query);
    }
}
