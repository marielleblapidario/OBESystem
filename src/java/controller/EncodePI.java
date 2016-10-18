/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import DAO.PiDAO;
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
import model.Course;
import model.MapCourseToProgram;
import model.PI;

/**
 *
 * @author mariellelapidario
 */
public class EncodePI extends BaseServlet {

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
        PiDAO piDAO = new PiDAO();
        ArrayList<PI> existingPI = new ArrayList<>();
        Boolean x = true;
        Boolean checkIfExist = false;
        
        String codeProgram = request.getParameter("program-title");
        System.out.println("codeProgram: " + codeProgram);
        String codePO = request.getParameter("select-PO");
        System.out.println("codeProgram: " + codePO);
        
        try {
            existingPI = piDAO.getAllMapPItoPO(codePO);
        } catch (ParseException ex) {
            Logger.getLogger(EncodePI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String contributor = request.getParameter("contributor");
        String[] codePI = request.getParameterValues("codePI");
        String[] description = request.getParameterValues("description");
        String status = "pending";
        String remarks = request.getParameter("remarks");
        String checker = request.getParameter("select-approver");
        
        System.out.println("contributor: " + contributor);
        System.out.println("checker: " + checker);

        for (int y = 0; y < description.length; y++) {
            System.out.println("codePA: " + codePI[y]);
            System.out.println("description: " + description[y]);
            System.out.println("remarks: " + remarks);

            PI pa = new PI();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingPI.size(); a++) {
                //existing entry
                if (existingPI.get(a).getCodePI().equalsIgnoreCase(codePI[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingPI.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingPI.get(position).getRemarks().equalsIgnoreCase(remarks)) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        pa.setCodePI(codePI[y]);
                        pa.setProgram(codeProgram);
                        pa.setDescription(description[y]);
                        pa.setStatus(status);
                        pa.setRemarks(remarks);
                        pa.setDateUpdated();
                        pa.setContributor(Integer.parseInt(contributor));
                        pa.setChecker(Integer.parseInt(checker));
                        if (piDAO.updatePI(pa)) {
                        } else {
                            x = false;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(EncodePI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                checkIfExist = false;
            } // new PA entity
            else {
                try {
                    pa.setCodePI(codePI[y]);
                    pa.setProgram(codeProgram);
                    pa.setDescription(description[y]);
                    pa.setStatus(status);
                    pa.setRemarks(remarks);
                    pa.setDateMade();
                    pa.setDateUpdated();
                    pa.setContributor(Integer.parseInt(contributor));
                    pa.setChecker(Integer.parseInt(checker));
                    if (piDAO.encodePI(pa)) {
                        System.out.println("PA created");
                        PI mapping = new PI();

                        mapping.setCodePI(codePI[y]);
                        mapping.setCodePO(codePO);

                        if (piDAO.EncodeMapPItoPO(mapping)) {
                        } else {
                            x = false;
                        }

                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_PI.jsp");
            request.setAttribute("sucesss", "success");
            rd.forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/Error.jsp");
            request.setAttribute("Error", "Error");
            rd.forward(request, response);
        }

    }
}
