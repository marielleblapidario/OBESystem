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
public class Curriculum {

    private String codeCurriculum;
    private String title;
    private String program;
    private java.sql.Date startYear;
    private java.sql.Date endYear;
    private String description;
    private int contributor;

    private String contributorName;

    /**
     * @return the codeCurriculum
     */
    public String getCodeCurriculum() {
        return codeCurriculum;
    }

    /**
     * @param codeCurriculum the codeCurriculum to set
     */
    public void setCodeCurriculum(String codeCurriculum) {
        this.codeCurriculum = codeCurriculum;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * @return the startYear
     */
    public java.sql.Date getStartYear() {
        return startYear;
    }

    /**
     * @param startYear the startYear to set
     */
//    public void setStartYear(java.sql.Date startYear) throws ParseException {
//        @SuppressWarnings("deprecation")
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date madeDate0 = new java.util.Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date madeDate1 = formatter.parse(df.format(madeDate0));
//        java.sql.Date sqlreceivedDate1 = new java.sql.Date(madeDate1.getTime());
//        this.startYear = sqlreceivedDate1;
//    }

    public void setStartYear(String startYear) throws ParseException {
        @SuppressWarnings("deprecation")
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date startYear1 = formatter.parse(startYear);
        java.sql.Date startYear2 = new java.sql.Date(startYear1.getTime());
        this.setStartYear(startYear2);
    }
    
    public void setStartYear(java.sql.Date startYear) {
        this.startYear = startYear;
    }

    /**
     * @return the endYear
     */
    public java.sql.Date getEndYear() {
        return endYear;
    }

    /**
     * @param endYear the endYear to set
     */
//    public void setEndYear(java.sql.Date endYear) throws ParseException {
//        @SuppressWarnings("deprecation")
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date madeDate0 = new java.util.Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date madeDate1 = formatter.parse(df.format(madeDate0));
//        java.sql.Date sqlreceivedDate1 = new java.sql.Date(madeDate1.getTime());
//        this.endYear = sqlreceivedDate1;
//    }
//    
    public void setEndYear(String endYear) throws ParseException {
        @SuppressWarnings("deprecation")
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date endYear1 = formatter.parse(endYear);
        java.sql.Date endYear2 = new java.sql.Date(endYear1.getTime());
        this.setEndYear(endYear2);
    }
    
    public void setEndYear(java.sql.Date endYear) {
        this.endYear = endYear;
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
