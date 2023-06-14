

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Room {
    private int number;
    private String type;
    private boolean isReserved;
    private List <String> amenities;

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
        this.isReserved = false;
        this.amenities = new ArrayList <> ();  
    }
    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
        
    }
    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
    }
    public List<String> getAmenities() {
        return amenities;
    } 
    public void addAmenity (String amenity) {
        amenities.add (amenity); 
    }
    
}