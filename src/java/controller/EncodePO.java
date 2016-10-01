/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PoDAO;
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
import model.PO;

/**
 *
 * @author mariellelapidario
 */
public class EncodePO extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PO> existingPO = new ArrayList<>();
        PoDAO paDAO = new PoDAO();
        boolean x = true;
        boolean checkIfExist = false;
        
        String codeProgram = request.getParameter("program-title");
        System.out.println("codeProgram: " + codeProgram);
        
        try {
            existingPO = paDAO.getAllPO(codeProgram);
        } catch (ParseException ex) {
            Logger.getLogger(EncodePA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String contributor = request.getParameter("contributor");
        String checker = request.getParameter("select-approver");
        String[] codePO = request.getParameterValues("codePO");
        String[] description = request.getParameterValues("description");
        String[] status = request.getParameterValues("status");
        String[] remarks = request.getParameterValues("remarks");
        
       

        System.out.println("contributor: " + contributor);
        System.out.println("checker: " + checker);
        System.out.println("array size: " + codePO.length);

        for (int y = 0; y < codePO.length; y++) {
            System.out.println("codePO: " + codePO[y]);
            System.out.println("description: " + description[y]);
            System.out.println("status" + status[y]);
            System.out.println("remarks: " + remarks[y]);

            PO po = new PO();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingPO.size(); a++) {
                //existing entry
                if (existingPO.get(a).getCodePO().equalsIgnoreCase(codePO[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingPO.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingPO.get(position).getRemarks().equalsIgnoreCase(remarks[y])) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        po.setCodePO(codePO[y]);
                        po.setProgram(codeProgram);
                        po.setDescription(description[y]);
                        po.setStatus(status[y]);
                        po.setRemarks(remarks[y]);
                        po.setDateUpdated();
                        po.setContributor(Integer.parseInt(contributor));
                        po.setChecker(Integer.parseInt(checker));
                        if (paDAO.updatePA(po)) {
                            x = true;
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
                    po.setCodePO(codePO[y]);
                    po.setProgram(codeProgram);
                    po.setDescription(description[y]);
                    po.setStatus(status[y]);
                    po.setRemarks(remarks[y]);
                    po.setDateMade();
                    po.setDateUpdated();
                    po.setContributor(Integer.parseInt(contributor));
                    po.setChecker(Integer.parseInt(checker));
                    if (paDAO.encodePO(po)) {
                        System.out.println("entered creation");
                        x = true;
                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/view_course.jsp");
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
