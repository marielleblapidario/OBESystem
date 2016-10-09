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
import model.MapCourseToProgram;

/**
 *
 * @author mariellelapidario
 */
public class MapCourseToProgramDAO {

    public boolean encodeMapCourseToProgram(MapCourseToProgram newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapCourseToProgram (courseID, codeProgram)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newMapping.getCourseID());
            System.out.println("id: " + newMapping.getCourseID());
            pstmt.setString(2, newMapping.getCodeProgram());
            System.out.println("program: " + newMapping.getCodeProgram());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCourseToProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMapCourseToProgram(MapCourseToProgram newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE mapCourseToProgram\n"
                    + "SET codeProgram = ?"
                    + "WHERE courseID = ? AND codeProgram = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeProgram());
            pstmt.setInt(2, newMapping.getCourseID());
            pstmt.setString(3, newMapping.getCodeProgram());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCourseToProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapCourseToProgram> getSpecificMapCourseToProgram(int courseID) throws ParseException {
        ArrayList<MapCourseToProgram> newMapping = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MCTP.courseID, MCTP.codeProgram, P.title\n"
                    + "FROM mapCourseToProgram MCTP\n"
                    + "JOIN program P\n"
                    + "ON MCTP.codeProgram = P.codeProgram\n"
                    + "WHERE MCTP.courseID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCourseToProgram temp = new MapCourseToProgram();
                temp.setCourseID(rs.getInt("courseID"));
                temp.setCodeProgram(rs.getString("codeProgram"));
                temp.setProgramTitle(rs.getString("title"));
                newMapping.add(temp);
            }
            pstmt.close();
            conn.close();
            return newMapping;
        } catch (SQLException ex) {
            Logger.getLogger(MapCourseToProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
