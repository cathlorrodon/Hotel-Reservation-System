import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

class RoomReservationSystem {
    private Map<String, List<Integer>> rooms;
    private Scanner scanner;
    private JFrame frame;

    public RoomReservationSystem() {
        rooms = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeRooms();
        createMainFrame();
    }

    private void createMainFrame() {
        frame = new JFrame("Room Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to the Room Reservation System");
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        frame.add(titleLabel);

        JButton availableRoomsButton = new JButton("Show available rooms");
        availableRoomsButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        availableRoomsButton.addActionListener(e -> showAvailableRooms());
        frame.add(availableRoomsButton);

        JButton reserveRoomButton = new JButton("Reserve a room");
        reserveRoomButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        reserveRoomButton.addActionListener(e -> reserveRoom());
        frame.add(reserveRoomButton);

        JButton reservedRoomsButton = new JButton("View reserved rooms");
        reservedRoomsButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        reservedRoomsButton.addActionListener(e -> viewReservedRooms());
        frame.add(reservedRoomsButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            frame.dispose();
            System.out.println("Thank you for using the Room Reservation System. Goodbye!");
        });
        frame.add(exitButton);

        frame.setVisible(true);
    }

    public void initializeRooms() {
        int roomNumber = 1;

        // Standard rooms (Room numbers: 1-20)
        List<Integer> standardRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            standardRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Standard", standardRoomNumbers);

        // Deluxe rooms (Room numbers: 21-40)
        List<Integer> deluxeRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            deluxeRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Deluxe", deluxeRoomNumbers);

        // Suite rooms (Room numbers: 41-60)
        List<Integer> suiteRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            suiteRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Suite", suiteRoomNumbers);

        // Executive rooms (Room numbers: 61-70)
        List<Integer> executiveRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            executiveRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Executive", executiveRoomNumbers);

        // Premium rooms (Room numbers: 71-80)
        List<Integer> premiumRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            premiumRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Premium", premiumRoomNumbers);

        // Penthouse Suite rooms (Room numbers: 81-90)
        List<Integer> penthouseSuiteRoomNumbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            penthouseSuiteRoomNumbers.add(roomNumber);
            roomNumber++;
        }
        rooms.put("Penthouse Suite", penthouseSuiteRoomNumbers);
    }

    public void showAvailableRooms() {
        JFrame availableRoomsFrame = new JFrame("Available Rooms");
        availableRoomsFrame.setSize(400, 300);
        availableRoomsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availableRoomsFrame.setLayout(new BoxLayout(availableRoomsFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Available Rooms");
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        availableRoomsFrame.add(titleLabel);

        for (String roomType : rooms.keySet()) {
            List<Integer> roomNumbers = rooms.get(roomType);
            JLabel roomTypeLabel = new JLabel("Room type: " + roomType);
            roomTypeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            availableRoomsFrame.add(roomTypeLabel);

            JLabel roomNumbersLabel = new JLabel("Room numbers: " + roomNumbers);
            roomNumbersLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            availableRoomsFrame.add(roomNumbersLabel);

            JLabel amenitiesLabel = new JLabel("Amenities: " + getAmenitiesForRoomType(roomType));
            amenitiesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            availableRoomsFrame.add(amenitiesLabel);

            availableRoomsFrame.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        availableRoomsFrame.setVisible(true);
    }

    public void viewReservedRooms() {
        JFrame reservedRoomsFrame = new JFrame("Reserved Rooms");
        reservedRoomsFrame.setSize(400, 300);
        reservedRoomsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reservedRoomsFrame.setLayout(new BoxLayout(reservedRoomsFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Reserved Rooms");
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        reservedRoomsFrame.add(titleLabel);

        boolean hasReservedRooms = false;
        for (String roomType : rooms.keySet()) {
            List<Integer> roomNumbers = rooms.get(roomType);
            for (int roomNumber : roomNumbers) {
                if (isRoomReserved(roomType, roomNumber)) {
                    hasReservedRooms = true;
                    JLabel roomNumberLabel = new JLabel("Room number: " + roomNumber);
                    roomNumberLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    reservedRoomsFrame.add(roomNumberLabel);

                    JLabel roomTypeLabel = new JLabel("Room type: " + roomType);
                    roomTypeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    reservedRoomsFrame.add(roomTypeLabel);

                    JLabel amenitiesLabel = new JLabel("Amenities: " + getAmenitiesForRoomType(roomType));
                    amenitiesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    reservedRoomsFrame.add(amenitiesLabel);

                    reservedRoomsFrame.add(Box.createRigidArea(new Dimension(0, 10)));

                    int option = JOptionPane.showConfirmDialog(reservedRoomsFrame,
                            "Cancel reservation for this room?", "Cancel Reservation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        cancelReservation(roomType, roomNumber);
                        JOptionPane.showMessageDialog(reservedRoomsFrame,
                                "Reservation for room number " + roomNumber + " has been canceled.",
                                "Reservation Canceled", JOptionPane.INFORMATION_MESSAGE);
                    }

                    int checkInOption = JOptionPane.showConfirmDialog(reservedRoomsFrame,
                            "Check-in for this room?", "Perform Check-in", JOptionPane.YES_NO_OPTION);
                    if (checkInOption == JOptionPane.YES_OPTION) {
                        performCheckIn(roomType, roomNumber);
                        JOptionPane.showMessageDialog(reservedRoomsFrame,
                                "Check-in for room number " + roomNumber + " has been performed.",
                                "Check-in Completed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        if (!hasReservedRooms) {
            JLabel noReservedRoomsLabel = new JLabel("No rooms have been reserved.");
            noReservedRoomsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            reservedRoomsFrame.add(noReservedRoomsLabel);
        }

        reservedRoomsFrame.setVisible(true);
    }

    public void reserveRoom() {
        JFrame reserveRoomFrame = new JFrame("Reserve a Room");
        reserveRoomFrame.setSize(400, 300);
        reserveRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reserveRoomFrame.setLayout(new BoxLayout(reserveRoomFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Reserve a Room");
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        reserveRoomFrame.add(titleLabel);

        JLabel roomTypeLabel = new JLabel("Enter the room type: ");
        roomTypeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        reserveRoomFrame.add(roomTypeLabel);

        JTextField roomTypeTextField = new JTextField();
        roomTypeTextField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        reserveRoomFrame.add(roomTypeTextField);

        JLabel roomNumberLabel = new JLabel("Enter the room number: ");
        roomNumberLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        reserveRoomFrame.add(roomNumberLabel);

        JTextField roomNumberTextField = new JTextField();
        roomNumberTextField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        reserveRoomFrame.add(roomNumberTextField);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        reserveButton.addActionListener(e -> {
            String roomType = roomTypeTextField.getText();
            int roomNumber = Integer.parseInt(roomNumberTextField.getText());
            if (reserveRoom(roomType, roomNumber)) {
                JOptionPane.showMessageDialog(reserveRoomFrame,
                        "Room number " + roomNumber + " has been reserved.", "Room Reserved",
                        JOptionPane.INFORMATION_MESSAGE);
                reserveRoomFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(reserveRoomFrame,
                        "Failed to reserve the room. Please check the room type and number.",
                        "Reservation Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        reserveRoomFrame.add(reserveButton);

        reserveRoomFrame.setVisible(true);
    }

    public boolean reserveRoom(String roomType, int roomNumber) {
        if (rooms.containsKey(roomType)) {
            List<Integer> roomNumbers = rooms.get(roomType);
            if (roomNumbers.contains(roomNumber)) {
                if (!isRoomReserved(roomType, roomNumber)) {
                    roomNumbers.remove(Integer.valueOf(roomNumber));
                    return true;
                }
            }
        }
        return false;
    }

    public void cancelReservation(String roomType, int roomNumber) {
        if (rooms.containsKey(roomType)) {
            List<Integer> roomNumbers = rooms.get(roomType);
            if (!roomNumbers.contains(roomNumber)) {
                roomNumbers.add(roomNumber);
            }
        }
    }

    public void performCheckIn(String roomType, int roomNumber) {
        // Perform check-in logic here
        System.out.println("Performing check-in for Room " + roomNumber + " of type " + roomType);
    }

    public boolean isRoomReserved(String roomType, int roomNumber) {
        // Check if room is reserved logic here
        return false;
    }

    public List<String> getAmenitiesForRoomType(String roomType) {
        // Get amenities for room type logic here
        return new ArrayList<>();
    }

}