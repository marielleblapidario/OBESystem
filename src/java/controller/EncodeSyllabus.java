/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SyllabusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Syllabus;

/**
 *
 * @author mariellelapidario
 */
public class EncodeSyllabus extends BaseServlet {

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
            Syllabus syllabus = new Syllabus();
            SyllabusDAO syllabusDAO = new SyllabusDAO();
            boolean x = true;
            
            String mapCurID = request.getParameter("mapCurID");
            String curriculumID = request.getParameter("curriculumID");
            String courseID = request.getParameter("courseID");
            String term = request.getParameter("term");
            String startYear = request.getParameter("startYear");
            String endYear = request.getParameter("endYear");
            String contributor = request.getParameter("contributor");
            
            System.out.println("mapCurID: " + mapCurID);
            System.out.println("curriculumID: " + curriculumID);
            System.out.println("courseID: " + courseID);
            System.out.println("term: " + term);
            System.out.println("startYear: " + startYear);
            System.out.println("endYear: " + endYear);
            System.out.println("contributor: " + contributor);
            
            syllabus.setMapCurID(Integer.parseInt(mapCurID));
            syllabus.setCurriculumID(Integer.parseInt(curriculumID));
            syllabus.setCourseID(Integer.parseInt(courseID));
            syllabus.setTerm(Integer.parseInt(term));
            syllabus.setStartYear(Integer.parseInt(startYear));
            syllabus.setEndYear(Integer.parseInt(endYear));
            syllabus.setContributor(Integer.parseInt(contributor));
            syllabus.setDateMade();
            syllabus.setDateUpdated();
            
            if (syllabusDAO.encodeSyllabus(syllabus)) {
            } else {
                x = false;
            }
            PrintWriter out = response.getWriter();
            out.print(x);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeSyllabus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
