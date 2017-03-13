/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CoDAO;
import DAO.SyllabusDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        try {
            ArrayList<CO> existingCO = new ArrayList<>();
            CoDAO coDAO = new CoDAO();
            Syllabus syllabus = new Syllabus();
            SyllabusDAO syllabusDAO = new SyllabusDAO();
            boolean x = true;
            int syllabusID = -1;
            ArrayList<CO> coID = new ArrayList<>();

            ArrayList<String> arrCodeCO = new ArrayList<>();
            ArrayList<String> arrDescription = new ArrayList<>();
            ArrayList<String> arrCodePI = new ArrayList<>();
            ArrayList<String> arrRemarks = new ArrayList<>();
            int arrMapCurID = -1;
            int arrCurriculumID = -1;
            int arrCourseID = -1;
            int arrTerm = -1;
            int arrStartYear = -1;
            int arrEndYear = -1;
            int contributor = -1;

            Object obj;
            String data = request.getParameter("jsonData");
            System.out.println("json data: " + data);
            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(data);
            JSONArray jsonArray = (JSONArray) obj;

            //get parameters from json
            for (int a = 0; a < jsonArray.size(); a++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(a);
                String mapCurID = (String) jsonObject.get("mapCurID");
                String curriculumID = (String) jsonObject.get("curriculumID");
                String courseID = (String) jsonObject.get("courseID");
                String term = (String) jsonObject.get("term");
                String startYear = (String) jsonObject.get("startYear");
                String endYear = (String) jsonObject.get("endYear");
                String codeCO = (String) jsonObject.get("codeCO");
                String description = (String) jsonObject.get("description");
                String codePI = (String) jsonObject.get("codePI");
                String remarks = (String) jsonObject.get("remarks");
                String contributorS = (String) jsonObject.get("contributor");
                arrCodeCO.add(codeCO);
                arrDescription.add(description);
                arrCodePI.add(codePI);
                arrRemarks.add(remarks);
                arrMapCurID = Integer.parseInt(mapCurID);
                arrCurriculumID = Integer.parseInt(curriculumID);
                arrCourseID = Integer.parseInt(courseID);
                arrTerm = Integer.parseInt(term);
                arrStartYear = Integer.parseInt(startYear);
                arrEndYear = Integer.parseInt(endYear);
                contributor = Integer.parseInt(contributorS);
            }

            //get syllabusID
            try {
                syllabusID = syllabusDAO.getLastSyllabusID();
            } catch (SQLException ex) {
                Logger.getLogger(EncodeCO.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int y = 0; y < arrCodeCO.size(); y++) {
                CO co = new CO();
                co.setSyllabusID(syllabusID);
                co.setMapCurID(arrMapCurID);
                co.setCurriculumID(arrCurriculumID);
                co.setCourseID(arrCourseID);
                co.setTerm(arrTerm);
                co.setStartYear(arrStartYear);
                co.setEndYear(arrEndYear);
                co.setCodePI(arrCodePI.get(y));
                co.setCodeCO(arrCodeCO.get(y));
                co.setDescription(arrDescription.get(y));
                co.setRemarks(arrRemarks.get(y));
                co.setContributor(contributor);
                if (coDAO.encodeCO(co)) {
                } else {
                    x = false;
                }
            }
            PrintWriter out = response.getWriter();
            out.print(x);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeCO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
