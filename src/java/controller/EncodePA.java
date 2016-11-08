/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MapPatoIgaDAO;
import DAO.PaDAO;
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
import model.MapPAtoIGA;
import model.PA;

/**
 *
 * @author mariellelapidario
 */
public class EncodePA extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PA> existingPA = new ArrayList<>();
        PaDAO paDAO = new PaDAO();
        boolean x = true;
        boolean checkIfExist = false;

        String codeProgram = request.getParameter("program-title");
        System.out.println("codeProgram: " + codeProgram);

        try {
            existingPA = paDAO.getAllPA(codeProgram);
        } catch (ParseException ex) {
            Logger.getLogger(EncodePA.class.getName()).log(Level.SEVERE, null, ex);
        }

        String contributor = request.getParameter("contributor");
        String[] codePA = request.getParameterValues("codePA");
        String[] description = request.getParameterValues("description");
        String[] codeIGA = request.getParameterValues("mapIGA");
        String[] remarks = request.getParameterValues("remarks");

        System.out.println("contributor: " + contributor);
        System.out.println("array size: " + codePA.length);

        for (int y = 0; y < codePA.length; y++) {
            System.out.println("codePA: " + codePA[y]);
            System.out.println("description: " + description[y]);
            System.out.println("remarks: " + remarks[y]);

            PA pa = new PA();
            int position = 0;

            //compare existing IGA in database with the IGA from JSP
            for (int a = 0; a < existingPA.size(); a++) {
                //existing entry
                if (existingPA.get(a).getCodePA().equalsIgnoreCase(codePA[y])) {
                    checkIfExist = true;
                    //get place in array for comparing for update
                    position = a;
                }
            }
            //if existing then check if its updated
            if (checkIfExist == true) {
                System.out.println("entered existing");
                //not updated
                if (existingPA.get(position).getDescription().equalsIgnoreCase(description[y])
                        && existingPA.get(position).getRemarks().equalsIgnoreCase(remarks[y])) {

                } //updated IGA
                else {
                    try {
                        System.out.println("entered update");
                        pa.setCodePA(codePA[y]);
                        pa.setProgram(codeProgram);
                        pa.setDescription(description[y]);
                        pa.setRemarks(remarks[y]);
                        pa.setDateUpdated();
                        pa.setContributor(Integer.parseInt(contributor));
                        if (paDAO.updatePA(pa)) {
                        } else {
                            x = false;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(EncodePA.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                checkIfExist = false;
            } // new PA entity
            else {
                try {
                    pa.setCodePA(codePA[y]);
                    pa.setProgram(codeProgram);
                    pa.setDescription(description[y]);
                    pa.setRemarks(remarks[y]);
                    pa.setDateMade();
                    pa.setDateUpdated();
                    pa.setContributor(Integer.parseInt(contributor));
                    if (paDAO.encodePA(pa)) {
                        System.out.println("PA created");
                        MapPatoIgaDAO dao = new MapPatoIgaDAO();
                        MapPAtoIGA mapping = new MapPAtoIGA();

                        mapping.setCodePA(codePA[y]);
                        mapping.setCodeIGA(codeIGA[y]);

                        if (dao.encodeMapPatoIga(mapping)) {
                        } else {
                            x = false;
                        }

                    } else {
                        System.out.println("entered fail");
                        x = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EncodeIGA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //check for deleted
        System.out.println("check for deleted");
        ArrayList<String> code = new ArrayList<>();
        boolean exist = false;
        System.out.println("old PA list: " + existingPA.size());
        System.out.println("new PA list: " + codePA.length);
        for (int a = 0; a < existingPA.size(); a++) {
            for (int b = 0; b < codePA.length; b++) {
                if (existingPA.get(a).getCodePA().equalsIgnoreCase(codePA[b])) {
                    exist = true;
                }
            }
            if (exist == false) {
                code.add(existingPA.get(a).getCodePA());
                System.out.println("iga to delete: " + existingPA.get(a).getCodePA());
            }
            exist = false;
        }
        //delete PA
        for (int c = 0; c < code.size(); c++) {
            if (paDAO.deletePA(code.get(c))) {
            } else {
                x = false;
            }
        }

        if (x == true) {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/view/create_PA.jsp");
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
