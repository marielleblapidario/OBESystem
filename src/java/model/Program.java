/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author mariellelapidario
 */
public class Program {
    private String codeProgram;
    private String title;
    private int college;
    private int units;
    private String description;
    private Date dateMade;
    private Date dateUpdated;
    private int contributor;
    
    private String collegeName;
    private String contributorName;
    

    /**
     * @return the codeProgram
     */
    public String getCodeProgram() {
        return codeProgram;
    }

    /**
     * @param codeProgram the codeProgram to set
     */
    public void setCodeProgram(String codeProgram) {
        this.codeProgram = codeProgram;
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
     * @return the college
     */
    public int getCollege() {
        return college;
    }

    /**
     * @param college the college to set
     */
    public void setCollege(int college) {
        this.college = college;
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
     * @return the dateMade
     */
    public java.sql.Date getDateMade() {
        return dateMade;
    }

    /**
     * @param dateMade the dateMade to set
     */
    public void setDateMade() throws ParseException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = formatter.parse(df.format(currentDate));
        java.sql.Date sqlDate = new java.sql.Date(currentDate1.getTime());
        this.dateMade = sqlDate;
    }
    
    public void setDateMade(java.sql.Date datMade) {
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
     */
    public void setDateUpdated() throws ParseException{
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
     * @return the collegeName
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * @param collegeName the collegeName to set
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
