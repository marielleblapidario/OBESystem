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
}
