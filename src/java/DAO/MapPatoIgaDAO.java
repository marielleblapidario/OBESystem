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
import model.MapPAtoIGA;
import model.PA;

/**
 *
 * @author mariellelapidario
 */
public class MapPatoIgaDAO {

    public boolean encodeMapPatoIga(MapPAtoIGA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mappatoiga (codePA, codeIGA, status, "
                    + "remarks, contributor, checker)\n"
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodePA());
            pstmt.setString(2, newMapping.getCodeIGA());
            pstmt.setString(3, newMapping.getStatus());
            pstmt.setString(4, newMapping.getRemarks());
            pstmt.setInt(5, newMapping.getContributor());
            pstmt.setInt(6, newMapping.getChecker());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPatoIgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMapPatoIga(MapPAtoIGA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE mappatoiga\n"
                    + "SET codeIGA = ?, status = ?, remarks = ?, "
                    + "contributor = ?, checker = ?\n"
                    + "WHERE codePA = ? AND codIGA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeIGA());
            pstmt.setString(2, newMapping.getStatus());
            pstmt.setString(3, newMapping.getRemarks());
            pstmt.setInt(4, newMapping.getContributor());
            pstmt.setInt(5, newMapping.getChecker());
            pstmt.setString(6, newMapping.getCodePA());
            pstmt.setString(7, newMapping.getCodeIGA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPatoIgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MapPAtoIGA> getAllMapPatoIga(String codeProgram) throws ParseException {
        ArrayList<MapPAtoIGA> newMapping = new ArrayList<MapPAtoIGA>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT M.codePA, M.codeIGA, P.program, M.status, "
                    + "M.remarks, M.contributor, M.checker\n"
                    + "FROM mappatoiga M\n"
                    + "JOIN PA P\n"
                    + "ON M.codePA = P.codePA\n"
                    + "JOIN IGA I\n"
                    + "ON M.codeIGA = I.codeIGA\n"
                    + "WHERE P.program = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, codeProgram);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MapPAtoIGA temp = new MapPAtoIGA();
                temp.setCodePA(rs.getString("codePA"));
                temp.setCodeIGA(rs.getString("codeiGA"));
                temp.setProgram(rs.getString("program"));
                temp.setStatus(rs.getString("status"));
                temp.setRemarks(rs.getString("remarks"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setChecker(rs.getInt("checker"));
                newMapping.add(temp);
            }
            pstmt.close();
            conn.close();
            return newMapping;
        } catch (SQLException ex) {
            Logger.getLogger(MapPatoIgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
