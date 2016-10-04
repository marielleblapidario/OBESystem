/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssessmentDAO;
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
import model.Assessment;

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
        ArrayList<Assessment> existingAssessment = new ArrayList<>();
        AssessmentDAO assessmentDAO = new AssessmentDAO();
        boolean x = true;
        boolean checkIfExist = false;
        
        String codeCourse = request.getParameter("codeCourse");
        System.out.println("codeCourse: " + codeCourse);
        
        try {
            existingAssessment = assessmentDAO.getAllAssessment(codeCourse);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String contributor = request.getParameter("contributor");
        String checker = request.getParameter("select-approver");
        String[] codeAssessment = request.getParameterValues("codeAssessment");
        String[] type = request.getParameterValues("AType");
        String[] description = request.getParameterValues("description");
        String[] weight = request.getParameterValues("weight");
        String[] status = request.getParameterValues("status");
        String[] remarks = request.getParameterValues("remarks");
        
        System.out.println("contributor: " + contributor);
        System.out.println("checker: " + checker);
        System.out.println("array size: " + codeAssessment.length);

        for (int y = 0; y < codeAssessment.length; y++) {
            System.out.println("codePO: " + codeAssessment[y]);
            System.out.println("type: " + type[y]);
            System.out.println("description: " + description[y]);
            System.out.println("weight: " + weight[y]);
            System.out.println("status" + status[y]);
            System.out.println("remarks: " + remarks[y]);

            Assessment assessment = new Assessment();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingAssessment.size(); a++) {
                //existing entry
                if (existingAssessment.get(a).getCodeAssessment().equalsIgnoreCase(codeAssessment[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingAssessment.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingAssessment.get(position).getRemarks().equalsIgnoreCase(remarks[y])
                        && existingAssessment.get(position).getWeight() == Double.parseDouble(weight[y])) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        assessment.setType(Integer.parseInt(type[y]));
                        assessment.setDescription(description[y]);
                        assessment.setWeight(Double.parseDouble(weight[y]));
                        assessment.setStatus(status[y]);
                        assessment.setRemarks(remarks[y]);
                        assessment.setDateUpdated();
                        assessment.setContributor(Integer.parseInt(contributor));
                        assessment.setCodeAssessment(codeAssessment[y]);
                        if (assessmentDAO.updateAssessment(assessment)) {
                            x = true;
                        } else {
                            x = false;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(EncodeAssessment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                checkIfExist = false;
            } // new IGA entity
            else {
                System.out.println("entered new IGA entity");
                try {
                    assessment.setCodeAssessment(codeAssessment[y]);
                    assessment.setCourse(codeCourse);
                    assessment.setType(Integer.parseInt(type[y]));
                    assessment.setDescription(description[y]);
                    assessment.setWeight(Double.parseDouble(weight[y]));
                    assessment.setStatus(status[y]);
                    assessment.setRemarks(remarks[y]);
                    assessment.setDateMade();
                    assessment.setDateUpdated();
                    assessment.setContributor(Integer.parseInt(contributor));
                    assessment.setChecker(Integer.parseInt(checker));
                    if (assessmentDAO.encodeCO(assessment)) {
                        System.out.println("entered creation");
                        x = true;
                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeAssessment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        //check for deleted
        System.out.println("check for deleted");
        ArrayList<String> code = new ArrayList<>();
        boolean exist = false;
        System.out.println("old PA list: " + existingAssessment.size());
        System.out.println("new PA list: " + codeAssessment.length);
        for (int a = 0; a < existingAssessment.size(); a++) {
            for (int b = 0; b < codeAssessment.length; b++) {
                if (existingAssessment.get(a).getCodeAssessment().equalsIgnoreCase(codeAssessment[b])) {
                    exist = true;
                }
            }
            if (exist == false) {
                code.add(existingAssessment.get(a).getCodeAssessment());
                System.out.println("iga to delete: " + existingAssessment.get(a).getCodeAssessment());
            }
            exist = false;
        }
        //delete Assessment
        for (int c = 0; c < code.size(); c++) {
            if (assessmentDAO.deleteAssessment(code.get(c))) {
            } else {
                x = false;
            }
        }

        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/search_assessment.jsp");
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
