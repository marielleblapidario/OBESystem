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
        ArrayList<IGA> existingIGA = new ArrayList<>();
        IgaDAO igaDAO = new IgaDAO();
        boolean x = true;
        boolean checkIfExist = false;

        try {
            existingIGA = igaDAO.getAllIGA();
        } catch (ParseException ex) {
            Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
        }

        String contributor = request.getParameter("contributor");
        String[] codeIGA = request.getParameterValues("codeIGA");
        String[] description = request.getParameterValues("description");
        String[] remarks = request.getParameterValues("remarks");

        System.out.println("contributor: " + contributor);
        System.out.println("array size: " + codeIGA.length);

        for (int y = 0; y < codeIGA.length; y++) {
            System.out.println("codeIGA: " + codeIGA[y]);
            System.out.println("description: " + description[y]);
            System.out.println("remarks: " + remarks[y]);

            IGA iga = new IGA();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingIGA.size(); a++) {
                //existing entry
                if (existingIGA.get(a).getCodeIGA().equalsIgnoreCase(codeIGA[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingIGA.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingIGA.get(position).getRemarks().equalsIgnoreCase(remarks[y])) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        iga.setCodeIGA(codeIGA[y]);
                        iga.setDescription(description[y]);
                        iga.setRemarks(remarks[y]);
                        iga.setDateUpdated();
                        iga.setContributor(Integer.parseInt(contributor));
                        if (igaDAO.updateIGA(iga)) {
                            x = true;
                        } else {
                            x = false;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                checkIfExist = false;
            } // new IGA entity
            else {
                System.out.println("entered new IGA entity");
                try {
                    iga.setCodeIGA(codeIGA[y]);
                    iga.setDescription(description[y]);
                    iga.setRemarks(remarks[y]);
                    iga.setDateMade();
                    iga.setDateUpdated();
                    iga.setContributor(Integer.parseInt(contributor));
                    if (igaDAO.EncodeIGA(iga)) {
                        System.out.println("entered creation");
                        x = true;
                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/view_course.jsp");
            request.setAttribute("sucesss", "success");
            rd.forward(request, response);
            /*
            response.setContentType("text/html;charset=UTF-8");
             ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_IGA.jsp");
            request.setAttribute("success", "success");
            rd.forward(request, response); */
        } else {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/Error.jsp");
            request.setAttribute("Error", "Error");
            rd.forward(request, response);
        }
    }

}
