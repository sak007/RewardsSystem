package driver.dao;

import driver.UserType;
import driver.object.User;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class UserDAO {

    public static List<User> loadUserByType(String type) throws SQLException {
        try {
            List<User> userList = new ArrayList();
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String query = "select * from actor where role_name = '" + type + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                User usr = new User();
                String role = rs.getString("role_name");
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                usr.setName(name);
                usr.setPassword(password);
                usr.setType(role);
                userList.add(usr);
            }
            return userList;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<User> loadUsers() {
        try {
            List<User> userList = new ArrayList();
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String query = "select * from actor";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                User usr = new User();
                String role = rs.getString("role_name");
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                usr.setName(name);
                usr.setPassword(password);
                usr.setType(role);
                userList.add(usr);
            }
            return userList;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Boolean validate(String usr, String pwd) {
        try {
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String query = "select * from actor where user_name = '" + usr + "' and password = '" + pwd + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }

    }

    public static UserType getType(String usr) {
        try {
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String query = "select * from actor where user_name = '" + usr + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String role = rs.getString("role_name");
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

}