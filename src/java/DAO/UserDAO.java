/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import database.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author mariellelapidario
 */
public class UserDAO {
    
    public boolean createAccount(User newUser) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO user "
                    + "(firstName, lastName, email, posID, gender, password)\n"
                    + "VALUES (?,?,?,?,?,password(?));";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setInt(4, newUser.getPosID());
            pstmt.setString(5, newUser.getGender());
            pstmt.setString(6, newUser.getPassword());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Authenticate
     *
     * @param User
     * @return
     */
    public boolean authenticate(User User) {
        boolean valid = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "select * from user where email = ? and password = password(?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, User.getEmail());
            pstmt.setString(2, User.getPassword());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                valid = true;
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    /**
     * Setting User
     *
     * @param username
     * @param password
     * @return
     * @throws ParseException
     */
    public User getUser(String username, String password) throws ParseException {
        User User = new User();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select userID, "
                    + "CONCAT(firstName, \" \" , lastName) as 'fullName', "
                    + "email, U.posID, position, gender, password "
                    + "FROM user U "
                    + "JOIN refposition RP "
                    + "ON U.posID = RP.posID "
                    + "WHERE email= ? and password= password(?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                User.setUserID(rs.getInt("userID"));
                User.setFullName(rs.getString("fullName"));
                User.setEmail(rs.getString("email"));
                User.setPosID(rs.getInt("posID"));
                User.setPosition(rs.getString("position"));
                User.setGender(rs.getString("gender"));
                User.setPassword(rs.getString("password"));
            }

            pstmt.close();
            rs.close();
            conn.close();

            return User;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getSpecificUser(String userID) throws ParseException {
        User User = new User();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * "
                    + "FROM user U "
                    + "JOIN refposition RP "
                    + "ON U.posID = RP.posID "
                    + "WHERE userID = ?");
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                User.setUserID(rs.getInt("userID"));
                User.setLastName(rs.getString("lastName"));
                User.setFirstName(rs.getString("firstName"));
                User.setEmail(rs.getString("email"));
                User.setPosition(rs.getString("position"));
                User.setGender(rs.getString("gender"));
                User.setPassword(rs.getString("password"));
            }

            pstmt.close();
            rs.close();
            conn.close();

            return User;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAllUser() throws ParseException {

        ArrayList<User> listUser = new ArrayList<User>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * "
                    + "FROM USER U"
                    + "JOIN refposition RP "
                    + "ON U.posID = RP.posID";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setLastName(rs.getString("lastName"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setEmail(rs.getString("email"));
                temp.setPosition(rs.getString("position"));
                temp.setGender(rs.getString("gender"));
                listUser.add(temp);
            }
            pstmt.close();
            conn.close();
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAllUserByPosition(int position) throws ParseException {

        ArrayList<User> listUser = new ArrayList<User>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * "
                    + "FROM USER "
                    + "WHERE posID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, position);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setLastName(rs.getString("lastName"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setEmail(rs.getString("email"));
                temp.setPosition(rs.getString("position"));
                temp.setGender(rs.getString("gender"));
                listUser.add(temp);
            }
            pstmt.close();
            conn.close();
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<User> getAllFaculty() throws ParseException {

        ArrayList<User> listUser = new ArrayList<User>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT userID, CONCAT(firstName, \" \" , LastName) as 'name'\n"
                    + "FROM user\n"
                    + "where posID = 2;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setFullName(rs.getString("name"));
                listUser.add(temp);
            }
            pstmt.close();
            conn.close();
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<User> getAllPosition() throws ParseException {

        ArrayList<User> listUser = new ArrayList<User>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT posID, position "
                    + "FROM refposition U;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setPosID(rs.getInt("posID"));
                temp.setPosition(rs.getString("position"));
                listUser.add(temp);
            }
            pstmt.close();
            conn.close();
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
