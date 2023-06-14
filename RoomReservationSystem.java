import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomReservationSystem extends JFrame {
    private DefaultListModel<String> availableRooms;
    private JList<String> availableRoomsList;

    public RoomReservationSystem() {
        setTitle("Room Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        createWelcomeTitle();
        initializeRooms();
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createWelcomeTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Please Select a Room");
        welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(welcomeLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

    private void initializeRooms() {
        availableRooms = new DefaultListModel<>();
        availableRooms.addElement("Standard");
        availableRooms.addElement("Deluxe");
        availableRooms.addElement("Suite");
        availableRooms.addElement("Executive");
        availableRooms.addElement("Premium");
        availableRooms.addElement("Penthouse Suite");
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        availableRoomsList = new JList<>(availableRooms);
        JScrollPane scrollPane = new JScrollPane(availableRoomsList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton showAvailableRoomsButton = new JButton("Display Room Amenities");
        showAvailableRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableRooms();
            }
        });
        buttonPanel.add(showAvailableRoomsButton);

        JButton reserveRoomButton = new JButton("Reserve a Room");
        reserveRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveRoom();
            }
        });
        buttonPanel.add(reserveRoomButton);

        JButton viewReservedRoomsButton = new JButton("View Reserved Rooms");
        viewReservedRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewReservedRooms();
            }
        });
        buttonPanel.add(viewReservedRoomsButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void showAvailableRooms() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < availableRooms.size(); i++) {
            String roomType = availableRooms.get(i);
            output.append("Room type: ").append(roomType).append("\n");
            output.append("Amenities: ").append(getAmenitiesForRoomType(roomType)).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, output.toString());
    }

    private String getAmenitiesForRoomType(String roomType) {
        return switch (roomType.toUpperCase()) {
            case "STANDARD" -> "It includes a bed, a private bathroom, a desk, and basic furniture.";
            case "DELUXE" -> "It includes a larger bed, a seating area, a work desk, and upgraded furnishings.";
            case "SUITE" -> "It includes additional amenities like a kitchenette, a dining table, and a private balcony. " +
                    "\nSuites are ideal for guests seeking more space and privacy.";
            case "EXECUTIVE" -> "Designed for business travelers, executive rooms are equipped with features to facilitate work-related activities. " +
                    "\nThey often include a spacious work desk, ergonomic chair, enhanced lighting, " +
                    "\nand extra connectivity options such as high-speed internet access.";
            case "PREMIUM" -> "Consists of two or more separate rooms with a connecting door, allowing guests to move between them without having to go through the hallway. " +
                    "\nThey are suitable for families or groups who prefer the convenience of staying close together.";
            case "PENTHOUSE SUITE" -> "The pinnacle of luxury, a penthouse suite is located on the top floor of a hotel and offers panoramic views. " +
                    "\nIt typically features multiple bedrooms, a spacious living area, a fully equipped kitchen, a private terrace or balcony, " +
                    "\nand exclusive services such as a dedicated concierge.";
            default -> "N/A";
        };
    }

    private void reserveRoom() {
        String selectedRoomType = availableRoomsList.getSelectedValue();
        if (selectedRoomType != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to reserve the room type " + selectedRoomType + "?", "Confirm Reservation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Room type " + selectedRoomType + " has been reserved.");
                availableRooms.removeElement(selectedRoomType);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a room type.");
        }
    }

    private void viewReservedRooms() {
        StringBuilder output = new StringBuilder();
        if (availableRooms.isEmpty()) {
            output.append("No rooms are currently reserved.");
        } else {
            output.append("Reserved Rooms:\n");
            for (int i = 0; i < availableRooms.size(); i++) {
                String roomType = availableRooms.get(i);
                output.append("- ").append(roomType).append("\n");
            }
        }
        output.append("\nDo you want to proceed with the reservation?");
        int choice = JOptionPane.showConfirmDialog(this, output.toString(), "Reserved Rooms", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            reserveRoom();
        }
    }

    }
