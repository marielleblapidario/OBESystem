/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProgramDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Program;

/**
 *
 * @author mariellelapidario
 */
public class EncodeProgram extends BaseServlet {

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
            ProgramDAO programDAO = new ProgramDAO();
            Program program = new Program();
            System.out.println("1");
            String contributor = request.getParameter("contributor");
            System.out.println("contributor: " + contributor);
            String title = request.getParameter("title");
            System.out.println("title: " + title);
            String codeProgram = request.getParameter("codeProgram");
            System.out.println("codeProgram: " + codeProgram);
            String college = request.getParameter("college");
            System.out.println("selected college: " + college);
            String units = request.getParameter("units");
            System.out.println("units: " + units);
            String description = request.getParameter("description");
            System.out.println("description: " + description);

            program.setCodeProgram(codeProgram);
            program.setTitle(title);
            program.setCollege(Integer.parseInt(college));
            program.setUnits(Integer.parseInt(units));
            program.setDescription(description);
            program.setDateMade();
            program.setDateUpdated();
            program.setContributor(Integer.parseInt(contributor));

            if (programDAO.encodeProgram(program)) {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/view/view_programs_list.jsp");
                request.setAttribute("Error", "Error");
                rd.forward(request, response);
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
