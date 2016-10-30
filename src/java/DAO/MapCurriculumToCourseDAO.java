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
            String query = "INSERT INTO mapcurriculumtocourse (curriculumID, courseID, "
                    + "term, yearLevel, prerequisite)\n"
                    + "VALUES (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCurriculumID());
            pstmt.setInt(2, newMapping.getCourseID());
            pstmt.setInt(3, newMapping.getTerm());
            pstmt.setInt(4, newMapping.getYearLevel());
            pstmt.setInt(5, newMapping.getPreRequisite());

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
                    + "SET courseID = ?, term = ?, yearLevel = ?, preRequisiste = ?\n"
                    + "WHERE curriculumID = ? and courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCourseID());
            pstmt.setInt(2, newMapping.getTerm());
            pstmt.setInt(3, newMapping.getYearLevel());
            pstmt.setInt(4, newMapping.getPreRequisite());
            pstmt.setInt(5, newMapping.getCurriculumID());
            pstmt.setInt(6, newMapping.getCourseID());

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
            String query = "SELECT MCTC.mapCurID, MCTC.curriculumID, C.codeCourse, MCTC.courseID,\n"
                    + "C.title, C.units, MCTC.term, MCTC.yearLevel, MCTC.prerequisite, PC.codeCourse as 'pTitle'\n"
                    + "FROM mapcurriculumtocourse MCTC\n"
                    + "JOIN course C\n"
                    + "ON MCTC.courseID = C. courseID\n"
                    + "LEFT JOIN course PC \n"
                    + "ON MCTC.prerequisite = PC.courseID\n"
                    + "WHERE curriculumID = ?\n"
                    + "ORDER BY yearLevel ASC, term ASC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCurriculum);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCurriculumToCourse temp = new MapCurriculumToCourse();
                temp.setMapCurID(rs.getInt("mapCurID"));
                temp.setCurriculumID(rs.getInt("curriculumID"));
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCourseTitle(rs.getString("title"));
                temp.setUnits(rs.getInt("units"));
                temp.setTerm(rs.getInt("term"));
                temp.setYearLevel(rs.getInt("yearLevel"));
                temp.setPreRequisite(rs.getInt("prerequisite"));
                temp.setPrerequisiteTitle(rs.getString("pTitle"));
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

    public int getCurID(int curriculumID, int courseID) throws ParseException {
        int mapCurID = -1;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT mapCurID\n"
                    + "FROM mapcurriculumtocourse\n"
                    + "WHERE curriculumID = ? AND courseID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, curriculumID);
            pstmt.setInt(2, courseID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                mapCurID = rs.getInt("mapCurID");
            }
            pstmt.close();
            conn.close();
            return mapCurID;
        } catch (SQLException ex) {
            Logger.getLogger(MapCurriculumToCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapCurID;
    }

}
