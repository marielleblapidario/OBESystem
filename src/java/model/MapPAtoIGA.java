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
public class MapPAtoIGA {
    private String codePA;
    private String codeIGA;
    private String status;
    private String remarks;
    private int contributor;
    private int checker;
    
    private String contributorName;
    private String checkerName;

    /**
     * @return the codePA
     */
    public String getCodePA() {
        return codePA;
    }

    /**
     * @param codePA the codePA to set
     */
    public void setCodePA(String codePA) {
        this.codePA = codePA;
    }

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the checker
     */
    public int getChecker() {
        return checker;
    }

    /**
     * @param checker the checker to set
     */
    public void setChecker(int checker) {
        this.checker = checker;
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
    
}
