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
public class MapCourseToProgram {
    private String codeCourse;
    private String codeProgram;
    
    private String programTitle;

    /**
     * @return the codeCourse
     */
    public String getCodeCourse() {
        return codeCourse;
    }

    /**
     * @param codeCourse the codeCourse to set
     */
    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

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
}
