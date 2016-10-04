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

    public boolean encodeCO(Assessment newAssessment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO assessment (codeAssessment, course, "
                    + "type, description, weight, status, remarks, dateMade, "
                    + "dateUpdated, contributor, checker)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newAssessment.getCodeAssessment());
            pstmt.setString(2, newAssessment.getCourse());
            pstmt.setInt(3, newAssessment.getType());
            pstmt.setString(4, newAssessment.getDescription());
            pstmt.setDouble(5, newAssessment.getWeight());
            pstmt.setString(6, newAssessment.getStatus());
            pstmt.setString(7, newAssessment.getRemarks());
            pstmt.setDate(8, newAssessment.getDateMade());
            pstmt.setDate(9, newAssessment.getDateUpdated());
            pstmt.setInt(10, newAssessment.getContributor());
            pstmt.setInt(11, newAssessment.getChecker());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAssessment(Assessment newAssessment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE assessment\n"
                    + "SET type = ?, description = ?, weight = ?, status = ?, "
                    + "remarks = ?, dateUpdated = ?, contributor = ?\n"
                    + "WHERE codeAssessment = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newAssessment.getType());
            pstmt.setString(2, newAssessment.getDescription());
            pstmt.setDouble(3, newAssessment.getWeight());
            pstmt.setString(4, newAssessment.getStatus());
            pstmt.setString(5, newAssessment.getRemarks());
            pstmt.setDate(6, newAssessment.getDateUpdated());
            pstmt.setInt(7, newAssessment.getContributor());
            pstmt.setString(8, newAssessment.getCodeAssessment());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Assessment> getAllAssessment(String codeCourse) throws ParseException {
        ArrayList<Assessment> newAssessment = new ArrayList<Assessment>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT A.codeAssessment, A.course, A.type as 'atype', T.type as 'ttype', "
                    + "A.description, A.weight, A.status, remarks, A.contributor, A.checker\n"
                    + "FROM assessment A\n"
                    + "JOIN reftype T\n"
                    + "ON A.type = T.typeID\n"
                    + "WHERE course = ? AND isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCourse);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Assessment temp = new Assessment();
                temp.setCodeAssessment(rs.getString("codeAssessment"));
                temp.setCourse(rs.getString("course"));
                temp.setType(rs.getInt("atype"));
                temp.setTypeName(rs.getString("ttype"));
                temp.setDescription(rs.getString("description"));
                temp.setWeight(rs.getDouble("weight"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setChecker(rs.getInt("checker"));
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

    public boolean deleteAssessment(String codeAssessment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE assessment\n"
                    + "SET isDeleted = TRUE\n"
                    + "WHERE codeAssessment = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeAssessment);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
