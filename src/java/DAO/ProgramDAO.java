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
import model.Program;

/**
 *
 * @author mariellelapidario
 */
public class ProgramDAO {

    public boolean encodeProgram(Program newProgram) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO program (codeProgram, title, college, units, description,"
                    + " dateMade, dateUpdated, contributor)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newProgram.getCodeProgram());
            pstmt.setString(2, newProgram.getTitle());
            pstmt.setString(3, newProgram.getCollege());
            pstmt.setInt(4, newProgram.getUnits());
            pstmt.setString(5, newProgram.getDescription());
            pstmt.setDate(6, newProgram.getDateMade());
            pstmt.setDate(7, newProgram.getDateUpdated());
            pstmt.setInt(8, newProgram.getContributor());

            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateProgram(Program newProgram) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE program \n"
                    + "SET codeProgram = ?, title = ?, college = ?, units = ?, "
                    + "description = ?, dateUpdated = ?, contributor = ?\n"
                    + "WHERE codeProgram = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newProgram.getCodeProgram());
            pstmt.setString(2, newProgram.getTitle());
            pstmt.setString(3, newProgram.getCollege());
            pstmt.setInt(4, newProgram.getUnits());
            pstmt.setString(5, newProgram.getDescription());
            pstmt.setDate(6, newProgram.getDateUpdated());
            pstmt.setInt(7, newProgram.getContributor());
            pstmt.setString(8, newProgram.getCodeProgram());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Program> getAllProgram() throws ParseException {

        ArrayList<Program> newProgram = new ArrayList<Program>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeProgram, title, college, contributor, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM program P\n"
                    + "JOIN user U\n"
                    + "ON P.contributor = U.userID\n"
                    + "WHERE P.isDeleted IS NULL;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Program temp = new Program();
                temp.setCodeProgram(rs.getString("codeProgram"));
                temp.setTitle(rs.getString("title"));
                temp.setCollege(rs.getString("college"));
                temp.setContributor(rs.getInt("contributor"));
                temp.setContributorName(rs.getString("name"));
                newProgram.add(temp);
            }
            pstmt.close();
            conn.close();
            return newProgram;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Program getSpecificProgram(String codeProgram) throws ParseException {
        Program program = new Program();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeProgram, title, college, units, description, contributor, "
                    + "CONCAT(U.firstName, \" \" , U.LastName) as 'name'\n"
                    + "FROM program P\n"
                    + "JOIN user U\n"
                    + "ON P.contributor = U.userID\n"
                    + "WHERE codeProgram = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, codeProgram);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                program.setCodeProgram(rs.getString("codeProgram"));
                program.setTitle(rs.getString("title"));
                program.setCollege(rs.getString("college"));
                program.setUnits(rs.getInt("units"));
                program.setDescription("description");
                program.setContributor(rs.getInt("contributor"));
                program.setContributorName(rs.getString("name"));
            }
            pstmt.close();
            conn.close();
            return program;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Program> searchProgram(String college) throws ParseException {
        ArrayList<Program> newProgram = new ArrayList<Program>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeProgram, title\n"
                    + "FROM program\n"
                    + "where college = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, college);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Program program = new Program();
                program.setCodeProgram(rs.getString("codeProgram"));
                program.setTitle(rs.getString("title"));
                newProgram.add(program);

            }
            pstmt.close();
            conn.close();
            return newProgram;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Program> getAllProgramTitle() throws ParseException {
        ArrayList<Program> newProgram = new ArrayList<Program>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT codeProgram, title\n"
                    + "FROM program;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Program program = new Program();
                program.setCodeProgram(rs.getString("codeProgram"));
                program.setTitle(rs.getString("title"));
                newProgram.add(program);

            }
            pstmt.close();
            conn.close();
            return newProgram;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteProgram(String codeProgram) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE program\n"
                    + "SET isDeleted = TRUE\n"
                    + "WHERE codeProgram = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, codeProgram);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
