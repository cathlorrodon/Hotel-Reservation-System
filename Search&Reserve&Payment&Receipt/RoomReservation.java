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
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel availableRoomsPanel;
    private JPanel reservedRoomsPanel;
    private JLabel welcomeLabel, text, welcomeLabel2;
    private List<String> reservedRooms;
    private List<String> availableRooms;
    private Map<String, String> roomTypes;
    private Map<String, String> amenities;
    private Map<String, Double> roomPrices;
    private String selectedRoom;
  

    public RoomReservation() {
        setTitle("HOTEL GoSo");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
        JLabel welcomeLabel = new JLabel("Welcome to GoSo Hotel ");
        welcomeLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 48));
        welcomeLabel.setBounds(50, 65, 600, 50);
        welcomeLabel.setForeground(new Color(0, 102, 102));
        add(welcomeLabel);

        JLabel welcomeLabel2 = new JLabel("Where comfort embraces you like a warm, familiar hug and where guests become cherished family.");
        welcomeLabel2.setFont(new Font("Serif", Font.BOLD, 14));
        welcomeLabel2.setBounds(50, 100, 700, 50);
        welcomeLabel2.setForeground(Color.BLACK);
        add(welcomeLabel2);

        JPanel buttonPanelContainer = new JPanel(null);
        mainPanel = new JPanel(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        

        GridBagLayout buttonPanelLayout = new GridBagLayout();
        buttonPanelContainer.setLayout(buttonPanelLayout);

        // SHOW AVAILABLE ROOM BUTTON
        
        showAvailableRoomButton = new JButton("Show Available Rooms");
        showAvailableRoomButton.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        showAvailableRoomButton.setFocusable(false);
        showAvailableRoomButton.addActionListener(this);
        buttonPanelContainer.add(showAvailableRoomButton, gbc);

        
        // VIEW RESERVED ROOM BUTTON
        
        viewReservedRoomButton = new JButton("View Reserved Rooms");
        viewReservedRoomButton.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        viewReservedRoomButton.setFocusable(false);
        viewReservedRoomButton.addActionListener(this);
        gbc.gridx = 1;
        buttonPanelContainer.add(viewReservedRoomButton, gbc);
        buttonPanelContainer.add(viewReservedRoomButton);
        viewReservedRoomButton.setBounds(25, 310, 400, 40);
        reservedRoomsPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane reservedRoomsScrollPane = new JScrollPane(reservedRoomsPanel);
        
        mainPanel.add(reservedRoomsScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanelContainer, BorderLayout.CENTER);

        reservedRooms = new ArrayList<>();
        availableRooms = new ArrayList<>();

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

        // Add all room numbers to the available rooms list initially
        for (int i = 1; i <= 55; i++) {
            availableRooms.add("Room " + i);
        }

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
        if (type.equals(roomType) && !reservedRooms.contains("Room " + i)) { // Check if the room is not already reserved
            String roomNumber = "Room " + i;
            double roomPrice = roomPrices.get(type);
            availableRoomsInfo.add(roomNumber + " - Price: ₱" + roomPrice);
        }
    }

    if (availableRoomsInfo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No available rooms of type " + roomType + " found.", "No Available Rooms", JOptionPane.INFORMATION_MESSAGE);
    } else {
        String[] roomInfoArray = availableRoomsInfo.toArray(new String[0]);
        String selectedRoomInfo = (String) JOptionPane.showInputDialog(this, "Select Room:", "Available Rooms - " + roomType, JOptionPane.PLAIN_MESSAGE, null, roomInfoArray, roomInfoArray[0]);

        if (selectedRoomInfo != null) {
            String selectedRoomNumber = selectedRoomInfo.substring(0, selectedRoomInfo.indexOf(" -")).trim();
            confirmReservation(selectedRoomNumber);
        }
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
   
        return;
    }
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

    // -------------------------------  VIEW RESERVED ROOMS ---------------------------------
   private void displayReservedRooms() {
    reservedRoomsPanel.removeAll();

    for (String room : reservedRooms) {
        String roomType = getRoomType(Integer.parseInt(room.substring(5)));
        double roomPrice = roomPrices.get(roomType);
        JLabel roomLabel = new JLabel("Reserved: " + roomType + " - " + room + " -₱ " + roomPrice);

        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.add(roomLabel, BorderLayout.CENTER);
        reservedRoomsPanel.add(roomPanel);
    }

    JScrollPane scrollPane = new JScrollPane(reservedRoomsPanel);
    JOptionPane.showMessageDialog(this, scrollPane, "Reserved Rooms", JOptionPane.PLAIN_MESSAGE);
}

   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showAvailableRoomButton) {
            displayAvailableRooms();
        } else if (e.getSource() == viewReservedRoomButton) {
            displayReservedRooms();
        } else {
            String roomNumber = e.getActionCommand();
        }
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomReservation());
    }

}
