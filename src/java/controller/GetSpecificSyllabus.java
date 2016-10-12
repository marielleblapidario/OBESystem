/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SyllabusDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mariellelapidario
 */
public class GetSpecificSyllabus  extends BaseServlet {

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
            int curriculumID = Integer.parseInt(request.getParameter("curriculumID"));
            System.out.println("selected curriculumID: " + curriculumID);
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            System.out.println("selected courseID: " + courseID);
            int term = Integer.parseInt(request.getParameter("term"));
            System.out.println("selected term: " + term);
            s = g.toJson(new SyllabusDAO().getSpecificSyllabus(curriculumID,courseID,term));
        } catch (ParseException ex) {
            Logger.getLogger(GetSpecificCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PrintWriter out = response.getWriter();
        out.print(s);
    }
}