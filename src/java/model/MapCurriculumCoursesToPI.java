/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mariellelapidario
 */
public class MapCurriculumCoursesToPI {
    private int curriculumID;
    private int courseID;
    private String codePI;

    /**
     * @return the curriculumID
     */
    public int getCurriculumID() {
        return curriculumID;
    }

    /**
     * @param curriculumID the curriculumID to set
     */
    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the codePI
     */
    public String getCodePI() {
        return codePI;
    }

    /**
     * @param codePI the codePI to set
     */
    public void setCodePI(String codePI) {
        this.codePI = codePI;
    }

}
