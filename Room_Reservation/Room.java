/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
class Room {

    private int number;
    private String type;
    private boolean reserved;

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
        this.reserved = false;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        reserved = true;
    }

    public void cancelReservation() {
        reserved = false;
    }

    public void checkIn() {
        reserved = true;
    }
}
