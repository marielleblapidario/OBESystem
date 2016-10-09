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
import model.PA;

/**
 *
 * @author mariellelapidario
 */
public class PaDAO {

    public boolean encodePA(PA newPA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO PA (codePA, program, description, "
                    + "status, remarks, dateMade, dateUpdated, contributor, checker)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPA.getCodePA());
            pstmt.setString(2, newPA.getProgram());
            pstmt.setString(3, newPA.getDescription());
            pstmt.setString(4, newPA.getStatus());
            pstmt.setString(5, newPA.getRemarks());
            pstmt.setDate(6, newPA.getDateMade());
            pstmt.setDate(7, newPA.getDateUpdated());
            pstmt.setInt(8, newPA.getContributor());
            pstmt.setInt(9, newPA.getChecker());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updatePA(PA newPA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE PA\n"
                    + "SET description = ?, status = ?, remarks = ?, dateUpdated = ?, contributor = ?\n"
                    + "WHERE codePA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPA.getDescription());
            pstmt.setString(2, newPA.getStatus());
            pstmt.setString(3, newPA.getRemarks());
            pstmt.setDate(4, newPA.getDateUpdated());
            pstmt.setInt(5, newPA.getContributor());
            pstmt.setString(6, newPA.getCodePA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<PA> getAllPA(String codeProgram) throws ParseException {
        ArrayList<PA> newPA = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT P.codePA, P.description, "
                    + "MPTI.codeIGA, I.title, P.status, P.remarks\n"
                    + "FROM PA P\n"
                    + "JOIN mappatoiga MPTI\n"
                    + "ON P.codePA = MPTI.codePA\n"
                    + "JOIN IGA I\n"
                    + "ON MPTI.codeIGA = I.codeIGA\n"
                    + "WHERE P.program = ? AND P.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeProgram);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PA temp = new PA();
                temp.setCodePA(rs.getString("codePA"));
                temp.setDescription(rs.getString("description"));
                temp.setCodeIGA(rs.getString("codeIGA"));
                temp.setIgaTitle(rs.getString("title"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                newPA.add(temp);
            }
            pstmt.close();
            conn.close();
            return newPA;
        } catch (SQLException ex) {
            Logger.getLogger(PaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deletePA(String codePA) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE PA\n"
                    + "SET isDELETED = TRUE\n"
                    + "WHERE codePA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codePA);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLastCodePA(String codeProgram) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codePA) from PA where program = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, codeProgram);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codePA)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
