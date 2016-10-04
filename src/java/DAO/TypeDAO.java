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
import model.Type;

/**
 *
 * @author mariellelapidario
 */
public class TypeDAO {
     public ArrayList<Type> getAllType() throws ParseException {
        ArrayList<Type> newType = new ArrayList<Type>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT typeID, type\n"
                    + "from refType\n"
                    + "ORDER BY type;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Type type = new Type();
                type.setType(rs.getString("type"));
                type.setTypeID(rs.getInt("typeID"));
                
                newType.add(type);

            }
            pstmt.close();
            conn.close();
            return newType;
        } catch (SQLException ex) {
            Logger.getLogger(TypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
