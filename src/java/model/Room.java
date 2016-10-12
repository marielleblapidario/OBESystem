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
public class Room {
    private int roomID;
    private String roomTitle;

    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
    
}
