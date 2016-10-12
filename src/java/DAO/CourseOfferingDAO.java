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
            String query = "INSERT INTO courseoffering (curriculumID, courseID, "
                    + "term, section, days, time, room, faculty)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newOffering.getCurriculumID());
            pstmt.setInt(2, newOffering.getCourseID());
            pstmt.setInt(3, newOffering.getTerm());
            pstmt.setString(4, newOffering.getSection());
            pstmt.setString(5, newOffering.getDays());
            pstmt.setString(6, newOffering.getTime());
            pstmt.setInt(7, newOffering.getRoom());
            pstmt.setInt(8, newOffering.getFaculty());

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
            String query = "SELECT CG.offeringID, CG.curriculumID, CG.courseID, CG.term, CG.section, "
                    + "CE.title as 'courseTitle', CG.room, R.Room as 'roomTitle',  "
                    + "CG.days, CG.time, CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM courseoffering CG \n"
                    + "JOIN course CE\n"
                    + "ON CG.courseID = CE.courseID\n"
                    + "JOIN refroom R \n"
                    + "ON CG.room = R.roomID\n"
                    + "JOIN user U \n"
                    + "ON CG.faculty = U.userID;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CourseOffering temp = new CourseOffering();
                temp.setOfferingID(rs.getInt("offeringID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setSection(rs.getString("section"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setRoom(rs.getInt("room"));
                temp.setRoomTitle("roomTitle");
                temp.setDays(rs.getString("days"));
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
