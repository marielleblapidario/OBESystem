/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.IgaDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.IGA;
import model.User;

/**
 *
 * @author mariellelapidario
 */
public class ViewIGA extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {        
            IgaDAO igaDAO = new IgaDAO();
            ArrayList<IGA>  listIGA = new ArrayList<IGA>();  
            String codeIGA = "";
            listIGA = igaDAO.getAllIGA();
            
            /*get last codeIGA*/
            try {
                codeIGA = igaDAO.getLastCodeIGA();
            } catch (SQLException ex) {
                Logger.getLogger(ViewIGA.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("size:" + listIGA.size());
            System.out.println("last code: " + codeIGA);
            
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_IGA.jsp");
            request.setAttribute("listIGA", listIGA);
            request.setAttribute("codeIGA", codeIGA);
            rd.forward(request, response);
            
        } catch (ParseException ex) {
            Logger.getLogger(ViewIGA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
