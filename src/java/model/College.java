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
public class College {
    private int collegeID;
    private String college;
    /**
     * @return the college
     */
    public String getCollege() {
        return college;
    }

    /**
     * @param college the college to set
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * @return the collegeID
     */
    public int getCollegeID() {
        return collegeID;
    }

    /**
     * @param collegeID the collegeID to set
     */
    public void setCollegeID(int collegeID) {
        this.collegeID = collegeID;
    }
    
}
