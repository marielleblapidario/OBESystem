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
import model.MapCurriculumToCourse;

/**
 *
 * @author mariellelapidario
 */
public class MapCurriculumToCourseDAO {

    public boolean encodeMapCurriculumToCourse(MapCurriculumToCourse newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapcurriculumtocourse (codeCurriculum, codeCourse)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCodeCurriculum());
            pstmt.setString(2, newMapping.getCodeCourse());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMapCurriculumToCourse(MapCurriculumToCourse newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE mapcurriculumtocourse\n"
                    + "SET codeCourse = ?\n"
                    + "WHERE codeCurriculum = ? and codeCourse = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeCourse());
            pstmt.setInt(2, newMapping.getCodeCurriculum());
            pstmt.setString(3, newMapping.getCodeCourse());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapCurriculumToCourse> getAllMapCurriculumToCourse(String codeCurriculum) throws ParseException {
        ArrayList<MapCurriculumToCourse> newMapping = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MCTC.codeCurriculum, MCTC.codeCourse, C.title, C.units\n"
                    + "FROM mapcurriculumtocourse MCTC\n"
                    + "JOIN course C\n"
                    + "ON MCTC.codeCourse = C.codeCourse\n"
                    + "WHERE MCTC.codeCurriculum = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCurriculum);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCurriculumToCourse temp = new MapCurriculumToCourse();
                temp.setCodeCurriculum(rs.getInt("codeCurriculum"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCourseTitle(rs.getString("title"));
                temp.setUnits(rs.getInt("units"));
                newMapping.add(temp);
            }
            pstmt.close();
            conn.close();
            return newMapping;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
