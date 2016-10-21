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
import model.CourseOffering;

/**
 *
 * @author mariellelapidario
 */
public class CourseOfferingDAO {

    public boolean encodeOffering(CourseOffering newOffering) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO courseoffering (syllabusID, curriculumID, "
                    + "courseID, term, startYear, endYear, section, days, time, room, faculty)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newOffering.getSyllabusID());
            pstmt.setInt(2, newOffering.getCurriculumID());
            pstmt.setInt(3, newOffering.getCourseID());
            pstmt.setInt(4, newOffering.getTerm());
            pstmt.setInt(5, newOffering.getStartYear());
            pstmt.setInt(6, newOffering.getEndYear());
            pstmt.setString(7, newOffering.getSection());
            pstmt.setString(8, newOffering.getDays());
            pstmt.setString(9, newOffering.getTime());
            pstmt.setInt(10, newOffering.getRoom());
            pstmt.setInt(11, newOffering.getFaculty());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseOfferingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<CourseOffering> getAllOfferings() throws ParseException {
        ArrayList<CourseOffering> newOffering = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT CG.offeringID, CG.syllabusID, CG.curriculumID,CG.courseID, "
                    + "C.codeCourse, C.title as 'courseTitle', CG.term, CG.section, "
                    + "CG.days, CG.time, CG.room, RR.room as 'roomTitle', CG.faculty, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name', CG.startYear, CG.endYear\n"
                    + "FROM courseoffering CG\n"
                    + "JOIN refroom RR\n"
                    + "ON CG.room = RR.roomID\n"
                    + "JOIN course C \n"
                    + "ON CG.courseID = C.courseID\n"
                    + "JOIN USER U\n"
                    + "ON CG.faculty = U.userID;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CourseOffering temp = new CourseOffering();
                temp.setOfferingID(rs.getInt("offeringID"));
                temp.setSyllabusID(rs.getInt("syllabusID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setTerm(rs.getInt("term"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                temp.setSection(rs.getString("section"));
                temp.setDays(rs.getString("days"));
                temp.setTime(rs.getString("time"));
                temp.setRoom(rs.getInt("room"));
                temp.setRoomTitle(rs.getString("roomTitle"));
                temp.setFaculty(rs.getInt("faculty"));
                temp.setFacultyName(rs.getString("name"));
                newOffering.add(temp);
            }
            pstmt.close();
            conn.close();
            return newOffering;
        } catch (SQLException ex) {
            Logger.getLogger(CourseOfferingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CourseOffering getSpecificOffering(int offeringID) throws ParseException {
        CourseOffering newOffering = new CourseOffering();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT CG.offeringID, CG.syllabusID, CG.curriculumID, "
                    + "CUR.title as 'curriculumTitle', CG.courseID, CG.term, "
                    + "CG.startYear,CG.endYear, CG.section, CE.title as 'courseTitle', "
                    + "CG.room, R.Room as 'roomTitle',  CG.days, CG.time, CG.faculty, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM courseoffering CG \n"
                    + "JOIN course CE\n"
                    + "ON CG.courseID = CE.courseID\n"
                    + "JOIN refroom R \n"
                    + "ON CG.room = R.roomID\n"
                    + "JOIN user U \n"
                    + "ON CG.faculty = U.userID\n"
                    + "JOIN curriculum CUR \n"
                    + "ON CG.curriculumID = CUR.curriculumID\n"
                    + "WHERE CG.offeringID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, offeringID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                newOffering.setOfferingID(rs.getInt("offeringID"));
                newOffering.setSyllabusID(rs.getInt("syllabusID"));
                newOffering.setCurriculumID(rs.getInt("curriculumID"));
                newOffering.setCurriculumTitle(rs.getString("curriculumTitle"));
                newOffering.setCourseID(rs.getInt("courseID"));
                newOffering.setCourseTitle(rs.getString("courseTitle"));
                newOffering.setTerm(rs.getInt("term"));
                newOffering.setStartYear(rs.getInt("startYear"));
                newOffering.setEndYear(rs.getInt("endYear"));
                newOffering.setSection(rs.getString("section"));
                newOffering.setDays(rs.getString("days"));
                newOffering.setTime(rs.getString("time"));
                newOffering.setRoom(rs.getInt("room"));
                newOffering.setRoomTitle(rs.getString("roomTitle"));
                newOffering.setFaculty(rs.getInt("faculty"));
                newOffering.setFacultyName(rs.getString("name"));
            }
            pstmt.close();
            conn.close();
            return newOffering;
        } catch (SQLException ex) {
            Logger.getLogger(CourseOfferingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean mapOffering(CourseOffering newOffering) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapofferingtosyllabus (syllabusID, offeringID)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newOffering.getSyllabusID());
            pstmt.setInt(2, newOffering.getOfferingID());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseOfferingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Integer getLastOfferingID() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        int i = 1000;
        String query = "SELECT MAX(offeringID) from courseoffering;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(offeringID)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
