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
import model.MapCurriculumCoursesToPI;
import model.MapCurriculumToCourse;

/**
 *
 * @author mariellelapidario
 */
public class MapCurriculumToPiDAO {

    public boolean encodeMapCurriculumToCourse(MapCurriculumCoursesToPI newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapcurriculumcoursestopi (curriculumID, courseID, codePI)\n"
                    + "VALUES (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCurriculumID());
            pstmt.setInt(2, newMapping.getCourseID());
            pstmt.setString(3, newMapping.getCodePI());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToPiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapCurriculumCoursesToPI> getCurriculumMapping(int curriculumID) throws ParseException {
        ArrayList<MapCurriculumCoursesToPI> newMapping = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT curriculumID, courseID, codePI\n"
                    + "FROM mapcurriculumcoursestopi\n"
                    + "WHERE curriculumID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCurriculumCoursesToPI temp = new MapCurriculumCoursesToPI();
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodePI(rs.getString("codePI"));
                newMapping.add(temp);
            }
            pstmt.close();
            conn.close();
            return newMapping;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToPiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<MapCurriculumCoursesToPI> getPIforCO(int curriculumID, int courseID) throws ParseException {
        ArrayList<MapCurriculumCoursesToPI> newMapping = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT curriculumID, courseID, codePI\n"
                    + "FROM mapcurriculumcoursestopi\n"
                    + "WHERE curriculumID = ? AND courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCurriculumCoursesToPI temp = new MapCurriculumCoursesToPI();
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodePI(rs.getString("codePI"));
                newMapping.add(temp);
            }
            pstmt.close();
            conn.close();
            return newMapping;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToPiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteMapping(MapCurriculumCoursesToPI newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "DELETE FROM mapcurriculumcoursestopi\n"
                    + "WHERE curriculumID = ? AND courseID = ? AND codePI = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCurriculumID());
            pstmt.setInt(2, newMapping.getCourseID());
            pstmt.setString(3, newMapping.getCodePI());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToPiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
