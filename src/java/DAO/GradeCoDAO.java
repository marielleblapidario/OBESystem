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
import model.GradeCO;

/**
 *
 * @author mariellelapidario
 */
public class GradeCoDAO {
    public boolean encodeGrade(GradeCO newStudent) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO gradeco (studentID, offeringID, coID, gradeCO, contributor)\n"
                    + "VALUES (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newStudent.getStudentID());
            pstmt.setInt(2, newStudent.getOfferingID());
            pstmt.setInt(3, newStudent.getCoID());
            pstmt.setDouble(4, newStudent.getGradeCO());
            pstmt.setInt(5, newStudent.getContributor());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GradeCoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteGrades(int offeringID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "DELETE FROM gradeco\n"
                    + "WHERE offeringID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, offeringID);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GradeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<GradeCO> getAllCOGradesOfSection(int offeringID) throws ParseException {
        ArrayList<GradeCO> arrGrade = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT GC.studentID, S.lastName, S.firstName, S.middleName, "
                    + "C.codeCO, GC.gradeCO\n"
                    + "FROM gradeco GC\n"
                    + "JOIN co C \n"
                    + "ON GC.coID = C.coID \n"
                    + "JOIN student S \n"
                    + "ON GC.studentID = S.studentID\n"
                    + "WHERE offeringID = ?\n"
                    + "ORDER BY S.lastName ASC, S.firstName ASC, S.middleName ASC, C.codeCO ASC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, offeringID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                GradeCO grade = new GradeCO();
                grade.setStudentID(rs.getInt("studentID"));
                grade.setLastName(rs.getString("lastName"));
                grade.setFirstName(rs.getString("firstName"));
                grade.setMiddleName(rs.getString("middleName"));
                grade.setCodeCO(rs.getString("codeCO"));
                grade.setGradeCO(rs.getDouble("gradeCO"));
                arrGrade.add(grade);
            }
            pstmt.close();
            conn.close();
            return arrGrade;
        } catch (SQLException ex) {
            Logger.getLogger(GradeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
