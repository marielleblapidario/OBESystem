/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CourseDAO;
import DAO.MapCourseToProgramDAO;
import DAO.ProgramDAO;
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
import model.Course;
import model.MapCourseToProgram;
import model.Program;

/**
 *
 * @author mariellelapidario
 */
public class EncodeCourse extends BaseServlet {

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
            CourseDAO courseDAO = new CourseDAO();
            Course course = new Course();
            Boolean checkCreation = true;
            String contributor = request.getParameter("contributor");
            String title = request.getParameter("title");
            String codeCourse = request.getParameter("codeCourse");
            String units = request.getParameter("units");
            String description = request.getParameter("description");

            course.setCodeCourse(codeCourse);
            course.setTitle(title);
            course.setUnits(Integer.parseInt(units));
            course.setDescription(description);
            course.setDateMade();
            course.setDateUpdated();
            course.setContributor(Integer.parseInt(contributor));

            String[] program = request.getParameterValues("select-program");
            System.out.println("program array size: " + program.length);

            if (courseDAO.encodeCourse(course)) {
                System.out.println("created course");
                MapCourseToProgramDAO mapping = new MapCourseToProgramDAO();
                for (int x = 0; x < program.length; x++) {
                    MapCourseToProgram temp = new MapCourseToProgram();
                    int courseID = courseDAO.getLastCourseID();
                    System.out.println("courseID: " + courseID);
                    System.out.println("codeProgram:" + program[x]);
                    temp.setCourseID(courseID);
                    temp.setCodeProgram(program[x]);

                    if (mapping.encodeMapCourseToProgram(temp) == false) {
                        checkCreation = false;
                    }
                }
                if (checkCreation) {
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/view/view_courses_list.jsp");
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
        } catch (SQLException ex) {
            Logger.getLogger(EncodeCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
