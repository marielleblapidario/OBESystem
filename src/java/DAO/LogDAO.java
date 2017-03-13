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
import model.Log;

/**
 *
 * @author mariellelapidario
 */
public class LogDAO {

    public ArrayList<Log> getAllLog() throws ParseException {
        ArrayList<Log> logs = new ArrayList<Log>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT log_no, txn_no, A.userID, "
                    + "CONCAT (firstName, \" \" , lastName) as 'userName', "
                    + "txn_time, txn_type, tbl_name, fld_name, bfr_value, ftr_value\n"
                    + "FROM audit A JOIN user U ON A.userID = U.userID ORDER BY log_no DESC;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Log log = new Log();
                log.setLog_no(rs.getInt("log_no"));
                log.setTxn_no(rs.getInt("txn_no"));
                log.setUserID(rs.getInt("userID"));
                log.setUserName(rs.getString("userName"));
                log.setTxn_time(rs.getString("txn_time"));
                log.setTxn_type(rs.getString("txn_type"));
                log.setTbl_name(rs.getString("tbl_name"));
                log.setFld_name(rs.getString("fld_name"));
                log.setBfr_value(rs.getString("bfr_value"));
                log.setFtr_value(rs.getString("ftr_value"));
                logs.add(log);
            }
            pstmt.close();
            conn.close();
            return logs;
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
