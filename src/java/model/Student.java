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
public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String program;
    private String gender;
    private Date birthday;
    
    private String columnName;
    private String programTitle;
    private int offeringID;
    private double grade;

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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birthday
     */
    public java.sql.Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday() throws ParseException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate1 = formatter.parse(df.format(currentDate));
        java.sql.Date sqlDate = new java.sql.Date(currentDate1.getTime());
        this.birthday = sqlDate;
    }
    
    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
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
}
