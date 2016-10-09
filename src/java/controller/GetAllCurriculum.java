/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CurriculumDAO;
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
import model.Curriculum;

/**
 *
 * @author mariellelapidario
 */
public class GetAllCurriculum extends BaseServlet {

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
        CurriculumDAO dao = new CurriculumDAO();
        ArrayList <Curriculum> cr = new ArrayList<>();
        
        try(PrintWriter out = response.getWriter()){
            cr = dao.getAllCurriculum();
            System.out.println("size: " + dao);
           Gson g = new Gson();
           String s = g.toJson(new CurriculumDAO().getAllCurriculum());
           out.printf(s);
        } catch (ParseException ex) {
            Logger.getLogger(GetAllCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
