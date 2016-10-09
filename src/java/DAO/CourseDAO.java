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
            String query = "INSERT INTO course (codeCourse, title, "
                    + "units, description, dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCourse.getCodeCourse());
            pstmt.setString(2, newCourse.getTitle());
            pstmt.setInt(3, newCourse.getUnits());
            pstmt.setString(4, newCourse.getDescription());
            pstmt.setDate(5, newCourse.getDateMade());
            pstmt.setDate(6, newCourse.getDateUpdated());
            pstmt.setInt(7, newCourse.getContributor());

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
            String query = "INSERT INTO mapCourseToProgram (courseID, codeProgram)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMap.getCourseID());
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
            String query = "SELECT C.courseID, C.codeCourse, C.title, C.units\n"
                    + "FROM course C\n"
                    + "JOIN mapcoursetoprogram M\n"
                    + "ON C.courseID = M.courseID\n"
                    + "WHERE M.codeProgram = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, program);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCodeCourse(rs.getString("codeCourse"));
                course.setTitle(rs.getString("title"));
                course.setUnits(rs.getInt("units"));
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

    public Course getSpecificCourse(int courseID) throws ParseException {
        Course course = null;

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT courseID, codeCourse, title, units\n"
                    + "FROM course\n"
                    + "WHERE courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCodeCourse(rs.getString("codeCourse"));
                course.setTitle(rs.getString("title"));
                course.setUnits(rs.getInt("units"));
            }
            pstmt.close();
            conn.close();
            return course;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Course getSpecificCourse(String codeCourse) throws ParseException {
        Course course = null;

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT courseID, codeCourse, title, units\n"
                    + "FROM course\n"
                    + "WHERE codeCourse = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCourse);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCodeCourse(rs.getString("codeCourse"));
                course.setTitle(rs.getString("title"));
                course.setUnits(rs.getInt("units"));
            }
            pstmt.close();
            conn.close();
            return course;
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
            String query = "SELECT courseID, codeCourse, title\n"
                    + "FROM course;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
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

    public ArrayList<Course> getAllCourse() throws ParseException {

        ArrayList<Course> newCourse = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT courseID, codeCourse, title, C.contributor, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM course C\n"
                    + "JOIN user U\n"
                    + "on C.contributor = U.userID\n"
                    + "WHERE C.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course temp = new Course();
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setTitle(rs.getString("title"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setContributorName(rs.getString("name"));
                newCourse.add(temp);
            }
            pstmt.close();
            conn.close();
            return newCourse;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCourse(int courseID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE course\n"
                    + "SET isDeleted = TRUE\n"
                    + "WHERE courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseID);
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
