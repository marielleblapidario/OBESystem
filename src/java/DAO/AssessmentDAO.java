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
import model.Assessment;

/**
 *
 * @author mariellelapidario
 */
public class AssessmentDAO {

    public boolean encodeAssessment(Assessment newAssessment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO assessment (coID, syllabusID, mapCurID, curriculumID, courseID, term, "
                    + "startYear, endYear, codeAT, type, description, weight)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newAssessment.getCoID());
            pstmt.setInt(2, newAssessment.getSyllabusID());
            pstmt.setInt(3, newAssessment.getMapCurID());
            pstmt.setInt(4, newAssessment.getCurriculumID());
            pstmt.setInt(5, newAssessment.getCourseID());
            pstmt.setInt(6, newAssessment.getTerm());
            pstmt.setInt(7, newAssessment.getStartYear());
            pstmt.setInt(8, newAssessment.getEndYear());
            pstmt.setString(9, newAssessment.getCodeAT());
            pstmt.setInt(10, newAssessment.getType());
            pstmt.setString(11, newAssessment.getDescription());
            pstmt.setDouble(12, newAssessment.getWeight());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public boolean updateAssessment(Assessment newAssessment) {
//        try {
//            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
//            Connection conn = myFactory.getConnection();
//            String query = "UPDATE assessment\n"
//                    + "SET type = ?, description = ?, weight = ?, status = ?, "
//                    + "remarks = ?, dateUpdated = ?, contributor = ?\n"
//                    + "WHERE codeAssessment = ?";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            pstmt.setInt(1, newAssessment.getType());
//            pstmt.setString(2, newAssessment.getDescription());
//            pstmt.setDouble(3, newAssessment.getWeight());
//            pstmt.setString(4, newAssessment.getStatus());
//            pstmt.setString(5, newAssessment.getRemarks());
//            pstmt.setDate(6, newAssessment.getDateUpdated());
//            pstmt.setInt(7, newAssessment.getContributor());
//            pstmt.setString(8, newAssessment.getCodeAssessment());
//
//            pstmt.executeUpdate();
//            pstmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public ArrayList<Assessment> getAllAssessment(int curriculumID, int courseID, int term, String section) throws ParseException {
        ArrayList<Assessment> newAssessment = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT assessmentID, curriculumID, courseID, term, "
                    + "codeAT, title, description, weight\n"
                    + "FROM assessment\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND term = ? AND section = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);
            pstmt.setInt(3, term);
            pstmt.setString(4, section);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Assessment temp = new Assessment();
                temp.setAssessmentID(rs.getInt("assessmentID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setCodeAT(rs.getString("codeAT"));
                temp.setTitle(rs.getString("title"));
                temp.setDescription(rs.getString("description"));
                temp.setWeight(rs.getDouble("weight"));
                newAssessment.add(temp);
            }
            pstmt.close();
            conn.close();
            return newAssessment;
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public boolean mapAToOffering(Assessment newOffering) {
//        try {
//            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
//            Connection conn = myFactory.getConnection();
//            String query = "INSERT INTO mapassessmenttooffering (offeringID, assessmentID)\n"
//                    + "VALUES (?,?);";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            pstmt.setInt(1, newOffering.getOfferingID());
//            pstmt.setInt(2, newOffering.getAssessmentID());
//
//            pstmt.executeUpdate();
//            pstmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    public boolean deleteAssessment(String codeAssessment) {
//        try {
//            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
//            Connection conn = myFactory.getConnection();
//            String query = "UPDATE assessment\n"
//                    + "SET isDeleted = TRUE\n"
//                    + "WHERE codeAssessment = ?;";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, codeAssessment);
//            pstmt.executeUpdate();
//            pstmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public ArrayList<String> getAssessmentforFormat(int syllabusID) throws ParseException {
        ArrayList<String> arrAssessment = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeAT\n"
                    + "FROM assessment\n"
                    + "WHERE syllabusID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String assessment = rs.getString("codeAT");
                arrAssessment.add(assessment);
            }
            pstmt.close();
            conn.close();
            return arrAssessment;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getLastCodeAssessment(String codeCourse) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codeAssessment)\n"
                + "FROM assessment\n"
                + "WHERE course = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, codeCourse);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codeAssessment)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
