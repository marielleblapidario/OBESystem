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
public class CourseOffering {
    private int offeringID;
    private int curriculumID;
    private int courseID;
    private int term;
    private String section;
    private String days;
    private String time;
    private int room;
    private int faculty;
    
    private String curriculumTitle;
    private String courseTitle;
    private String roomTitle;
    private String facultyName;
    private int syllabusID;

    /**
     * @return the curriculumID
     */
    public int getCurriculumID() {
        return curriculumID;
    }

    /**
     * @param curriculumID the curriculumID to set
     */
    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the term
     */
    public int getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return the days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the room
     */
    public int getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(int room) {
        this.room = room;
    }

    /**
     * @return the faculty
     */
    public int getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    /**
     * @return the curriculumTitle
     */
    public String getCurriculumTitle() {
        return curriculumTitle;
    }

    /**
     * @param curriculumTitle the curriculumTitle to set
     */
    public void setCurriculumTitle(String curriculumTitle) {
        this.curriculumTitle = curriculumTitle;
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
     * @return the roomTitle
     */
    public String getRoomTitle() {
        return roomTitle;
    }

    /**
     * @param roomTitle the roomTitle to set
     */
    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    /**
     * @return the facultyName
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * @param facultyName the facultyName to set
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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
}