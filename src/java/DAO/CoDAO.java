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
            String query = "INSERT INTO CO (mapCurID, codePI, syllabusID, curriculumID, "
                    + "courseID, term, codeCO, description, remarks, startYear, endYear)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newCO.getMapCurID());
            pstmt.setString(2, newCO.getCodePI());
            pstmt.setInt(3, newCO.getSyllabusID());
            pstmt.setInt(4, newCO.getCurriculumID());
            pstmt.setInt(5, newCO.getCourseID());
            pstmt.setInt(6, newCO.getTerm());
            pstmt.setString(7, newCO.getCodeCO());
            pstmt.setString(8, newCO.getDescription());
            pstmt.setString(9, newCO.getRemarks());
            pstmt.setInt(10, newCO.getStartYear());
            pstmt.setInt(11, newCO.getEndYear());
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
                    + "SET description = ?, contributor = ?\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND term = ? AND CodeCO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCO.getDescription());
            pstmt.setString(2, newCO.getRemarks());
            pstmt.setInt(3, newCO.getCurriculumID());
            pstmt.setInt(4, newCO.getCourseID());
            pstmt.setInt(5, newCO.getTerm());
            pstmt.setString(6, newCO.getCodeCO());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<CO> getAllCO(int curriculumID, int courseID, int term, int startYear, int endYear) throws ParseException {
        ArrayList<CO> newCO = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT coID, curriculumID, courseID, term, codeCO, "
                    + "description, remarks\n"
                    + "FROM CO\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND term = ? "
                    + "AND startYear = ? AND endYear = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);
            pstmt.setInt(3, term);
            pstmt.setInt(4, startYear);
            pstmt.setInt(5, endYear);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CO temp = new CO();
                temp.setCoID(rs.getInt("coID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setCodeCO(rs.getString("codeCO"));
                temp.setDescription(rs.getString("description"));
                temp.setRemarks(rs.getString("remarks"));
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
            String query = "INSERT INTO mapcotopi (coID, mapCurID, codePI)\n"
                    + "VALUES (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newCO.getCoID());
            pstmt.setInt(2, newCO.getMapCurID());
            pstmt.setString(3, newCO.getCodePI());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<CO> getCOforFormat(int syllabusID) throws ParseException {
        ArrayList<CO> arrCO = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeCO, description\n"
                    + "FROM co \n"
                    + "WHERE syllabusID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CO temp = new CO();
                temp.setCodeCO(rs.getString("codeCO"));
                temp.setDescription(rs.getString("description"));
                arrCO.add(temp);
            }
            pstmt.close();
            conn.close();
            return arrCO;
        } catch (SQLException ex) {
            Logger.getLogger(CoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
