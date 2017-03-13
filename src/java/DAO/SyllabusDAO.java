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
    
    public boolean deleteSyllabus(int contributor, int syllabusID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "set @userID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, contributor);
            pstmt.execute();
            
            query = "delete from syllabus where syllabusID = ?;";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, syllabusID);
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
            String query = "SELECT S.syllabusID, S.mapCurID, S.curriculumID,\n"
                    + "S.courseID, S.term, S.startYear, S.endYear, C.title as 'curriculumTitle',\n"
                    + "CE.title as 'courseTitle', CE.codeCourse\n"
                    + "FROM syllabus S\n"
                    + "JOIN curriculum  C\n"
                    + "ON S.curriculumID = C.curriculumID\n"
                    + "JOIN course CE\n"
                    + "ON S.courseID = CE.courseID \n"
                    + "ORDER BY S.syllabusID DESC";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus temp = new Syllabus();
                temp.setSyllabusID(rs.getInt("syllabusID"));
                temp.setMapCurID(rs.getInt("mapCurID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setCurriculumTitle(rs.getString("curriculumTitle"));
                temp.setCodeCourse(rs.getString("codeCourse"));
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

    public Syllabus getSpecificSyllabus(int syllabusID) throws ParseException {
        Syllabus syllabus = new Syllabus();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT S.syllabusID, S.mapCurID, S.curriculumID, "
                    + "S.courseID, S.term, S.startYear, S.endYear, "
                    + "C.title as 'curriculumTitle', CE.title as 'courseTitle'\n"
                    + "FROM syllabus S \n"
                    + "JOIN curriculum  C \n"
                    + "ON S.curriculumID = C.curriculumID\n"
                    + "JOIN course CE \n"
                    + "ON S.courseID = CE.courseID\n"
                    + "WHERE S.syllabusID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                syllabus.setSyllabusID(rs.getInt("syllabusID"));
                syllabus.setMapCurID(rs.getInt("mapCurID"));
                syllabus.setCurriculumID(rs.getInt("curriculumID"));
                syllabus.setCourseID(rs.getInt("courseID"));
                syllabus.setTerm(rs.getInt("term"));
                syllabus.setStartYear(rs.getInt("startYear"));
                syllabus.setEndYear(rs.getInt("endYear"));
                syllabus.setCourseTitle(rs.getString("courseTitle"));
                syllabus.setCurriculumTitle(rs.getString("curriculumTitle"));
            }
            pstmt.close();
            conn.close();
            return syllabus;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Syllabus> getSpecificSyllabusCO(int syllabusID) throws ParseException {
        ArrayList<Syllabus> arrSyllabus = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT coID, syllabusID, mapCurID, curriculumID, "
                    + "courseID, term, startYear, endYear, codePI, codeCO, description, remarks\n"
                    + "FROM CO\n"
                    + "WHERE syllabusID = ?\n"
                    + "ORDER BY coID;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus syllabus = new Syllabus();
                syllabus.setCoID(rs.getInt("coID"));
                syllabus.setSyllabusID(rs.getInt("syllabusID"));
                syllabus.setMapCurID(rs.getInt("mapCurID"));
                syllabus.setCurriculumID(rs.getInt("curriculumID"));
                syllabus.setCourseID(rs.getInt("courseID"));
                syllabus.setTerm(rs.getInt("term"));
                syllabus.setStartYear(rs.getInt("startYear"));
                syllabus.setEndYear(rs.getInt("endYear"));
                syllabus.setCodePI(rs.getString("codePI"));
                syllabus.setCodeCO(rs.getString("codeCO"));
                syllabus.setCoDescription(rs.getString("description"));
                syllabus.setCoRemarks(rs.getString("remarks"));
                arrSyllabus.add(syllabus);
            }
            pstmt.close();
            conn.close();
            return arrSyllabus;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Syllabus> getSpecificSyllabusAssessment(int syllabusID) throws ParseException {
        ArrayList<Syllabus> arrSyllabus = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT A.assessmentID, A.coID, A.syllabusID, A.mapCurID,\n"
                    + "A.curriculumID, A.courseID, A.term, A.startYear, A.endYear,\n"
                    + "C.codeCO, A.codeAT, A.type, RT.type as 'typeTitle', A.description, A.weight\n"
                    + "FROM assessment A\n"
                    + "JOIN CO C \n"
                    + "ON A.coID = C.coID\n"
                    + "JOIN refType RT \n"
                    + "ON A.type = RT.typeID\n"
                    + "WHERE A.syllabusID = ? \n"
                    + "ORDER BY A.assessmentID;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus syllabus = new Syllabus();
                syllabus.setAssessmentID(rs.getInt("assessmentID"));
                syllabus.setCoID(rs.getInt("coID"));
                syllabus.setSyllabusID(rs.getInt("syllabusID"));
                syllabus.setMapCurID(rs.getInt("mapCurID"));
                syllabus.setCurriculumID(rs.getInt("curriculumID"));
                syllabus.setCourseID(rs.getInt("courseID"));
                syllabus.setTerm(rs.getInt("term"));
                syllabus.setStartYear(rs.getInt("startYear"));
                syllabus.setEndYear(rs.getInt("endYear"));
                syllabus.setCodeCO(rs.getString("codeCO"));
                syllabus.setCodeAT(rs.getString("codeAT"));
                syllabus.setType(rs.getInt("type"));
                syllabus.setTypeTitle(rs.getString("typeTitle"));
                syllabus.setAssessmentDescription(rs.getString("description"));
                syllabus.setAssessmentWeight(rs.getDouble("weight"));
                arrSyllabus.add(syllabus);
            }
            pstmt.close();
            conn.close();
            return arrSyllabus;
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
    public ArrayList<Integer> getSylforCur(int curriculumID) throws ParseException {
        ArrayList<Integer> arrSyllabus = new ArrayList();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select courseID from syllabus where curriculumID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                arrSyllabus.add(rs.getInt("courseID"));
            }
            pstmt.close();
            conn.close();
            return arrSyllabus;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Integer getSyllabusCount(int syllabusID) throws ParseException {
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select count(syllabusID) as 'count' from courseoffering where syllabusID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, syllabusID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               count = rs.getInt("count");
            }
            pstmt.close();
            conn.close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Syllabus> getCurrentSyllabus() throws ParseException {
        ArrayList<Syllabus> newSyllabus = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT S.syllabusID, S.mapCurID, S.curriculumID,\n"
                    + "S.courseID, S.term, S.startYear, S.endYear, C.title as 'curriculumTitle',\n"
                    + "CE.title as 'courseTitle', CE.codeCourse\n"
                    + "FROM syllabus S\n"
                    + "JOIN curriculum  C\n"
                    + "ON S.curriculumID = C.curriculumID\n"
                    + "JOIN course CE\n"
                    + "ON S.courseID = CE.courseID \n"
                    + "WHERE S.startYear >= YEAR(curdate()) \n"
                    + "ORDER BY S.syllabusID DESC";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus temp = new Syllabus();
                temp.setSyllabusID(rs.getInt("syllabusID"));
                temp.setMapCurID(rs.getInt("mapCurID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setCurriculumTitle(rs.getString("curriculumTitle"));
                temp.setCodeCourse(rs.getString("codeCourse"));
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
    
    public ArrayList<Syllabus> GetPastSyllabus() throws ParseException {
        ArrayList<Syllabus> newSyllabus = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT S.syllabusID, S.mapCurID, S.curriculumID,\n"
                    + "S.courseID, S.term, S.startYear, S.endYear, C.title as 'curriculumTitle',\n"
                    + "CE.title as 'courseTitle', CE.codeCourse\n"
                    + "FROM syllabus S\n"
                    + "JOIN curriculum  C\n"
                    + "ON S.curriculumID = C.curriculumID\n"
                    + "JOIN course CE\n"
                    + "ON S.courseID = CE.courseID \n"
                    + "WHERE S.startYear <  YEAR(curdate()) \n"
                    + "ORDER BY S.syllabusID DESC";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Syllabus temp = new Syllabus();
                temp.setSyllabusID(rs.getInt("syllabusID"));
                temp.setMapCurID(rs.getInt("mapCurID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setCurriculumTitle(rs.getString("curriculumTitle"));
                temp.setCodeCourse(rs.getString("codeCourse"));
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
}
