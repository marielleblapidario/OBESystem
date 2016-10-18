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
import model.YearLevel;

/**
 *
 * @author mariellelapidario
 */
public class YearLevelDAO {
    public ArrayList<YearLevel> getAllYearLevel() throws ParseException {
        ArrayList<YearLevel> newCollege = new ArrayList<>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT level\n"
                    + "from refyearlevel\n"
                    + "ORDER BY level;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                YearLevel temp = new YearLevel();
                temp.setYearLevel(rs.getInt("level"));
                
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
