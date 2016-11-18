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
public class GradeCO {
    private int studentID;
    private int offeringID;
    private int coID;
    private double gradeCO;
    
    private String codeCO;
    private String lastName;
    private String firstName;
    private String middleName;

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the offeringID
     */
    public int getOfferingID() {
        return offeringID;
    }

    /**
     * @param offeringID the offeringID to set
     */
    public void setOfferingID(int offeringID) {
        this.offeringID = offeringID;
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
     * @return the gradeCO
     */
    public double getGradeCO() {
        return gradeCO;
    }

    /**
     * @param gradeCO the gradeCO to set
     */
    public void setGradeCO(double gradeCO) {
        this.gradeCO = gradeCO;
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
