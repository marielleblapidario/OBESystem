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
import model.PI;

/**
 *
 * @author mariellelapidario
 */
public class PiDAO {

    public boolean encodePI(PI newPI) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO PI (codePI, program, description, "
                    + "remarks, dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPI.getCodePI());
            pstmt.setString(2, newPI.getProgram());
            pstmt.setString(3, newPI.getDescription());
            pstmt.setString(4, newPI.getRemarks());
            pstmt.setDate(5, newPI.getDateMade());
            pstmt.setDate(6, newPI.getDateUpdated());
            pstmt.setInt(7, newPI.getContributor());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updatePI(PI newPI) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE PI\n"
                    + "SET description = ?, remarks = ?, dateUpdated = ?, editor = ?\n"
                    + "WHERE codePI = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPI.getDescription());
            pstmt.setString(2, newPI.getRemarks());
            pstmt.setDate(3, newPI.getDateUpdated());
            pstmt.setInt(4, newPI.getContributor());
            pstmt.setString(5, newPI.getCodePI());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean EncodeMapPItoPO(PI newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mappitopo (codePI, codePO)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodePI());
            pstmt.setString(2, newMapping.getCodePO());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<PI> getAllMapPItoPO(String codePO) throws ParseException {
        ArrayList<PI> newPA = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MPTP.codePI, MPTP.codePO, P.description, P.remarks\n"
                    + "FROM mappitopo MPTP\n"
                    + "JOIN PI P\n"
                    + "ON MPTP.codePI = P.codePI\n"
                    + "WHERE MPTP.codePO = ? AND P.isDeleted IS NULL "
                    + "ORDER BY MPTP.codePI;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codePO);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PI temp = new PI();
                temp.setCodePI(rs.getString("codePI"));
                temp.setCodePO(rs.getString("codePO"));
                temp.setDescription(rs.getString("description"));
                temp.setRemarks(rs.getString("remarks"));
                newPA.add(temp);
            }
            pstmt.close();
            conn.close();
            return newPA;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<PI> GetAllPIforCurriulum(String program) throws ParseException {
        ArrayList<PI> newPA = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MPTP.codePI, MPTP.codePO, P.description\n"
                    + "FROM mappitopo MPTP\n"
                    + "JOIN PI P\n"
                    + "ON MPTP.codePI = P.codePI\n"
                    + "WHERE P.program = ? AND P.isDeleted IS NULL "
                    + "ORDER BY MPTP.codePI;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, program);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PI temp = new PI();
                temp.setCodePI(rs.getString("codePI"));
                temp.setCodePO(rs.getString("codePO"));
                temp.setDescription(rs.getString("description"));
                newPA.add(temp);
            }
            pstmt.close();
            conn.close();
            return newPA;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getLastCodePI(String codePO) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codePI) \n"
                + "FROM mappitopo \n"
                + "WHERE codePO = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, codePO);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codePI)");
        }
        ps.close();
        rs.close();
        return i;
    }

    public ArrayList<PI> getAllPI(String program) throws ParseException {
        ArrayList<PI> newPA = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT MPTP.codePI, MPTP.codePO, P.description, P.remarks\n"
                    + "FROM mappitopo MPTP\n"
                    + "JOIN PI P\n"
                    + "ON MPTP.codePI = P.codePI\n"
                    + "WHERE P.program = ? AND P.isDeleted IS NULL\n"
                    + "ORDER BY MPTP.codePI;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, program);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PI temp = new PI();
                temp.setCodePI(rs.getString("codePI"));
                temp.setCodePO(rs.getString("codePO"));
                temp.setDescription(rs.getString("description"));
                temp.setRemarks(rs.getString("remarks"));
                newPA.add(temp);
            }
            pstmt.close();
            conn.close();
            return newPA;
        } catch (SQLException ex) {
            Logger.getLogger(PiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
