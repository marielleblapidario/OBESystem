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
import model.MapPOtoPA;

/**
 *
 * @author mariellelapidario
 */
public class MapPoToPaDAO {

    public boolean encodeMapPatoIga(MapPOtoPA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO mappotopa (codePO, codePA)\n"
                    + "VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodePO());
            pstmt.setString(2, newMapping.getCodePA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPoToPaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMapPatoIga(MapPOtoPA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE mappotopa\n"
                    + "SET codePA = ?\n"
                    + "WHERE codePO = ? AND codePA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newMapping.getCodePA());
            pstmt.setString(2, newMapping.getCodePO());
            pstmt.setString(3, newMapping.getCodePA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPoToPaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteMapPatoIga(MapPOtoPA newMapping) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "DELETE FROM mappotopa\n"
                    + "WHERE codePO = ? AND codePA = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newMapping.getCodePO());
            pstmt.setString(2, newMapping.getCodePA());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MapPoToPaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
