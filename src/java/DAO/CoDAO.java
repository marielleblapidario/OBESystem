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
import model.CO;

/**
 *
 * @author mariellelapidario
 */
public class CoDAO {

    public boolean encodeCO(CO newCO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO CO (codeCO, course, description, weight, "
                    + "status, remarks, dateMade, dateUpdated, contributor, checker)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCO.getCodeCO());
            pstmt.setString(2, newCO.getCourse());
            pstmt.setString(3, newCO.getDescription());
            pstmt.setDouble(4, newCO.getWeight());
            pstmt.setString(5, newCO.getStatus());
            pstmt.setString(6, newCO.getRemarks());
            pstmt.setDate(7, newCO.getDateMade());
            pstmt.setDate(8, newCO.getDateUpdated());
            pstmt.setInt(9, newCO.getContributor());
            pstmt.setInt(10, newCO.getChecker());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCO(CO newCO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE CO\n"
                    + "SET description = ?, weight = ?, status = ?, remarks = ?, "
                    + "dateUpdated = ?, contributor = ?\n"
                    + "WHERE codeCO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCO.getDescription());
            pstmt.setDouble(2, newCO.getWeight());
            pstmt.setString(3, newCO.getStatus());
            pstmt.setString(4, newCO.getRemarks());
            pstmt.setDate(5, newCO.getDateUpdated());
            pstmt.setInt(6, newCO.getContributor());
            pstmt.setString(7, newCO.getCodeCO());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<CO> getAllCO(String codeCourse) throws ParseException {
        ArrayList<CO> newCO = new ArrayList<CO>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeCO, course, description, weight, status, "
                    + "remarks, contributor, checker\n"
                    + "FROM CO\n"
                    + "WHERE course = ? AND isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCourse);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CO temp = new CO();
                temp.setCodeCO(rs.getString("codeCO"));
                temp.setCourse(rs.getString("course"));
                temp.setDescription(rs.getString("description"));
                temp.setWeight(rs.getDouble("weight"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setChecker(rs.getInt("checker"));
                newCO.add(temp);
            }
            pstmt.close();
            conn.close();
            return newCO;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCO(String codeCO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE CO\n"
                    + "SET isDELETED = TRUE\n"
                    + "WHERE codeCO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCO);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLastCodeCO(String codeCourse) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codeCO)\n"
                + "FROM CO\n"
                + "WHERE course = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, codeCourse);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codeCO)");
        }
        ps.close();
        rs.close();
        return i;
    }

}
