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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author mariellelapidario
 */
public class StudentDAO {

    public ArrayList<String> getColumnNames() {
        try {
            ArrayList<String> columnNames = new ArrayList<>();

            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT studentID, firstName, lastName FROM student";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i);
                columnNames.add(name);
            }
            pstmt.close();
            conn.close();
            return columnNames;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean encodeEnrolledStudent(Student newStudent) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO enrolledStudent (studentID, offeringID)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newStudent.getStudentID());
            pstmt.setInt(2, newStudent.getOfferingID());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteEnrolledStudents(int offeringID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "DELETE FROM enrolledStudent\n"
                    + "WHERE offeringID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, offeringID);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Student> getEnrolledStudents(int offeringID) throws ParseException {
        ArrayList<Student> arrStudent = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT ES.studentID, S.firstName, S.lastName, S.middleName\n"
                    + "FROM enrolledstudent ES \n"
                    + "JOIN student S \n"
                    + "ON ES.studentID = S.studentID\n"
                    + "WHERE ES.offeringID = ?\n"
                    + "ORDER BY S.lastName ASC, S.firstName ASC, S.middleName ASC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, offeringID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setMiddleName(rs.getString("middleName"));
                arrStudent.add(student);
            }
            pstmt.close();
            conn.close();
            return arrStudent;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
