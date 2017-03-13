/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssessmentDAO;
import DAO.GradeCoDAO;
import DAO.GradeDAO;
import DAO.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Assessment;
import model.Grade;
import model.GradeCO;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mariellelapidario
 */
public class EncodeStudentGrades extends BaseServlet {

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
            GradeDAO gradeDAO = new GradeDAO();
            boolean x = true;

            ArrayList<Integer> arrOfferingID = new ArrayList<>();
            ArrayList<Grade> arrTempGrade = new ArrayList<>();
            int syllabusID = -1;
            int contributor = 0;

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
                String syllabusIDs = (String) jsonObject.get("syllabusID");
                String type = (String) jsonObject.get("type");
                String grade = (String) jsonObject.get("grade");
                String contributorS = (String) jsonObject.get("contributor");
                contributor = Integer.parseInt(contributorS);
                if (!studentID.isEmpty()) {
                    Grade temp = new Grade();
                    temp.setStudentID(Integer.parseInt(studentID));
                    temp.setOfferingID(Integer.parseInt(offeringID));
                    temp.setSyllabusID(syllabusID);
                    temp.setTypeName(type);
                    temp.setGrade(Double.parseDouble(grade));
                    temp.setContributor(contributor);

                    arrTempGrade.add(temp);
                    arrOfferingID.add(Integer.parseInt(offeringID));
                    syllabusID = Integer.parseInt(syllabusIDs);
                }
            }
            boolean delete = gradeDAO.deleteGrades(arrOfferingID.get(0));
            System.out.println("deleted: " + delete);
            System.out.println("syllabusID: " + syllabusID);
            AssessmentDAO assessDAO = new AssessmentDAO();
            ArrayList<Assessment> arrAssess = new ArrayList<>();
            //get all the types used under the syllabus
            arrAssess = assessDAO.getTypesUnderSyllabus(syllabusID);

            System.out.println("arrAssess size: " + arrAssess.size());
            System.out.println("arrTempeGrade size: " + arrTempGrade.size());

            for (int y = 0; y < arrTempGrade.size(); y++) {
                Grade temp = new Grade();
                temp.setStudentID(arrTempGrade.get(y).getStudentID());
                temp.setOfferingID(arrTempGrade.get(y).getOfferingID());
                temp.setContributor(contributor);
                //sets the grades of each assessment
                for (int a = 0; a < arrAssess.size(); a++) {
                    System.out.println("typeName: " + arrAssess.get(a).getTypeName()
                            + " vs " + arrTempGrade.get(y).getTypeName());
                    if (arrTempGrade.get(y).getTypeName().equalsIgnoreCase(arrAssess.get(a).getTypeName())) {
                        System.out.println("entered if");
                        temp.setAssessmentID(arrAssess.get(a).getAssessmentID());
                        temp.setGrade(arrTempGrade.get(y).getGrade());
                        if (gradeDAO.encodeGrade(temp)) {
                        } else {
                            x = false;
                        }
                    }
                }
            }

            //calculates and encode the CO grade based on the assessment grade
            GradeCoDAO gradeCoDAO = new GradeCoDAO();
            if (gradeCoDAO.deleteGrades(arrOfferingID.get(0))) {
                ArrayList<Grade> arrTemp = new ArrayList<>();
                ArrayList<Integer> arrCO = new ArrayList<>();
                //get students in Offering
                StudentDAO studentDAO = new StudentDAO();
                ArrayList<Student> arrStudent = new ArrayList<>();

                arrStudent = studentDAO.getEnrolledStudents(arrOfferingID.get(0));
                arrTemp = gradeDAO.getAllGradesForGradeCO(arrOfferingID.get(0));
                arrCO = gradeDAO.getCOs(arrOfferingID.get(0));

                System.out.println("arrSutdent: " + arrStudent.size() + " arrCO: " + arrCO.size() + " arrTemp: " + arrTemp.size());
                //loops through all the students in offering
                for (int a = 0; a < arrStudent.size(); a++) {
                    //loops through all the COs in assessment
                    for (int b = 0; b < arrCO.size(); b++) {
                        double gradeCO = 0;
                        //loops through all the grades
                        for (int c = 0; c < arrTemp.size(); c++) {
//                            System.out.println("compare student: " + arrStudent.get(a).getStudentID()
//                                    + " vs " + arrTemp.get(c).getStudentID()
//                                    + " compareCO : " + arrCO.get(b) + " vs " + arrTemp.get(c).getCoID());
                            if (arrStudent.get(a).getStudentID() == arrTemp.get(c).getStudentID()
                                    && arrCO.get(b) == arrTemp.get(c).getCoID()) {
                                System.out.println("entered if");
                                double weightedGrade = arrTemp.get(c).getGrade() * (arrTemp.get(c).getWeight() * 0.01);
                                gradeCO += weightedGrade;
                                System.out.println(arrTemp.get(c).getAssessmentID() + " : " + weightedGrade);
                            }
                        }
                        System.out.println("co grade: " + gradeCO);
                        gradeCO = gradeCO - (gradeCO % .5);
                        if (gradeCO < 1) {
                            gradeCO = 0;
                        }
                        GradeCO temp = new GradeCO();
                        temp.setStudentID(arrStudent.get(a).getStudentID());
                        temp.setOfferingID(arrOfferingID.get(0));
                        temp.setCoID(arrCO.get(b));
                        temp.setGradeCO(gradeCO);
                        temp.setContributor(contributor);
                        if (gradeCoDAO.encodeGrade(temp)) {
                        } else {
                            x = false;
                        }
                    }
                }
            } else {
                x = false;
            }
            PrintWriter out = response.getWriter();
            out.print(x);
        } catch (ParseException ex) {
            Logger.getLogger(EncodeStudentGrades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(EncodeStudentGrades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
