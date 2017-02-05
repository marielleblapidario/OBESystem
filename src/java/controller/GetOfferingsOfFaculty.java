/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CourseOfferingDAO;
import DAO.GradeDAO;
import DAO.StudentDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CourseOffering;
import model.Grade;
import model.Student;

/**
 *
 * @author mariellelapidario
 */
public class GetOfferingsOfFaculty extends BaseServlet {

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
        Gson g = new Gson();
        String s = null;
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            System.out.println("selected userID: " + userID);
            ArrayList<CourseOffering> offerings = new CourseOfferingDAO().getOfferingsOfFaculty(userID);
            
            String status = "pending uploads";
            for (int x = 0; x < offerings.size(); x++) {
                ArrayList<Student> students = new StudentDAO().getEnrolledStudents(offerings.get(x).getOfferingID());
                if(students.size() > 0){
                    ArrayList<Grade> grades = new GradeDAO().getAllGradesOfSection(offerings.get(x).getOfferingID());
                    if (grades.size() > 0){
                        status = "grades uploaded";
                    } else {
                        status = "students uploaded";
                    }
                }
                System.out.println(status);
                offerings.get(x).setStatus(status); 
                status = "pending uploads";
            }

            s = g.toJson(offerings);
        } catch (ParseException ex) {
            Logger.getLogger(GetAllOfferings.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter out = response.getWriter();
        out.print(s);
    }
}
