package Login_System;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Kenneth Odgien
 */
public class Main {

	public static void main(String[] args) {
                Accounts idandPasswords = new Accounts();		
		new LoginPage(idandPasswords.getLoginInfo());
	}
        
        public class websitePage extends JFrame {
        private JTable table;
        private DefaultTableModel tableModel;
    
        public websitePage() {
        WebsitePage();
        }  
        
        public void WebsitePage(){
        setTitle("access:ADMIN      |     HOTEL GOSO RESERVATION LOG ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 300);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Reservation No.");
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Contact No.");       
        tableModel.addColumn("Email"); 
        tableModel.addColumn("Check-In");
        tableModel.addColumn("Check-Out");

        tableModel.addRow(new Object[]{"1", "Claire B.", "Standard", "0929-325-4014", "clrB@gmail.com", "12/25/2023", "12/27/2023"});
        tableModel.addRow(new Object[]{"2", "Kath O.", "Deluxe", "0932-341-2063", "itskath@gmail.com", "02/14/2023", "02/15/2023"});
        tableModel.addRow(new Object[]{"3", "Kevin O.", "Premium", "0983-245-1753", "kevin0000@gmail.com", "05/31/2023", "06/02/2023"});
        tableModel.addRow(new Object[]{"4", "Yesha V.", "Pent-House", "0976-445-2196", "yesha786@gmail.com", "01/15/2023", "01/20/2023"});
        tableModel.addRow(new Object[]{"5", "James P.", "Executive", "0945-365-0227", "jamesP@gmail.com", "06/09/2023", "06/12/2023"});


        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton reserveButton = new JButton("Add Reservation");
        reserveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int reservationNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Reservation No.:"));
                String fullName = JOptionPane.showInputDialog("Enter Full Name: ");
                int fnLenght = fullName.length();
                String roomType = JOptionPane.showInputDialog("Enter Room Type:");
                int rtLenght = roomType.length();
                String contactNo = JOptionPane.showInputDialog("Enter Contact no. :");
                int cnLenght = contactNo.length();
                String email = JOptionPane.showInputDialog("Enter Email:");
                int eLenght = email.length();
                String checkIn = ""+LocalDateTime.now();
                String checkOut = JOptionPane.showInputDialog("Enter Check-Out:");
                int coLenght = checkOut.length();
                tableModel.addRow(new Object[]{ reservationNo, fullName, roomType, contactNo, email, checkIn, checkOut});
                
                if (reservationNo <= 0){JOptionPane.showMessageDialog(reserveButton, "Reservation Number should not be null");}
                else if (fnLenght == 0) {JOptionPane.showMessageDialog(reserveButton, "Full Name should not be null");}
                else if (rtLenght == 0){JOptionPane.showMessageDialog(reserveButton, "Room Type should not be null");}
                else if (cnLenght != 11){JOptionPane.showMessageDialog(reserveButton, "Contact should be eleven digits");}
                else if (eLenght == 0){JOptionPane.showMessageDialog(reserveButton, "Email should not be null");}
                else if (coLenght == 0){JOptionPane.showMessageDialog(reserveButton, "Check-Out Time should not be null");}
                else {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/HRS", "root", "balaguer");

                    var query = "INSERT INTO ReservationLog values('" + reservationNo + "','" + fullName + "','" + roomType + "','" + contactNo + "','" + email + "','" + LocalDateTime.now() + "','" + checkOut +"')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    connection.close();
                    if (x == 0){JOptionPane.showMessageDialog(reserveButton, "This is alredy exist");}
                    else {JOptionPane.showMessageDialog(reserveButton, "Success!");}
                }
                catch (Exception exception) {}
            }
            }
        });
        buttonPanel.add(reserveButton);

        JButton removeButton = new JButton("Remove Reservation");
        removeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                }
            }
        });
        buttonPanel.add(removeButton);

        JButton bttnLogout = new JButton("LogOut");
        bttnLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                dispose();
                if(ae.getSource() == bttnLogout)
                {System.out.print(new Main());}
            }
        });
        buttonPanel.add(bttnLogout);
        
        add(buttonPanel, BorderLayout.SOUTH); 
        }
        public void registrationHP(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override public void run(){
                new websitePage().setVisible(true);
            }
        });
        }
    }   
}
