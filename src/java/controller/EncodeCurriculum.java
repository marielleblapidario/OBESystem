/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CurriculumDAO;
import DAO.MapCurriculumToCourseDAO;
import com.google.gson.Gson;
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
import model.Curriculum;
import model.MapCourseToProgram;
import model.MapCurriculumToCourse;

/**
 *
 * @author mariellelapidario
 */
public class EncodeCurriculum extends BaseServlet {

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
        CurriculumDAO curriculumDAO = new CurriculumDAO();
        Curriculum curriculum = new Curriculum();
        Boolean checkCreation = true;
        String contributor = request.getParameter("contributor");
        System.out.println("contributor: " + contributor);
        String title = request.getParameter("title");
        String codeProgram = request.getParameter("select-program");
        String description = request.getParameter("description");
        String startYear = request.getParameter("startYear");
        String endYear = request.getParameter("endYear");
        curriculum.setTitle(title);
        curriculum.setProgram(codeProgram);
        curriculum.setStartYear(Integer.parseInt(startYear));
        System.out.println("start year: " + startYear);
        curriculum.setEndYear(Integer.parseInt(endYear));
        System.out.println("end year: " + endYear);
        curriculum.setDescription(description);
        curriculum.setContributor(Integer.parseInt(contributor));

        String[] codeCourse = request.getParameterValues("codeCourse");
        String[] courseID = request.getParameterValues("courseID");
        String[] term = request.getParameterValues("term");
        String[] yearLevel = request.getParameterValues("yearLevel");
        String[] preRequisite = request.getParameterValues("prerequisite");

        System.out.println("codeCourse array size: " + courseID.length);
        System.out.println("preRequisite array size: " + preRequisite.length);
        if (curriculumDAO.encodeCurriculum(curriculum)) {
            System.out.println("created curriculum");
            MapCurriculumToCourseDAO mapping = new MapCurriculumToCourseDAO();
            //gets the last codeCurriculum in curriculum table
            int createdCurriculum = 0;
            try {
                createdCurriculum = curriculumDAO.getLastCodeCurriculum();
            } catch (SQLException ex) {
                Logger.getLogger(EncodeCurriculum.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int x = 0; x < courseID.length; x++) {
                MapCurriculumToCourse temp = new MapCurriculumToCourse();
                System.out.println("courseID: " + courseID[x]);
                temp.setCurriculumID(createdCurriculum);
                temp.setCourseID(Integer.parseInt(courseID[x]));
                temp.setTerm(Integer.parseInt(term[x]));
                temp.setYearLevel(Integer.parseInt(yearLevel[x]));
                temp.setPreRequisite(Integer.parseInt(preRequisite[x]));

                if (mapping.encodeMapCurriculumToCourse(temp) == false) {
                    checkCreation = false;
                }
            }
            if (checkCreation) {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/view/view_curriculums_list.jsp");
                request.setAttribute("success", "success");
                rd.forward(request, response);
            } else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/view/Error.jsp");
                request.setAttribute("Error", "Error");
                rd.forward(request, response);
            }
        } else {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/Error.jsp");
            request.setAttribute("Error", "Error");
            rd.forward(request, response);
        }

    }
}
