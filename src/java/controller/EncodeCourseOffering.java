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
        Assessment at = new Assessment();
        AssessmentDAO atDAO = new AssessmentDAO();
        ArrayList<Assessment> arrAt = new ArrayList<>();
        boolean x = true;
        int offeringID = 0;
        String syllabusIDs = request.getParameter("syllabusID");
        String curriculumIDs = request.getParameter("curriculumID");
        String courseIDs = request.getParameter("courseID");
        String terms = request.getParameter("term");
        String rooms = request.getParameter("room");
        String facultys = request.getParameter("faculty");

        System.out.println("syllabusID: " + syllabusIDs);
        System.out.println("curriculumID: " + curriculumIDs);
        System.out.println("courseID: " + courseIDs);
        System.out.println("term: " + terms);

        int syllabusID = Integer.parseInt(syllabusIDs);
        int curriculumID = Integer.parseInt(curriculumIDs);
        int courseID = Integer.parseInt(courseIDs);
        int term = Integer.parseInt(terms);
        int room = Integer.parseInt(rooms);
        int faculty = Integer.parseInt(facultys);
        String section = request.getParameter("section");
        String days = request.getParameter("days");
        String time = request.getParameter("time");
        String[] codeAT = request.getParameterValues("codeAT");
        String[] title = request.getParameterValues("title");
        String[] coID = request.getParameterValues("codeCO");
        String[] description = request.getParameterValues("description");
        String[] weight = request.getParameterValues("weight");

        offering.setCurriculumID(curriculumID);
        offering.setCourseID(courseID);
        offering.setTerm(term);
        offering.setSection(section);
        offering.setDays(days);
        offering.setTime(time);
        offering.setRoom(room);
        offering.setFaculty(faculty);

        System.out.println("syllabusID: " + syllabusID);
        System.out.println("codAt size: " + codeAT.length);
        System.out.println("coID size: " + coID.length);

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
