import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomReservation extends JFrame implements ActionListener {

    private JButton showAvailableRoomButton;
    private JButton viewReservedRoomButton;
    private JButton payButton;
    private JButton logoutButton;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel availableRoomsPanel;
    private JPanel reservedRoomsPanel;

    private List<String> reservedRooms;
    private Map<String, String> roomTypes;
    private Map<String, String> amenities;
    private Map<String, Double> roomPrices;
    private String selectedRoom; 

    public RoomReservation() {
        setTitle("HOTEL GOSO");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome to HOTEL GOSO, where comfort embraces you like a warm, familiar hug.\n Where guests become cherished family.");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        showAvailableRoomButton = new JButton("Show Available Rooms");
        viewReservedRoomButton = new JButton("View Reserved Rooms");
        payButton = new JButton("Pay for Reserved Room");
        logoutButton = new JButton("Logout");

        buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(showAvailableRoomButton);
        buttonPanel.add(viewReservedRoomButton); // Adding viewReservedRoomButton to the buttonPanel
        buttonPanel.add(payButton);
        buttonPanel.add(logoutButton);

        showAvailableRoomButton.addActionListener(this);
        viewReservedRoomButton.addActionListener(this);
        payButton.addActionListener(this);
        logoutButton.addActionListener(this);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        availableRoomsPanel = new JPanel(new GridLayout(11, 5, 5, 5));
        reservedRoomsPanel = new JPanel(new GridLayout(11, 5, 5, 5));

        reservedRooms = new ArrayList<>();

        roomTypes = new HashMap<>();
        roomTypes.put("STANDARD", "Room 1-10");
        roomTypes.put("DELUXE", "Room 11-20");
        roomTypes.put("SUITE", "Room 21-30");
        roomTypes.put("EXECUTIVE", "Room 31-40");
        roomTypes.put("PREMIUM", "Room 41-50");
        roomTypes.put("PENTHOUSE", "Room 51-55");

        amenities = new HashMap<>();
        amenities.put("STANDARD", "It includes a bed, a private bathroom, a desk, and basic furniture.");
        amenities.put("DELUXE", "It includes a larger bed, a seating area, a work desk, and upgraded furnishings.");
        amenities.put("SUITE", "It includes additional amenities like a kitchenette, a dining table, and a private balcony. \nSuites are ideal for guests seeking more space and privacy.");
        amenities.put("EXECUTIVE", "Designed for business travelers, executive rooms are equipped with features to facilitate work-related activities. "
                + "\nThey often include a spacious work desk, ergonomic chair, enhanced lighting, \nand extra connectivity options such as high-speed internet access.");
        amenities.put("PREMIUM", "Consists of two or more separate rooms with a connecting door, allowing guests to move between them without having to go through the hallway. "
                + "\nThey are suitable for families or groups who prefer the convenience of staying close together.");
        amenities.put("PENTHOUSE", "The pinnacle of luxury, a penthouse suite is located on the top floor of a hotel and offers panoramic views. "
                + "\nIt typically features multiple bedrooms, a spacious living area, a fully equipped kitchen, a private terrace or balcony, \nand exclusive services such as a dedicated concierge.");

        roomPrices = new HashMap<>();
        roomPrices.put("STANDARD", 1000.0);
        roomPrices.put("DELUXE", 2000.0);
        roomPrices.put("SUITE", 5000.0);
        roomPrices.put("EXECUTIVE", 5500.0);
        roomPrices.put("PREMIUM", 6500.0);
        roomPrices.put("PENTHOUSE", 7500.0);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        
        
        //------ DATABASE CONNECTOR ------
        try {
            String url = "jdbc:mysql://localhost:3306/hotel_goso";
            String username = "root";
            String password = "password123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private Connection connection;
    
    

    // --------------------------------- SHOW AVAILABLE ROOMS --------------------------------
    private void displayAvailableRooms() {
        String[] roomTypesArray = {"STANDARD", "DELUXE", "SUITE", "EXECUTIVE", "PREMIUM", "PENTHOUSE"};
        String selectedRoomType = (String) JOptionPane.showInputDialog(this, "Select Room Type:", "Available Room Types", JOptionPane.PLAIN_MESSAGE, null, roomTypesArray, roomTypesArray[0]);

        if (selectedRoomType != null) {
            selectRoomNumber(selectedRoomType);
        }
    }

    private void selectRoomNumber(String roomType) {
        List<String> availableRoomsInfo = new ArrayList<>();

        for (int i = 1; i <= 55; i++) {
            String type = getRoomType(i);
            if (type.equals(roomType)) {
                String roomNumber = "Room " + i;
                double roomPrice = roomPrices.get(type);
                availableRoomsInfo.add(roomNumber + " - Price: ₱" + roomPrice);
            }
        }

        String[] roomInfoArray = availableRoomsInfo.toArray(new String[0]);
        String selectedRoomInfo = (String) JOptionPane.showInputDialog(this, "Select Room:", "Available Rooms - " + roomType, JOptionPane.PLAIN_MESSAGE, null, roomInfoArray, roomInfoArray[0]);

        if (selectedRoomInfo != null) {
            String selectedRoomNumber = selectedRoomInfo.substring(0, selectedRoomInfo.indexOf(" -")).trim();
            confirmReservation(selectedRoomNumber);
        }
    }

    private String getRoomType(int roomNumber) {
        if (roomNumber >= 1 && roomNumber <= 10) {
            return "STANDARD";
        } else if (roomNumber >= 11 && roomNumber <= 20) {
            return "DELUXE";
        } else if (roomNumber >= 21 && roomNumber <= 30) {
            return "SUITE";
        } else if (roomNumber >= 31 && roomNumber <= 40) {
            return "EXECUTIVE";
        } else if (roomNumber >= 41 && roomNumber <= 50) {
            return "PREMIUM";
        } else if (roomNumber >= 51 && roomNumber <= 55) {
            return "PENTHOUSE";
        } else {
            return "Not available";
        }
    }

    private void confirmReservation(String roomNumber) {
        String roomType = getRoomType(Integer.parseInt(roomNumber.substring(5)));
        String roomAmenities = amenities.get(roomType);
        double roomPrice = roomPrices.get(roomType);

        int option = JOptionPane.showOptionDialog(this,
                "You have selected: " + roomNumber + " - " + roomType + "\nAmenities: " + roomAmenities + "\nPrice: ₱" + roomPrice
                + "\nDo you want to reserve this Room?", "Confirm Reservation", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Confirm", "Back"}, "CONFIRM RESERVATION");

        if (option == 0) {
            reserveRoom(roomNumber);
        }
    }

    private void reserveRoom(String roomNumber) {
        if (reservedRooms.contains(roomNumber)) {
            JOptionPane.showMessageDialog(this, "Room " + roomNumber + " is already reserved.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String roomType = getRoomType(Integer.parseInt(roomNumber.substring(5)));
            double roomPrice = roomPrices.get(roomType);

            // Insert the reserved room details into the database
            try {
                String insertSQL = "INSERT INTO reservations (room_number, room_type, room_price, amenities) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, roomNumber);
                preparedStatement.setString(2, roomType);
                preparedStatement.setDouble(3, roomPrice);
                preparedStatement.setString(4, amenities.get(roomType));
                preparedStatement.executeUpdate();

                // Update the UI and inform the user about the successful reservation
                reservedRooms.add(roomNumber);
                JOptionPane.showMessageDialog(this, "Reservation created for: " + roomNumber + "\nRoom Type: " + roomType + "\nPrice: ₱" + roomPrice, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to reserve the room. Please try again.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (reservedRooms.contains(roomNumber)) {
            JOptionPane.showMessageDialog(this, "Room " + roomNumber + " is already reserved.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String roomType = getRoomType(Integer.parseInt(roomNumber.substring(5)));
            double roomPrice = roomPrices.get(roomType);

            reservedRooms.add(roomNumber);
            JOptionPane.showMessageDialog(this, "Reservation created for: " + roomNumber + "\nRoom Type: " + roomType + "\nPrice: ₱" + roomPrice, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // -------------------------------  VIEW RESERVED ROOMS ---------------------------------
    private void displayReservedRooms() {
        reservedRoomsPanel.removeAll();

        for (String room : reservedRooms) {
            String roomType = getRoomType(Integer.parseInt(room.substring(5)));
            double roomPrice = roomPrices.get(roomType);
            JLabel roomLabel = new JLabel("Reserved: " + roomType + " - " + room + " -₱ " + roomPrice);

            JButton removeButton = new JButton("Remove");
            removeButton.setActionCommand(room);
            removeButton.addActionListener(this);

            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.add(roomLabel, BorderLayout.CENTER);
            roomPanel.add(removeButton, BorderLayout.EAST);
            reservedRoomsPanel.add(roomPanel);
        }

        JScrollPane scrollPane = new JScrollPane(reservedRoomsPanel);

        JOptionPane.showMessageDialog(this, scrollPane, "Reserved Rooms", JOptionPane.PLAIN_MESSAGE);
    }

    private void removeReservation(String roomNumber) {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the reservation for " + roomNumber + "?", "Remove Reservation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            if (reservedRooms.remove(roomNumber)) {
                JOptionPane.showMessageDialog(this, "Reservation for " + roomNumber + " has been removed.", "Reservation Removed", JOptionPane.INFORMATION_MESSAGE);
                displayReservedRooms();

            } else {
                JOptionPane.showMessageDialog(this, "Room " + roomNumber + " is not reserved.", "Remove Reservation Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // ----------------- PAYMENT ---------------------------
    private void payForReservedRoom() {
        if (reservedRooms.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No reserved rooms found. Please reserve a room first.",
                    "No Reserved Rooms", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] paymentMethods = {"Credit Card", "Debit Card", "PayPal", "Bank Transfer", "Over the Counter"};
        String selectedPaymentMethod = (String) JOptionPane.showInputDialog(this, "Select Payment Method:",
                "Available Payment Methods", JOptionPane.PLAIN_MESSAGE, null, paymentMethods, paymentMethods[0]);

        if (selectedPaymentMethod != null) {
            if (selectedPaymentMethod.equals("Over the Counter")) {
                String paymentAmountString = JOptionPane.showInputDialog(this, "Enter payment amount for the reserved room:", "Payment Amount", JOptionPane.PLAIN_MESSAGE);
                if (paymentAmountString != null && !paymentAmountString.isEmpty()) {
                    double paymentAmount = Double.parseDouble(paymentAmountString);
                    String roomNumber = reservedRooms.get(reservedRooms.size() - 1);
                    double roomPrice = roomPrices.get(getRoomType(Integer.parseInt(roomNumber.substring(5))));
                    if (paymentAmount >= roomPrice) {
                        double change = paymentAmount - roomPrice;
                        JOptionPane.showMessageDialog(this, "Payment successful! \n " + roomNumber + " has been paid for. "
                                + "\n Amount: ₱" + roomPrice
                                + "\n Change: ₱" + change,
                                "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        displayReceipt(roomNumber, roomPrice); // 
                    } else {
                        JOptionPane.showMessageDialog(this, "Payment amount is insufficient. Please enter the full amount.",
                                "Insufficient Payment", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Payment amount cannot be empty. Please try again.",
                            "Invalid Payment Amount", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String roomNumber = reservedRooms.get(reservedRooms.size() - 1);
                double roomPrice = roomPrices.get(getRoomType(Integer.parseInt(roomNumber.substring(5))));
                JOptionPane.showMessageDialog(this, "Payment successful! \n " + roomNumber + " has been paid for." + "\n Amount: ₱" + roomPrice,
                        "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);

                displayReceipt(roomNumber, roomPrice);
            }
        }
    }

    // --------------------- DISPLAYING RECEIPT -----------------------------
    private void displayReceipt(String roomNumber, double roomPrice) {
        String roomType = getRoomType(Integer.parseInt(roomNumber.substring(5)));
        String roomAmenities = amenities.get(roomType);

        StringBuilder receiptMessage = new StringBuilder();
        receiptMessage.append("Reservation Receipt\n");
        receiptMessage.append("Room Number: ").append(roomNumber).append("\n");
        receiptMessage.append("Room Type: ").append(roomType).append("\n");
        receiptMessage.append("Amenities: ").append(roomAmenities).append("\n");
        receiptMessage.append("Price: ₱").append(roomPrice).append("\n\n");
        receiptMessage.append("Thank you for choosing HOTEL GOSO!");

        JOptionPane.showMessageDialog(this, receiptMessage.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }

    // ------------------- LOGGING OUT -----------------------------------
    private void logoutUser() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "You have been logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showAvailableRoomButton) {
            displayAvailableRooms();
        } else if (e.getSource() == viewReservedRoomButton) {
            displayReservedRooms();
        } else if (e.getSource() == logoutButton) {
            logoutUser();
        } else if (e.getSource() == payButton) {
            payForReservedRoom();
        } else {
            String roomNumber = e.getActionCommand();
            removeReservation(roomNumber);
        }
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomReservation());
    }

}
