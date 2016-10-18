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
import model.Curriculum;
import model.MapCurriculumToCourse;

/**
 *
 * @author mariellelapidario
 */
public class CurriculumDAO {

    public boolean encodeCurriculum(Curriculum newCurriculum) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO curriculum (title, program, startYear, "
                    + "endYear, description, contributor)\n"
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newCurriculum.getTitle());
            pstmt.setString(2, newCurriculum.getProgram());
            pstmt.setInt(3, newCurriculum.getStartYear());
            pstmt.setInt(4, newCurriculum.getEndYear());
            pstmt.setString(5, newCurriculum.getDescription());
            pstmt.setInt(6, newCurriculum.getContributor());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean encodeMapCurriculumToCourse(MapCurriculumToCourse newMap) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapCurriculumToCourse (ciurriculumID, courseID)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMap.getCurriculumID());
            pstmt.setInt(2, newMap.getCourseID());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteCurriculum(String codeCurriculum) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE curriculum\n"
                    + "SET isDeleted = TRUE\n"
                    + "WHERE curriculumID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCurriculum);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Curriculum> getAllCurriculum() throws ParseException {
        ArrayList<Curriculum> newCurriculum = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT C.curriculumID, C.program, C.title as 'crTitle', "
                    + "P.title as 'pTitle', CO.college, C.startYear, C.endYear, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM curriculum C\n"
                    + "JOIN program P \n"
                    + "ON C.program = P.codeProgram\n"
                    + "JOIN refcollege CO \n"
                    + "ON P.college = CO.collegeID\n"
                    + "JOIN user U\n"
                    + "ON C.contributor = U.userID\n"
                    + "WHERE C.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Curriculum temp = new Curriculum();
                temp.setCodeCurriculum(rs.getString("curriculumID"));
                temp.setProgram(rs.getString("program"));
                temp.setTitle(rs.getString("crTitle"));
                temp.setProgramName(rs.getString("pTitle"));
                temp.setCollegeName(rs.getString("college"));
                temp.setStartYear(rs.getInt("startYear"));
                temp.setEndYear(rs.getInt("endYear"));
                temp.setContributorName(rs.getString("name"));
                newCurriculum.add(temp);
            }
            pstmt.close();
            conn.close();
            return newCurriculum;
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Curriculum getSpecificCurriculum(String codeCurriculum) throws ParseException {
        Curriculum newCurriculum = new Curriculum();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT C.curriculumID, C.program, C.title as 'crTitle', "
                    + "P.title as 'pTitle', C.startYear, C.endYear, C.description\n"
                    + "FROM curriculum C\n"
                    + "JOIN program P \n"
                    + "ON C.program = P.codeProgram\n"
                    + "WHERE curriculumID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCurriculum);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                newCurriculum.setCodeCurriculum(rs.getString("curriculumID"));
                newCurriculum.setProgram(rs.getString("program"));
                newCurriculum.setTitle(rs.getString("crTitle"));
                newCurriculum.setProgramName(rs.getString("pTitle"));
                newCurriculum.setStartYear(rs.getInt("startYear"));
                newCurriculum.setEndYear(rs.getInt("endYear"));
                newCurriculum.setDescription(rs.getString("description"));
            }
            pstmt.close();
            conn.close();
            return newCurriculum;
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getLastCodeCurriculum() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        int i = 1000;
        String query = "SELECT MAX(curriculumID) from curriculum;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(curriculumID)");
        }
        ps.close();
        rs.close();
        return i;
    }

}
