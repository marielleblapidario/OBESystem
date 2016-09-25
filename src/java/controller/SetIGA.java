/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.User;

/**
 *
 * @author mariellelapidario
 */
public class SetIGA extends BaseServlet {

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
            //String poNumber = request.getParameter("poNumber");
            UserDAO userDAO = new UserDAO();
            ArrayList<User> listUser = new ArrayList();
            
            listUser = userDAO.getAllUser();
            
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_IGA.jsp");
            request.setAttribute("listUser", listUser);
            rd.forward(request, response);
            
        } catch (ParseException ex) {
            Logger.getLogger(SetIGA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
