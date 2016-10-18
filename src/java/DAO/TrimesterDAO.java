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
import model.Trimester;

/**
 *
 * @author mariellelapidario
 */
public class TrimesterDAO {
     public ArrayList<Trimester> getAllTrimester() throws ParseException {
        ArrayList<Trimester> newCollege = new ArrayList<>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT term\n"
                    + "from reftrimester\n"
                    + "ORDER BY term;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Trimester temp = new Trimester();
                temp.setTerm(rs.getInt("term"));
                
                newCollege.add(temp);

            }
            pstmt.close();
            conn.close();
            return newCollege;
        } catch (SQLException ex) {
            Logger.getLogger(TrimesterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
