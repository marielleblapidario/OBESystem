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
import model.PO;

/**
 *
 * @author mariellelapidario
 */
public class PoDAO {
    public boolean encodePO(PO newPO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO PO (codePO, program, description, "
                    + "status, remarks, dateMade, dateUpdated, contributor, checker)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPO.getCodePO());
            pstmt.setString(2, newPO.getProgram());
            pstmt.setString(3, newPO.getDescription());
            pstmt.setString(4, newPO.getStatus());
            pstmt.setString(5, newPO.getRemarks());
            pstmt.setDate(6, newPO.getDateMade());
            pstmt.setDate(7, newPO.getDateUpdated());
            pstmt.setInt(8, newPO.getContributor());
            pstmt.setInt(9, newPO.getChecker());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updatePA(PO newPO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE PO\n"
                    + "SET description = ?, status = ?, remarks = ?, dateUpdated = ?, contributor = ?\n"
                    + "WHERE codePO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newPO.getDescription());
            pstmt.setString(2, newPO.getStatus());
            pstmt.setString(3, newPO.getRemarks());
            pstmt.setDate(4, newPO.getDateUpdated());
            pstmt.setInt(5, newPO.getContributor());
            pstmt.setString(6, newPO.getCodePO());
            

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<PO> getAllPO(String codeProgram) throws ParseException {
        ArrayList<PO> newPO = new ArrayList<PO>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT P.codePO, P.program, prog.title, prog.college, "
                    + "P.description, status, P.remarks, P.contributor, P.checker,\n"
                    + "CONCAT(checker.firstName, \" \" , checker.LastName) as 'checkerName'\n"
                    + "FROM PO P\n"
                    + "JOIN user checker\n"
                    + "ON P.checker = checker.userID\n"
                    + "JOIN program prog\n"
                    + "ON P.program = prog.codeProgram\n"
                    + "WHERE P.program = ? AND P.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeProgram);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PO temp = new PO();
                temp.setCodePO(rs.getString("codePO"));
                temp.setProgram(rs.getString("program"));
                temp.setProgramTitle(rs.getString("title"));
                temp.setCollege(rs.getString("college"));
                temp.setDescription(rs.getString("description"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setChecker(rs.getInt("checker"));
                temp.setCheckerName(rs.getString("checkerName"));
                newPO.add(temp);
            }
            pstmt.close();
            conn.close();
            return newPO;
        } catch (SQLException ex) {
            Logger.getLogger(PoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deletePO(String codePO) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE PO\n"
                    + "SET isDELETED = TRUE\n"
                    + "WHERE codePO = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codePO);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLastCodePO(String codeProgram) throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String i = "";
        String query = "SELECT MAX(codePO) from PO where program = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, codeProgram);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getString("MAX(codePO)");
        }
        ps.close();
        rs.close();
        return i;
    }
}
