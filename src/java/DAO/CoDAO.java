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
            String query = "INSERT INTO CO (curriculumID, courseID, term, codeCO, "
                    + "description, status, remarks, dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newCO.getCurriculumID());
            pstmt.setInt(2, newCO.getCourseID());
            pstmt.setInt(3, newCO.getTerm());
            pstmt.setString(4, newCO.getCodeCO());
            pstmt.setString(5, newCO.getDescription());
            pstmt.setString(6, newCO.getStatus());
            pstmt.setString(7, newCO.getRemarks());
            pstmt.setDate(8, newCO.getDateMade());
            pstmt.setDate(9, newCO.getDateUpdated());
            pstmt.setInt(10, newCO.getContributor());

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
                    + "SET description = ?, status = ?, remarks = ?, dateUpdated = ?, contributor = ?\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND term = ? AND CodeCO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCO.getDescription());
            pstmt.setString(2, newCO.getStatus());
            pstmt.setString(3, newCO.getRemarks());
            pstmt.setDate(4, newCO.getDateUpdated());
            pstmt.setInt(5, newCO.getContributor());
            pstmt.setInt(6, newCO.getCurriculumID());
            pstmt.setInt(7, newCO.getCourseID());
            pstmt.setInt(8, newCO.getTerm());
            pstmt.setString(9, newCO.getCodeCO());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<CO> getAllCO(int curriculumID, int courseID, int term) throws ParseException {
        ArrayList<CO> newCO = new ArrayList<CO>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT curriculumID, courseID, term, codeCO, "
                    + "description, status, remarks, contributor\n"
                    + "FROM CO\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND term = ? AND isDelete IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);
            pstmt.setInt(3, term);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CO temp = new CO();
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setCodeCO(rs.getString("codeCO"));
                temp.setDescription(rs.getString("description"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setContributor(rs.getInt("contributor"));
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

    public boolean deleteCO(int curriculumID, int courseID, int term, String codeCO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE CO\n"
                    + "SET isDELETED = TRUE\n"
                    + "WHERE curriculumID = ? AND courseID = ? "
                    + "AND term = ? AND codeCO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);
            pstmt.setInt(3, term);
            pstmt.setString(4, codeCO);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLastCodeCO(int curriculumID, int courseID, int term) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codeCO)\n"
                + "FROM CO\n"
                + "WHERE curriculumID = ? AND courseID = ? AND term = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, curriculumID);
        ps.setInt(2, courseID);
        ps.setInt(3, term);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codeCO)");
        }
        ps.close();
        rs.close();
        return i;
    }

    public boolean mapCOtoPI(CO newCO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapcotopi (curriculumID, courseID, codePI, term, codeCO)\n"
                    + "VALUES (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newCO.getCurriculumID());
            pstmt.setInt(2, newCO.getCourseID());
            pstmt.setString(3, newCO.getCodePI());
            pstmt.setInt(4, newCO.getTerm());
            pstmt.setString(5, newCO.getCodeCO());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
