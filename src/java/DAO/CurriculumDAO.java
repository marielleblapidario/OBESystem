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
            pstmt.setDate(3, newCurriculum.getStartYear());
            pstmt.setDate(4, newCurriculum.getEndYear());
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
            String query = "INSERT INTO mapCurriculumToCourse (codeCurriculum, codeCourse)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMap.getCodeCurriculum());
            pstmt.setString(2, newMap.getCodeCourse());

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
                    + "WHERE codeCurriculum = ?;";
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
       
    public Integer getLastCodeCurriculum() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        int i = 1000;
        String query = "SELECT MAX(codeCurriculum) from curriculum;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(codeCurriculum)");
        }
        ps.close();
        rs.close();
        return i;
    }
    
}
