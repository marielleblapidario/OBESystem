/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CourseDAO;
import DAO.MapCourseToProgramDAO;
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

/**
 *
 * @author mariellelapidario
 */
public class EditCourse extends BaseServlet {

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
            Boolean checkIfExist = false;

            String contributor = request.getParameter("contributor");
            System.out.println("contributor: " + contributor);
            String title = request.getParameter("title");
            System.out.println("title: " + title);
            String codeCourse = request.getParameter("codeCourse");
            System.out.println("codeCourse: " + codeCourse);
            String[] codeProgram = request.getParameterValues("program");
            System.out.println("selected program: " + codeProgram.length);
            String units = request.getParameter("units");
            System.out.println("description: " + units);
            String description = request.getParameter("description");
            System.out.println("description: " + description);
            String courseIDs = request.getParameter("courseID");
            int courseID = Integer.parseInt(courseIDs);
            System.out.println("courseID: " + courseID);

            course.setCodeCourse(codeCourse);
            course.setTitle(title);
            course.setUnits(Integer.parseInt(units));
            course.setDescription(description);
            course.setDateUpdated();
            course.setContributor(Integer.parseInt(contributor));
            course.setCourseID(courseID);

            if (courseDAO.updateCourse(course)) {
                System.out.println("created course");
                MapCourseToProgramDAO mapping = new MapCourseToProgramDAO();
                ArrayList<MapCourseToProgram> existingMapping = new ArrayList<>();
                //get all existing mapping
                existingMapping = mapping.getSpecificMapCourseToProgram(courseID);
                for (int x = 0; x < codeProgram.length; x++) {
                    //check if existing
                    for (int y = 0; y < existingMapping.size(); y++) {
                        //if existing entry
                        if (codeProgram[x].equalsIgnoreCase(existingMapping.get(y).getCodeProgram())) {
                            checkIfExist = true;
                        }
                    }
                    //if not existing encode new mapping
                    if (!checkIfExist) {
                        MapCourseToProgram temp = new MapCourseToProgram();
                        System.out.println("courseID: " + courseID);
                        System.out.println("codeProgram:" + codeProgram[x]);
                        temp.setCourseID(courseID);
                        temp.setCodeProgram(codeProgram[x]);
                        if (mapping.encodeMapCourseToProgram(temp) == false) {
                            checkCreation = false;
                        }
                    }
                    checkIfExist = false;
                }
                //check if deleted
                System.out.println("check for deleted");
                ArrayList<MapCourseToProgram> code = new ArrayList<>();
                boolean exist = false;
                for (int x = 0; x < existingMapping.size(); x++) {
                    for (int y = 0; y < codeProgram.length; y++) {
                        if(existingMapping.get(x).getCodeProgram().equalsIgnoreCase(codeProgram[y])){
                            System.out.println( existingMapping.get(x).getCodeProgram() + " exists");
                            exist = true;
                        }
                    }
                    if(!exist){
                        MapCourseToProgram temp = new MapCourseToProgram();
                        temp.setCodeProgram(existingMapping.get(x).getCodeProgram());
                        temp.setCourseID(courseID);
                        code.add(temp);
                        System.out.println("iga to delete: " + temp.getCodeProgram());
                    }
                }
                for(int x = 0; x < code.size(); x++){
                    if(mapping.deleteMapping(code.get(x))){
                    } else {
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
        }

    }
}
