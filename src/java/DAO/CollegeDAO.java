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
import model.College;
/**
 *
 * @author mariellelapidario
 */
public class CollegeDAO {

    public ArrayList<College> getAllCollege() throws ParseException {
        ArrayList<College> newCollege = new ArrayList<College>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT collegeID, college\n"
                    + "from refCollege\n"
                    + "ORDER BY college;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                College college = new College();
                college.setCollege(rs.getString("college"));
                college.setCollegeID(rs.getInt("collegeID"));
                
                newCollege.add(college);

            }
            pstmt.close();
            conn.close();
            return newCollege;
        } catch (SQLException ex) {
            Logger.getLogger(CollegeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
