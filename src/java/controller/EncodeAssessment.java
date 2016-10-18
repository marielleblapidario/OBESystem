/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssessmentDAO;
import DAO.SyllabusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.Syllabus;

/**
 *
 * @author mariellelapidario
 */
public class EncodeAssessment extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Assessment> existingA = new ArrayList<>();
        AssessmentDAO aDAO = new AssessmentDAO();
        String mapCurID = request.getParameter("mapCurID");
        String curriculumID = request.getParameter("curriculumID");
        String courseID = request.getParameter("courseID");
        String term = request.getParameter("term");
        String startYear = request.getParameter("startYear");
        String endYear = request.getParameter("endYear");
        String[] coID = request.getParameterValues("codeCOA");
        String[] codeAT = request.getParameterValues("codAT");
        String[] title = request.getParameterValues("titleA");
        String[] description = request.getParameterValues("descriptionA");
        String[] weight = request.getParameterValues("weight");
        Syllabus syllabus = new Syllabus();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        boolean x = true;
        int syllabusID = -1;
        try {
            syllabusID = syllabusDAO.getLastSyllabusID();
        } catch (SQLException ex) {
            Logger.getLogger(EncodeCO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int y = 0; y < codeAT.length; y++) {
            Assessment as = new Assessment();
            as.setSyllabusID(syllabusID);
            as.setMapCurID(Integer.parseInt(mapCurID));
            as.setCurriculumID(Integer.parseInt(curriculumID));
            as.setCourseID(Integer.parseInt(courseID));
            as.setTerm(Integer.parseInt(term));
            as.setStartYear(Integer.parseInt(startYear));
            as.setEndYear(Integer.parseInt(endYear));
            as.setCodeAT(codeAT[y]);
            as.setTitle(title[y]);
            as.setCoID(Integer.parseInt(coID[y]));
            as.setDescription(description[y]);
            as.setWeight(Double.parseDouble(weight[y]));
            if (aDAO.encodeAssessment(as)) {
            } else {
                x = false;
            }
        }
        
        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/view_syllabus_list.jsp");
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