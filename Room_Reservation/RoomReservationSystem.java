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

class RoomReservationSystem {

    private List<Room> rooms;
    private Scanner scanner;

    public RoomReservationSystem() {
        rooms = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeRooms();
    }

    public void initializeRooms() {
        int roomNumber = 1;

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Standard"));
            roomNumber++;
        }

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Deluxe"));
            roomNumber++;
        }

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Suite"));
            roomNumber++;
        }

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Executive"));
            roomNumber++;
        }

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Premium"));
            roomNumber++;
        }

        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(roomNumber, "Penthouse Suite"));
            roomNumber++;
        }
    }

    public void showAvailableRooms() {
        System.out.println("\n***** Available Rooms *");
        for (Room room : rooms) {
            if (!room.isReserved()) {
                System.out.println("Room number: " + room.getNumber());
                System.out.println("Room type: " + room.getType());
                System.out.println("Amenities: " + getAmenitiesForRoomType(room.getType()));
                System.out.println();
            }
        }
    }

    public void viewReservedRooms() {
        System.out.println("\n***** Reserved Rooms *");
        boolean hasReservedRooms = false;
        for (Room room : rooms) {
            if (room.isReserved()) {
                hasReservedRooms = true;
                System.out.println("Room number: " + room.getNumber());
                System.out.println("Room type: " + room.getType());
                System.out.println("Amenities: " + getAmenitiesForRoomType(room.getType()));
                System.out.println();
                System.out.print("Cancel reservation for this room? (Y/N): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    room.cancelReservation();
                    System.out.println("Reservation for room number " + room.getNumber() + " has been canceled.");
                }
                System.out.print("Check-in for this room? (Y/N): ");
                String checkInChoice = scanner.nextLine();
                if (checkInChoice.equalsIgnoreCase("Y")) {
                    room.checkIn();
                    System.out.println("Check-in for room number " + room.getNumber() + " has been completed.");
                }
            }
        }

        if (!hasReservedRooms) {
            System.out.println("No rooms are currently reserved.");
        }
    }

    private String getAmenitiesForRoomType(String roomType) {
        return switch (roomType.toUpperCase()) {
            case "STANDARD" ->
                "It includes a bed, a private bathroom, a desk, and basic furniture.";
            case "DELUXE" ->
                "It includes a larger bed, a seating area, a work desk, and upgraded furnishings.";
            case "SUITE" ->
                "It includes additional amenities like a kitchenette, a dining table, and a private balcony. "
                + "\nSuites are ideal for guests seeking more space and privacy.";
            case "EXECUTIVE" ->
                "Designed for business travelers, executive rooms are equipped with features to facilitate work-related activities. "
                + "\nThey often include a spacious work desk, ergonomic chair, enhanced lighting, "
                + "\nand extra connectivity options such as high-speed internet access.";
            case "PREMIUM" ->
                "Consists of two or more separate rooms with a connecting door, allowing guests to move between them without having to go through the hallway. "
                + "\nThey are suitable for families or groups who prefer the convenience of staying close together.";
            case "PENTHOUSE SUITE" ->
                "The pinnacle of luxury, a penthouse suite is located on the top floor of a hotel and offers panoramic views. "
                + "\nIt typically features multiple bedrooms, a spacious living area, a fully equipped kitchen, a private terrace or balcony, "
                + "\nand exclusive services such as a dedicated concierge.";
            default ->
                "N/A";
        };
    }

    public void reserveRoom() {
        System.out.print("Enter room type to reserve: ");
        String roomType = scanner.nextLine();

        Room selectedRoom = findAvailableRoomByType(roomType);
        if (selectedRoom != null) {
            selectedRoom.reserve();
            System.out.println("Room number " + selectedRoom.getNumber() + " (type: " + roomType + ") has been reserved.");
        } else {
            System.out.println("No available rooms of type " + roomType + " found.");
        }
    }

    public Room findAvailableRoomByType(String roomType) {
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(roomType) && !room.isReserved()) {
                return room;
            }
        }
        return null;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAvailableRooms();
                    break;
                case 2:
                    reserveRoom();
                    break;
                case 3:
                    viewReservedRooms();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting Room Reservation System. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayMenu() {
        System.out.println("\n***** Room Reservation System *");
        System.out.println("1. Show Available Rooms");
        System.out.println("2. Reserve a Room");
        System.out.println("3. View Reserved Rooms");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
