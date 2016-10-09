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
            String query = "INSERT INTO mapcurriculumtocourse (ciurriculumID, courseID)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCurriculumID());
            pstmt.setInt(2, newMapping.getCourseID());

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
                    + "SET courseID = ?\n"
                    + "WHERE ciurriculumID = ? and courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCourseID());
            pstmt.setInt(2, newMapping.getCurriculumID());
            pstmt.setInt(3, newMapping.getCourseID());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapCurriculumToCourse> getSpecificMapCurriculumToCourse(String codeCurriculum) throws ParseException {
        ArrayList<MapCurriculumToCourse> newMapping = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MCTC.ciurriculumID, C.codeCourse, MCTC.courseID, C.title, C.units\n"
                    + "FROM mapcurriculumtocourse MCTC\n"
                    + "JOIN course C \n"
                    + "ON MCTC.courseID = C. courseID\n"
                    + "WHERE ciurriculumID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCurriculum);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCurriculumToCourse temp = new MapCurriculumToCourse();
                temp.setCurriculumID(rs.getInt("ciurriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
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
