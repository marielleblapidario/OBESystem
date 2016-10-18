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
import model.Syllabus;

/**
 *
 * @author mariellelapidario
 */
public class SyllabusDAO {

    public boolean encodeSyllabus(Syllabus newSyllabus) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO syllabus (mapCurID, curriculumID, courseID, "
                    + "term, startYear, endYear, contributor, dateMade, dateUpdated)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newSyllabus.getMapCurID());
            pstmt.setInt(2, newSyllabus.getCurriculumID());
            pstmt.setInt(3, newSyllabus.getCourseID());
            pstmt.setInt(4, newSyllabus.getTerm());
            pstmt.setInt(5, newSyllabus.getStartYear());
            pstmt.setInt(6, newSyllabus.getEndYear());
            pstmt.setInt(7, newSyllabus.getContributor());
            pstmt.setDate(8, newSyllabus.getDateMade());
            pstmt.setDate(9, newSyllabus.getDateUpdated());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Syllabus> getAllSyllabus() throws ParseException {
        ArrayList<Syllabus> newSyllabus = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT S.syllabusID, S.curriculumID, S.courseID, S.term, "
                    + "CS.codeCourse, CS.title as 'courseTitle', "
                    + "P.title as 'programTitle', C.title as 'curriculumTitle',\n"
                    + "S.mapCurID, S.startYear, S.endYear "
                    + "FROM syllabus S \n"
                    + "JOIN curriculum C \n"
                    + "ON S.curriculumID = C.curriculumID \n"
                    + "JOIN program P \n"
                    + "ON C.program = P.codeProgram\n"
                    + "JOIN course CS\n"
                    + "ON S.courseID = CS.courseID;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus temp = new Syllabus();
                temp.setSyllabusID(rs.getInt("syllabusID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setProgramTitle(rs.getString("programTitle"));
                temp.setCurriculumTitle(rs.getString("curriculumTitle"));
                temp.setMapCurID(rs.getInt("mapCurID"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                newSyllabus.add(temp);
            }
            pstmt.close();
            conn.close();
            return newSyllabus;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Syllabus getSpecificSyllabus(int curriculumID, int courseID, int term) throws ParseException {
        Syllabus syllabus = new Syllabus();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT S.curriculumID, S.courseID, S.term, "
                    + "CS.title as 'courseTitle', C.title as 'curriculumTitle'\n"
                    + "S.mapCurID, S.startYear, S.endYear "
                    + "FROM syllabus S \n"
                    + "JOIN curriculum C \n"
                    + "ON S.curriculumID = C.curriculumID\n"
                    + "JOIN course CS\n"
                    + "ON S.courseID = CS.courseID\n"
                    + "WHERE  S.curriculumID = ? AND  S.courseID = ? AND  S.term = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);
            pstmt.setInt(3, term);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                syllabus.setCurriculumID(rs.getInt("curriculumID"));
                syllabus.setCourseID(rs.getInt("courseID"));
                syllabus.setTerm(rs.getInt("term"));
                syllabus.setCourseTitle(rs.getString("courseTitle"));
                syllabus.setCurriculumTitle(rs.getString("curriculumTitle"));
                syllabus.setMapCurID(rs.getInt("mapCurID"));
                syllabus.setStartYear(rs.getInt("startYear"));
                syllabus.setEndYear(rs.getInt("endYear"));
            }
            pstmt.close();
            conn.close();
            return syllabus;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Integer getLastSyllabusID() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        int i = 1000;
        String query = "SELECT MAX(syllabusID) from syllabus;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(syllabusID)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
