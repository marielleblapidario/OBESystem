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
public class MapCurriculumToCourse {
    private int mapCurID;
    private int curriculumID;
    private int courseID;
    private int term;
    private int yearLevel;
    private int preRequisite;
    
    private String codeCourse;
    private String courseTitle;
    private int units;

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
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * @return the codeCourse
     */
    public String getCodeCourse() {
        return codeCourse;
    }

    /**
     * @param codeCourse the codeCourse to set
     */
    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

    /**
     * @return the term
     */
    public int getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * @return the yearLevel
     */
    public int getYearLevel() {
        return yearLevel;
    }

    /**
     * @param yearLevel the yearLevel to set
     */
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    /**
     * @return the preRequisite
     */
    public int getPreRequisite() {
        return preRequisite;
    }

    /**
     * @param preRequisite the preRequisite to set
     */
    public void setPreRequisite(int preRequisite) {
        this.preRequisite = preRequisite;
    }

    /**
     * @return the mapCurID
     */
    public int getMapCurID() {
        return mapCurID;
    }

    /**
     * @param mapCurID the mapCurID to set
     */
    public void setMapCurID(int mapCurID) {
        this.mapCurID = mapCurID;
    }

}
