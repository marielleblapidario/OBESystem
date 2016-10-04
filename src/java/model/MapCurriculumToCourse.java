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
public class MapCurriculumToCourse {
    private int codeCurriculum;
    private String codeCourse;
    
    private String courseTitle;
    private int units;

    /**
     * @return the codeCurriculum
     */
    public int getCodeCurriculum() {
        return codeCurriculum;
    }

    /**
     * @param codeCurriculum the codeCurriculum to set
     */
    public void setCodeCurriculum(int codeCurriculum) {
        this.codeCurriculum = codeCurriculum;
    }

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
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units;
    }
}
