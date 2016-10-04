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
        try {
            response.setContentType("text/html;charset=UTF-8");
            CurriculumDAO curriculumDAO = new CurriculumDAO();
            Curriculum curriculum = new Curriculum();
            Boolean checkCreation = true;
            String contributor = request.getParameter("contributor");
            String title = request.getParameter("title");
            String codeProgram = request.getParameter("select-program");
            String description = request.getParameter("description");
            //datepicker
            String range = request.getParameter("range");
            String[] splitted = range.split("-");
            for (int x = 0; x < splitted.length; x++) {
                splitted[x] = splitted[x].replaceAll("\\s+", "");
            }
                                   
            curriculum.setTitle(title);
            curriculum.setProgram(codeProgram);
            curriculum.setStartYear(splitted[0]);
            System.out.println("start year: " + splitted[0]);
            curriculum.setEndYear(splitted[1]);
            System.out.println("end year: " + splitted[1]);
            curriculum.setDescription(description);
            curriculum.setContributor(Integer.parseInt(contributor));
            
            //how to get the courses
            String[] codeCourse = request.getParameterValues("codeCourse");

            System.out.println("codeCourse array size: " + codeCourse.length);

            if (curriculumDAO.encodeCurriculum(curriculum)) {
                System.out.println("created curriculum");
                MapCurriculumToCourseDAO mapping = new MapCurriculumToCourseDAO();
                //gets the last codeCurriculum in curriculum table
                int createdCurriculum = 1000;
                try {
                    createdCurriculum = curriculumDAO.getLastCodeCurriculum();
                } catch (SQLException ex) {
                    Logger.getLogger(EncodeCurriculum.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (int x = 0; x < codeCourse.length; x++) {
                    MapCurriculumToCourse temp = new MapCurriculumToCourse();
                    System.out.println("codeCourse: " + codeCourse[x]);
                    temp.setCodeCurriculum(createdCurriculum);
                    temp.setCodeCourse(codeCourse[x]);

                    if (mapping.encodeMapCurriculumToCourse(temp) == false) {
                        checkCreation = false;
                    }
                }
                if (checkCreation) {
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/view/create_curriculum.jsp");
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

        } catch (ParseException ex) {
            Logger.getLogger(EncodeProgram.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
