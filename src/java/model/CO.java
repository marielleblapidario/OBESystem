/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author mariellelapidario
 */
public class CO {
    private int coID;
    private int syllabusID;
    private int curriculumID;
    private int courseID;
    private int term;
    private int startYear;
    private int endYear;
    private String codeCO;
    private String description;
    private String remarks;

    private String checkerName;
    private String codePI;
    private int mapCurID;
    private int contributor;

    /**
     * @return the codeCO
     */
    public String getCodeCO() {
        return codeCO;
    }

    /**
     * @param codeCO the codeCO to set
     */
    public void setCodeCO(String codeCO) {
        this.codeCO = codeCO;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the checkerName
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * @param checkerName the checkerName to set
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

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

    /**
     * @return the coID
     */
    public int getCoID() {
        return coID;
    }

    /**
     * @param coID the coID to set
     */
    public void setCoID(int coID) {
        this.coID = coID;
    }

    /**
     * @return the syllabusID
     */
    public int getSyllabusID() {
        return syllabusID;
    }

    /**
     * @param syllabusID the syllabusID to set
     */
    public void setSyllabusID(int syllabusID) {
        this.syllabusID = syllabusID;
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

    /**
     * @return the startYear
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * @param startYear the startYear to set
     */
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    /**
     * @return the endYear
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * @param endYear the endYear to set
     */
    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    /**
     * @return the contributor
     */
    public int getContributor() {
        return contributor;
    }

    /**
     * @param contributor the contributor to set
     */
    public void setContributor(int contributor) {
        this.contributor = contributor;
    }
}
