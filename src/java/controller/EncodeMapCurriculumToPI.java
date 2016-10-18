/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MapCurriculumToPiDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MapCurriculumCoursesToPI;

/**
 *
 * @author mariellelapidario
 */
public class EncodeMapCurriculumToPI extends BaseServlet {

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
        MapCurriculumToPiDAO dao = new MapCurriculumToPiDAO();
        MapCurriculumCoursesToPI mapping = new MapCurriculumCoursesToPI();
        ArrayList<MapCurriculumCoursesToPI> existingMapping = new ArrayList<>();
        ArrayList<Integer> arrMapCurID = new ArrayList<>();
        ArrayList<Integer> arrCourseID = new ArrayList<>();
        ArrayList<String> arrCodePI = new ArrayList<>();
        int arrCurriculumID = 0;
        boolean checkIfExist = false;
        boolean x = true;

        try {
            Object obj;
            String data = request.getParameter("jsonData");
            System.out.println("json data: " + data);
            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(data);
            JSONArray jsonArray = (JSONArray) obj;

            for (int a = 0; a < jsonArray.size(); a++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(a);
                 String mapCurID = (String) jsonObject.get("mapCurID");
                String courseID = (String) jsonObject.get("courseID");
                String codePI = (String) jsonObject.get("codePI");
                String curriculumID = (String) jsonObject.get("curriculumID");
                arrMapCurID.add(Integer.parseInt(mapCurID));
                arrCourseID.add(Integer.parseInt(courseID));
                arrCodePI.add(codePI);
                arrCurriculumID = Integer.parseInt(curriculumID);
            }

            try {
                existingMapping = dao.getCurriculumMapping(arrCurriculumID);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(EncodeMapCurriculumToPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int y = 0; y < arrCourseID.size(); y++) {
                //compare existing mapping in database with the mapping from JSP
                for (int a = 0; a < existingMapping.size(); a++) {
                    if (existingMapping.get(a).getCourseID() == arrCourseID.get(y)
                            && existingMapping.get(a).getCodePI().equalsIgnoreCase(arrCodePI.get(y))) {
                        checkIfExist = true;
                    }
                }
                if (checkIfExist == false) {
                    mapping.setCurriculumID(arrCurriculumID);
                    mapping.setCourseID(arrCourseID.get(y));
                    mapping.setCodePI(arrCodePI.get(y));
                    mapping.setMapCurID(arrMapCurID.get(y));
                    if (dao.encodeMapCurriculumToCourse(mapping)) {
                    } else {
                        x = false;
                    }
                }
                checkIfExist = false;
            }

            //check for deleted
            System.out.println("check for deleted");
            System.out.println("old: " + existingMapping.size());
            System.out.println("new: " + arrCourseID.size());
            boolean exist = false;
            ArrayList<MapCurriculumCoursesToPI> arrDeleteMap = new ArrayList<>();
            for (int a = 0; a < existingMapping.size(); a++) {
                for (int b = 0; b < arrCourseID.size(); b++) {
                    if (existingMapping.get(a).getCourseID() == arrCourseID.get(b)
                            && existingMapping.get(a).getCodePI().equalsIgnoreCase(arrCodePI.get(b))) {
                        exist = true;
                    }
                }
                if (exist == false) {
                    MapCurriculumCoursesToPI deleteMap = new MapCurriculumCoursesToPI();
                    deleteMap.setCurriculumID(arrCurriculumID);
                    deleteMap.setCourseID(existingMapping.get(a).getCourseID());
                    deleteMap.setCodePI(existingMapping.get(a).getCodePI());
                    arrDeleteMap.add(deleteMap);
                }
                exist = false;
            }
            //delete map
            for (int a = 0; a < arrDeleteMap.size(); a++) {
                if (dao.deleteMapping(arrDeleteMap.get(a))) {
                } else {
                    x = false;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(EncodeMapCurriculumToPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
