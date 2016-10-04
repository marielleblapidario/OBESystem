/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CoDAO;
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

/**
 *
 * @author mariellelapidario
 */
public class EncodeCO extends BaseServlet {

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
        boolean x = true;
        boolean checkIfExist = false;
        
        String codeCourse = request.getParameter("codeCourse");
        System.out.println("codeCourse: " + codeCourse);
        
        try {
            existingCO = coDAO.getAllCO(codeCourse);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeCO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String contributor = request.getParameter("contributor");
        String checker = request.getParameter("select-approver");
        String[] codeCO = request.getParameterValues("codeCO");
        String[] description = request.getParameterValues("description");
        String[] weight = request.getParameterValues("weight");
        String[] status = request.getParameterValues("status");
        String[] remarks = request.getParameterValues("remarks");
        
        System.out.println("contributor: " + contributor);
        System.out.println("checker: " + checker);
        System.out.println("array size: " + codeCO.length);

        for (int y = 0; y < codeCO.length; y++) {
            System.out.println("codePO: " + codeCO[y]);
            System.out.println("description: " + description[y]);
            System.out.println("weight: " + weight[y]);
            System.out.println("status" + status[y]);
            System.out.println("remarks: " + remarks[y]);

            CO co = new CO();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingCO.size(); a++) {
                //existing entry
                if (existingCO.get(a).getCodeCO().equalsIgnoreCase(codeCO[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingCO.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingCO.get(position).getRemarks().equalsIgnoreCase(remarks[y])
                        && existingCO.get(position).getWeight() == Double.parseDouble(weight[y])) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        
                        co.setDescription(description[y]);
                        co.setWeight(Double.parseDouble(weight[y]));
                        co.setStatus(status[y]);
                        co.setRemarks(remarks[y]);
                        co.setDateUpdated();
                        co.setContributor(Integer.parseInt(contributor));
                        co.setCodeCO(codeCO[y]);
                        if (coDAO.updateCO(co)) {
                        } else {
                            x = false;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(EncodePO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                checkIfExist = false;
            } // new IGA entity
            else {
                System.out.println("entered new IGA entity");
                try {
                    co.setCodeCO(codeCO[y]);
                    co.setCourse(codeCourse);
                    co.setDescription(description[y]);
                    co.setWeight(Double.parseDouble(weight[y]));
                    co.setStatus(status[y]);
                    co.setRemarks(remarks[y]);
                    co.setDateMade();
                    co.setDateUpdated();
                    co.setContributor(Integer.parseInt(contributor));
                    co.setChecker(Integer.parseInt(checker));
                    if (coDAO.encodeCO(co)) {
                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeCO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        //check for deleted
        System.out.println("check for deleted");
        ArrayList<String> code = new ArrayList<>();
        boolean exist = false;
        System.out.println("old PA list: " + existingCO.size());
        System.out.println("new PA list: " + codeCO.length);
        for (int a = 0; a < existingCO.size(); a++) {
            for (int b = 0; b < codeCO.length; b++) {
                if (existingCO.get(a).getCodeCO().equalsIgnoreCase(codeCO[b])) {
                    exist = true;
                }
            }
            if (exist == false) {
                code.add(existingCO.get(a).getCodeCO());
                System.out.println("iga to delete: " + existingCO.get(a).getCodeCO());
            }
            exist = false;
        }
        //delete CO
        for (int c = 0; c < code.size(); c++) {
            if (coDAO.deleteCO(code.get(c))) {
            } else {
                x = false;
            }
        }

        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/search_CO.jsp");
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
