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
import model.MapPAtoIGA;

/**
 *
 * @author mariellelapidario
 */
public class MapCourseToProgramDAO {

    public boolean encodeMapCourseToProgram(MapCourseToProgram newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mapCourseToProgram (codeCourse, codeProgram)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeCourse());
            pstmt.setString(2, newMapping.getCodeProgram());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCourseToProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMapPatoIgaDAO(MapCourseToProgram newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE mapCourseToProgram\n"
                    + "SET codeProgram = ?"
                    + "WHERE codeCourse = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeProgram());
            pstmt.setString(2, newMapping.getCodeCourse());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapCourseToProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapCourseToProgram> getAllMapPatoIgaDAO(String codeCourse) throws ParseException {
        ArrayList<MapCourseToProgram> newMapping = new ArrayList<MapCourseToProgram>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MCTP.codeCourse, MCTP.codeProgram, P.title\n"
                    + "FROM mapCourseToProgram MCTP\n"
                    + "JOIN program P\n"
                    + "ON MCTP.codeProgram = P.codeProgram\n"
                    + "WHERE MCTP.codeCourse = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeCourse);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapCourseToProgram temp = new MapCourseToProgram();
                temp.setCodeCourse(rs.getString("codeCourse"));
                temp.setCodeProgram(rs.getString("codeProgram"));
                temp.setProgramTitle(rs.getString("program"));
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
