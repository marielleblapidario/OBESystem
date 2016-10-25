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
public class Grade {
    private int studentID;
    private int offeringID;
    private int assessmentID;
    private double grade;
    private String codeAT;

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
     * @return the grade
     */
    public double getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(double grade) {
        this.grade = grade;
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
}
