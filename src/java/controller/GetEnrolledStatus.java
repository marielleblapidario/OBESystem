/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.Student;

/**
 *
 * @author mariellelapidario
 */
public class GetEnrolledStatus extends BaseServlet {

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
            Gson g = new Gson();
            String s = null;
            boolean uploaded = false;
            int offeringID = Integer.parseInt(request.getParameter("offeringID"));
            System.out.println("selected syllabusID: " + offeringID);
            ArrayList<Student> students= new StudentDAO().getEnrolledStudents(offeringID);
            if (students.size() > 0){
                uploaded = true;
            }
            s = g.toJson(uploaded);
            PrintWriter out = response.getWriter();
            System.out.println(s);
            out.print(s);
        } catch (ParseException ex) {
            Logger.getLogger(GetEnrolledStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
