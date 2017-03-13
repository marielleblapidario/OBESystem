/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssessmentDAO;
import DAO.CourseOfferingDAO;
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
import model.Assessment;
import model.CourseOffering;

/**
 *
 * @author mariellelapidario
 */
public class EncodeCourseOffering extends BaseServlet {

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
        
        String curriculumIDs = request.getParameter("curriculumID");
        String courseIDs = request.getParameter("courseID");
        String terms = request.getParameter("term");
        String facultys = request.getParameter("faculty");
        String startYears = request.getParameter("startYear");
        String endYears = request.getParameter("endYear");
        String syllabusIDs = request.getParameter("syllabusID");
        String contributorS = request.getParameter("contributor");
        
        String section = request.getParameter("section");
        String days = request.getParameter("days");
        String time = request.getParameter("time");
        
        System.out.println("syllabusID: " + syllabusIDs);
        System.out.println("curriculumID: " + curriculumIDs);
        System.out.println("courseID: " + courseIDs);
        System.out.println("term: " + terms);
        System.out.println("startYear: " + startYears);
        System.out.println("endYear: " + endYears);
        System.out.println("faculty: " + facultys);
        System.out.println("section: " + section);
        System.out.println("days: " + days);
        System.out.println("time: " + time);
        
        int syllabusID = Integer.parseInt(syllabusIDs);
        int curriculumID = Integer.parseInt(curriculumIDs);
        int courseID = Integer.parseInt(courseIDs);
        int term = Integer.parseInt(terms);
        int faculty = Integer.parseInt(facultys);
        int startYear = Integer.parseInt(startYears);
        int endYear = Integer.parseInt(endYears);
        int contributor = Integer.parseInt(contributorS);
        
        offering.setSyllabusID(syllabusID);
        offering.setCurriculumID(curriculumID);
        offering.setCourseID(courseID);
        offering.setTerm(term);
        offering.setStartYear(startYear);
        offering.setEndYear(endYear);
        offering.setSection(section);
        offering.setDays(days);
        offering.setTime(time);
        offering.setFaculty(faculty);
        offering.setContributor(contributor);
        
        if (offeringDAO.encodeOffering(offering)) {
        } else {
            x = false;
        }
        
        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/view_course_offerings_list.jsp");
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
