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
            String query = "INSERT INTO mappatoiga (codePA, codeIGA)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodePA());
            pstmt.setString(2, newMapping.getCodeIGA());

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
                    + "SET codeIGA = ? "
                    + "WHERE codePA = ? AND codIGA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodeIGA());
            pstmt.setString(2, newMapping.getCodePA());
            pstmt.setString(3, newMapping.getCodeIGA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPatoIgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteMapPatoIga(MapPAtoIGA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "DELETE FROM mappatoiga\n"
                    + "WHERE codePA = ? AND codeIGA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newMapping.getCodePA());
            pstmt.setString(2, newMapping.getCodeIGA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPatoIgaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
