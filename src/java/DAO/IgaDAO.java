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
import model.IGA;

/**
 *
 * @author mariellelapidario
 */
public class IgaDAO {

    public boolean EncodeIGA(IGA newIGA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO IGA "
                    + "(codeIGA, description,remarks, dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newIGA.getCodeIGA());
            pstmt.setString(2, newIGA.getDescription());
            pstmt.setString(3, newIGA.getRemarks());
            pstmt.setDate(4, newIGA.getDateMade());
            pstmt.setDate(5, newIGA.getDateUpdated());
            pstmt.setInt(6, newIGA.getContributor());

            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateIGA(IGA newIGA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE  IGA\n"
                    + "SET description = ?, remarks = ?, dateUpdated =?, contributor =?\n"
                    + "WHERE codeIGA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newIGA.getDescription());
            pstmt.setString(2, newIGA.getRemarks());
            pstmt.setDate(3, newIGA.getDateUpdated());
            pstmt.setInt(4, newIGA.getContributor());
            pstmt.setString(5, newIGA.getCodeIGA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<IGA> getAllIGA() throws ParseException {
        ArrayList<IGA> newIGA = new ArrayList<IGA>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeIGA, description, remarks, dateMade, "
                    + "dateUpdated, I.contributor, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'contributorName'\n"
                    + "FROM IGA I\n"
                    + "JOIN USER U\n"
                    + "ON I.contributor = U.userID\n"
                    + "WHERE I.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                IGA temp = new IGA();
                temp.setCodeIGA(rs.getString("codeIGA"));
                temp.setDescription(rs.getString("description"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setDateMade(rs.getDate("dateMade"));
                temp.setDateUpdated(rs.getDate("dateUpdated"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setContributorName(rs.getString("contributorName"));
                newIGA.add(temp);
            }
            pstmt.close();
            conn.close();
            return newIGA;
        } catch (SQLException ex) {
            Logger.getLogger(IgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteIGA(String codeIGA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE IGA\n"
                    + "SET isDELETED = TRUE\n"
                    + "WHERE codeIGA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);            
            pstmt.setString(1, codeIGA);            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLastCodeIGA() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codeIGA) from IGA";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codeIGA)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
