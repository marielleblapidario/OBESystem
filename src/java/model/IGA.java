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
public class IGA {
    private String codeIGA;
    private String description;
    private String remarks;
    private Date dateMade;
    private Date dateUpdated;
    private int contributor;
    
    //for view
    private String contributorFirstName;
    private String contributorLastName;
    
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @return the codeIGA
     */
    public String getCodeIGA() {
        return codeIGA;
    }

    /**
     * @param codeIGA the codeIGA to set
     */
    public void setCodeIGA(String codeIGA) {
        this.codeIGA = codeIGA;
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
     * @return the dateMade
     */
    public java.sql.Date getDateMade() {
        return dateMade;
    }

    /**
     * @param dateMade the dateMade to set
     */
    public void setDateMade() throws ParseException{
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = getFormatter().parse(df.format(currentDate));
        java.sql.Date sqlDate = new java.sql.Date(currentDate1.getTime());
        this.dateMade = sqlDate;
    }
    
    public void setDateMade(java.sql.Date dateMade) {
        this.dateMade = dateMade;
    }
    /**
     * @return the dateReceived
     */
    public java.sql.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateUpdated() throws ParseException{
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = getFormatter().parse(df.format(currentDate));
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
     * @return the formatter
     */
    public DateFormat getFormatter() {
        return formatter;
    }

    /**
     * @param formatter the formatter to set
     */
    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    /**
     * @return the contributorFirstName
     */
    public String getContributorFirstName() {
        return contributorFirstName;
    }

    /**
     * @param contributorFirstName the contributorFirstName to set
     */
    public void setContributorFirstName(String contributorFirstName) {
        this.contributorFirstName = contributorFirstName;
    }

    /**
     * @return the contributorLastName
     */
    public String getContributorLastName() {
        return contributorLastName;
    }

    /**
     * @param contributorLastName the contributorLastName to set
     */
    public void setContributorLastName(String contributorLastName) {
        this.contributorLastName = contributorLastName;
    }
    
    
    
}
