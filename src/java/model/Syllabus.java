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
public class Syllabus {
    private int syllabusID;
    private int mapCurID;
    private int curriculumID;
    private int courseID;
    private int term;
    private int startYear;
    private int endYear;
    private int contributor;
    private java.sql.Date dateMade;
    private java.sql.Date dateUpdated;
    
    private String curriculumTitle;
    private String courseTitle;
    private String programTitle;
    private String codeCourse;
    private String contributorName;
    
    private int assessmentID;
    private int coID;
    private String codeCO;
    private String codePI;
    private String coDescription;
    private String coRemarks;
    private String codeAT;
    private String assessmentTitle;
    private String assessmentDescription;
    private double assessmentWeight;

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
     * @return the curriculumTitle
     */
    public String getCurriculumTitle() {
        return curriculumTitle;
    }

    /**
     * @param curriculumTitle the curriculumTitle to set
     */
    public void setCurriculumTitle(String curriculumTitle) {
        this.curriculumTitle = curriculumTitle;
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
     * @return the programTitle
     */
    public String getProgramTitle() {
        return programTitle;
    }

    /**
     * @param programTitle the programTitle to set
     */
    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
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
     * @return the contributorName
     */
    public String getContributorName() {
        return contributorName;
    }

    /**
     * @param contributorName the contributorName to set
     */
    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
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
    
    /**
     * @return the dateMade
     */
    public java.sql.Date getDateMade() {
        return dateMade;
    }

    /**
     * @param dateMade the dateMade to set
     * @throws java.text.ParseException
     */
    public void setDateMade() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = formatter.parse(df.format(currentDate));
        java.sql.Date sqlDate = new java.sql.Date(currentDate1.getTime());
        this.dateMade = sqlDate;
    }

    public void setDateMade(java.sql.Date dateMade) {
        this.dateMade = dateMade;
    }

    /**
     * @return the dateUpdated
     */
    public java.sql.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     * @throws java.text.ParseException
     */
    public void setDateUpdated() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = formatter.parse(df.format(currentDate));
        java.sql.Date sqlDate = new java.sql.Date(currentDate1.getTime());
        this.dateUpdated = sqlDate;
    }

    public void setDateUpdated(java.sql.Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * @return the assessmentID
     */
    public int getAssessmentID() {
        return assessmentID;
    }

    /**
     * @param assessmentID the assessmentID to set
     */
    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
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
     * @return the coDescription
     */
    public String getCoDescription() {
        return coDescription;
    }

    /**
     * @param coDescription the coDescription to set
     */
    public void setCoDescription(String coDescription) {
        this.coDescription = coDescription;
    }

    /**
     * @return the coRemarks
     */
    public String getCoRemarks() {
        return coRemarks;
    }

    /**
     * @param coRemarks the coRemarks to set
     */
    public void setCoRemarks(String coRemarks) {
        this.coRemarks = coRemarks;
    }

    /**
     * @return the codeAT
     */
    public String getCodeAT() {
        return codeAT;
    }

    /**
     * @param codeAT the codeAT to set
     */
    public void setCodeAT(String codeAT) {
        this.codeAT = codeAT;
    }

    /**
     * @return the assessmentTitle
     */
    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    /**
     * @param assessmentTitle the assessmentTitle to set
     */
    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    /**
     * @return the assessmentDescription
     */
    public String getAssessmentDescription() {
        return assessmentDescription;
    }

    /**
     * @param assessmentDescription the assessmentDescription to set
     */
    public void setAssessmentDescription(String assessmentDescription) {
        this.assessmentDescription = assessmentDescription;
    }

    /**
     * @return the assessmentWeight
     */
    public double getAssessmentWeight() {
        return assessmentWeight;
    }

    /**
     * @param assessmentWeight the assessmentWeight to set
     */
    public void setAssessmentWeight(double assessmentWeight) {
        this.assessmentWeight = assessmentWeight;
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
