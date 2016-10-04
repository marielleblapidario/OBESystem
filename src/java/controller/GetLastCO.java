/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CoDAO;
import DAO.PaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class GetLastCO extends BaseServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String SelectedCourse = request.getParameter("SelectedCourse");
            Gson g = new Gson();
            String s = "";
            try {
                s = g.toJson(new CoDAO().getLastCodeCO(SelectedCourse));
            } catch (SQLException ex) {
                Logger.getLogger(GetLastPA.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.print(s);
        }
    }
}
