/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProgramDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mariellelapidario
 */
public class GetProgramByCollege extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        try(PrintWriter out = response.getWriter()){
            String SelectedCollege = request.getParameter("SelectedCollege");
            Gson g = new Gson();
            String s = g.toJson(new ProgramDAO().searchProgram(Integer.parseInt(SelectedCollege)));
            out.print(s);            
        } catch (ParseException ex) {
            Logger.getLogger(GetProgramByCollege.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}