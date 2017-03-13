/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CourseOfferingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CourseOffering;

/**
 *
 * @author mariellelapidario
 */
public class EditOffering extends BaseServlet {

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
        CourseOffering offering = new CourseOffering();
        CourseOfferingDAO offeringDAO = new CourseOfferingDAO();
        boolean x = true;
                
        String offeringIDs = request.getParameter("offeringID");
        String facultys = request.getParameter("faculty");        
        String section = request.getParameter("section");
        String days = request.getParameter("days");
        String time = request.getParameter("time");
        String contributorS = request.getParameter("contributor");
        
        System.out.println("faculty: " + facultys);
        System.out.println("section: " + section);
        System.out.println("days: " + days);
        System.out.println("time: " + time);

        int faculty = Integer.parseInt(facultys);
        int offeringID = Integer.parseInt(offeringIDs);
        int contributor = Integer.parseInt(contributorS);
        
        offering.setOfferingID(offeringID);
        offering.setSection(section);
        offering.setDays(days);
        offering.setTime(time);
        offering.setFaculty(faculty);
        offering.setContributor(contributor);
                
        if (offeringDAO.updateOffering(offering)) {
        } else {
            x = false;
        }        
        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/view_course_offering.jsp");
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
