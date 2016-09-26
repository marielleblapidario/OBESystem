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
public class MapPOtoPA {
    private String codePO;
    private String codePA;
    private String status;
    private String remarks;
    private int contributor;
    private int checker;
    
    private String contributorFirstName;
    private String contributorLastName;
    private String checkerFirstName;
    private String checkerLastName;

    /**
     * @return the codePO
     */
    public String getCodePO() {
        return codePO;
    }

    /**
     * @param codePO the codePO to set
     */
    public void setCodePO(String codePO) {
        this.codePO = codePO;
    }

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

    /**
     * @return the checkerFirstName
     */
    public String getCheckerFirstName() {
        return checkerFirstName;
    }

    /**
     * @param checkerFirstName the checkerFirstName to set
     */
    public void setCheckerFirstName(String checkerFirstName) {
        this.checkerFirstName = checkerFirstName;
    }

    /**
     * @return the checkerLastName
     */
    public String getCheckerLastName() {
        return checkerLastName;
    }

    /**
     * @param checkerLastName the checkerLastName to set
     */
    public void setCheckerLastName(String checkerLastName) {
        this.checkerLastName = checkerLastName;
    }
}
