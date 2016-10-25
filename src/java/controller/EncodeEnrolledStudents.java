/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mariellelapidario
 */
public class EncodeEnrolledStudents extends BaseServlet {

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
            StudentDAO studentDAO = new StudentDAO();
            boolean x = true;

            ArrayList<Integer> arrStudentID = new ArrayList<>();
            ArrayList<Integer> arrOfferingID = new ArrayList<>();

            Object obj;
            String data = request.getParameter("jsonData");
            System.out.println("json data: " + data);
            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(data);
            JSONArray jsonArray = (JSONArray) obj;

            //get parameters from json
            for (int a = 0; a < jsonArray.size(); a++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(a);
                String studentID = (String) jsonObject.get("studentID");
                String offeringID = (String) jsonObject.get("offeringID");
                if (!studentID.isEmpty()) {
                    arrStudentID.add(Integer.parseInt(studentID));
                    arrOfferingID.add(Integer.parseInt(offeringID));
                }
            }
            
            boolean delete = studentDAO.deleteEnrolledStudents(arrOfferingID.get(0));
            System.out.println("deleted: " + delete);
            
            for (int y = 0; y < arrStudentID.size(); y++) {
                Student temp = new Student();
                temp.setStudentID(arrStudentID.get(y));
                temp.setOfferingID(arrOfferingID.get(y));
                if (studentDAO.encodeEnrolledStudent(temp)) {
                } else {
                    x = false;
                }
            }
            PrintWriter out = response.getWriter();
            out.print(x);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeEnrolledStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
