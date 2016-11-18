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
    
    private int coID;
    private double weight;
    private int syllabusID;
    private String typeName;

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
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
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
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
