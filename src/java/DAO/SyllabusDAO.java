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
            String query = "INSERT INTO syllabus (curriculumID, courseID, term)\n"
                    + "VALUES (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newSyllabus.getCurriculumID());
            pstmt.setInt(2, newSyllabus.getCourseID());
            pstmt.setInt(3, newSyllabus.getTerm());

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
            String query = "SELECT S.curriculumID, S.courseID, S.term, "
                    + "CS.codeCourse, CS.title as 'courseTitle', "
                    + "P.title as 'programTitle', C.title as 'curriculumTitle'\n"
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
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setTerm(rs.getInt("term"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCourseTitle(rs.getString("courseTitle"));
                temp.setProgramTitle(rs.getString("programTitle"));
                temp.setCurriculumTitle(rs.getString("curriculumTitle"));
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
