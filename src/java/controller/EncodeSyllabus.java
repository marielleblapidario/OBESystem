/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CoDAO;
import DAO.SyllabusDAO;
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
import model.CO;
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
        ArrayList<CO> existingCO = new ArrayList<>();
        CoDAO coDAO = new CoDAO();
        Syllabus syllabus = new Syllabus();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        boolean x = true;
        
        String curriculumID = request.getParameter("curriculumID");
        String courseID = request.getParameter("courseID");
        String term = request.getParameter("term");
        String[] codeCO = request.getParameterValues("codeCO");
        String[] description = request.getParameterValues("description");
        String[] status = request.getParameterValues("status");
        String[] remarks = request.getParameterValues("remarks");
        String[] codePI = request.getParameterValues("codePI");
        String contributor = request.getParameter("contributor");
        
        System.out.println("curriculumID: " + curriculumID);
        System.out.println("courseID: " + courseID);
        System.out.println("term: " + term);
        System.out.println("codeCO length: " + codeCO.length);
        System.out.println("codePI length: " + codePI.length);
        syllabus.setCurriculumID(Integer.parseInt(curriculumID));
        syllabus.setCourseID(Integer.parseInt(courseID));
        syllabus.setTerm(Integer.parseInt(term));
        
        if(syllabusDAO.encodeSyllabus(syllabus)){
            System.out.println("create syllabus success");
            for(int y = 0; y < codeCO.length; y++){
                try {
                    CO co = new CO();
                    co.setCurriculumID(Integer.parseInt(curriculumID));
                    co.setCourseID(Integer.parseInt(courseID));
                    co.setTerm(Integer.parseInt(term));
                    co.setCodeCO(codeCO[y]);
                    co.setDescription(description[y]);
                    co.setStatus(status[y]);
                    co.setRemarks(remarks[y]);
                    co.setDateMade();
                    co.setDateUpdated();
                    co.setContributor(Integer.parseInt(contributor));
                    if(coDAO.encodeCO(co)){
                        System.out.println("create CO success");
                        CO mapCO = new CO();
                        mapCO.setCurriculumID(Integer.parseInt(curriculumID));
                        mapCO.setCourseID(Integer.parseInt(courseID));
                        mapCO.setTerm(Integer.parseInt(term));
                        mapCO.setCodeCO(codeCO[y]);
                        mapCO.setCodePI(codePI[y]);
                        if(coDAO.mapCOtoPI(mapCO)){
                            System.out.println("create mapping sucess");
                        }
                        else{
                            x = false;
                        }
                    } else {
                        x = false;                    
                    }                    
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeSyllabus.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } else {
            x = false;
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
