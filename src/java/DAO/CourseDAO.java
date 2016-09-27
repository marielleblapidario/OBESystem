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
import model.Course;
import model.MapCourseToProgram;

/**
 *
 * @author mariellelapidario
 */
public class CourseDAO {

    public boolean encodeCourse(Course newCourse) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO course (codeCourse, title, program, "
                    + "units, description, dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCourse.getCodeCourse());
            pstmt.setString(2, newCourse.getTitle());
            pstmt.setString(3, newCourse.getProgram());
            pstmt.setInt(4, newCourse.getUnits());
            pstmt.setString(5, newCourse.getDescription());
            pstmt.setDate(6, newCourse.getDateMade());
            pstmt.setDate(7, newCourse.getDateUpdated());
            pstmt.setInt(8, newCourse.getContributor());

            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean encodeMapCourseToProgram(MapCourseToProgram newMap) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapCourseToProgram (codeCourse, codeProgram)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMap.getCodeCourse());
            pstmt.setString(2, newMap.getCodeProgram());

            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Course> searchCourse(String program) throws ParseException {
        ArrayList<Course> newCourse = new ArrayList<Course>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeCourse, title\n"
                    + "FROM course\n"
                    + "WHERE program = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, program);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCodeCourse(rs.getString("codeCourse"));
                course.setTitle(rs.getString("title"));
                newCourse.add(course);
            }
            pstmt.close();
            conn.close();
            return newCourse;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<Course> getAllCourseTitle() throws ParseException {
        ArrayList<Course> newCourse = new ArrayList<Course>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeCourse, title\n"
                    + "FROM course;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCodeCourse(rs.getString("codeCourse"));
                course.setTitle(rs.getString("title"));
                newCourse.add(course);
            }
            pstmt.close();
            conn.close();
            return newCourse;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCourse(String codeCourse) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE course\n"
                    + "SET isDeleted = TRUE\n"
                    + "WHERE codeCourse = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCourse);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
