/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.IgaDAO;
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

/**
 *
 * @author mariellelapidario
 */
public class EncodeIGA extends BaseServlet {

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
        ArrayList<IGA> listIGA = new ArrayList<>();
        IgaDAO igaDAO = new IgaDAO();
        boolean x = true;

        String contributor = request.getParameter("contributor");
        String[] codeIGA = request.getParameterValues("codeIGA");
        String[] description = request.getParameterValues("description");
        String[] remarks = request.getParameterValues("remarks");
        
        System.out.println("contributor: " + contributor);
        System.out.println ("array size: " + codeIGA.length);

        for (int y = 0; y < codeIGA.length; y++) {
            System.out.println("codeIGA: " + codeIGA[y]);
            System.out.println("description: " + description[y]);
            System.out.println("remarks: " + remarks[y]);
            
            /*
            IGA iga = new IGA();
            if (x == true) {
                try {
                    
                    iga.setCodeIGA(codeIGA[y]);
                    iga.setDescription(description[y]);
                    iga.setRemarks(remarks[y]);
                    iga.setDateMade();
                    iga.setDateUpdated();
                    iga.setContributor(Integer.parseInt(contributor));
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(igaDAO.EncodeIGA(iga)){
                    x = true;
                    listIGA.add(iga);
                } else{
                    x = false;
                }
            }
            */
        }
        if(x==true){
             ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_IGA.jsp");
            request.setAttribute("success", "success");
            rd.forward(request, response);
        } else {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/Error.jsp");
            request.setAttribute("Error", "Error");
            rd.forward(request, response);
        }
    }

}