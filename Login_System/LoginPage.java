package Login_System;

/**
 *
 * @author Kenneth Odgien
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LoginPage extends Main implements ActionListener{
	
	JFrame frame = new JFrame();
        JLabel Head = new JLabel("HOTEL GOSO");
	JButton loginButton = new JButton("Login");
	JButton signUPButton = new JButton("Sign Up");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("USER / ID:");
	JLabel userPasswordLabel = new JLabel("PASSWORD:");
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<>();
	
        
	LoginPage(HashMap<String,String> loginInfoParent){
            
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		logininfo = loginInfoParent;
                
                Head.setFont(new Font("Klavika", Font.PLAIN, 30));
                Head.setBounds(100,20,200, 30);
		
		userIDLabel.setBounds(50,100,80,25);
		userPasswordLabel.setBounds(50,150,80,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font("Klavika",Font.ITALIC,15));
                
		
		userIDField.setBounds(140,100,200,25);
		userPasswordField.setBounds(140,150,200,25);
		
		loginButton.setBounds(140,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		signUPButton.setBounds(245,200,100,25);
		signUPButton.setFocusable(false);
		signUPButton.addActionListener(this);
		
                frame.add(Head);
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(signUPButton);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==loginButton) {
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(logininfo.containsKey(userID)) {
				if(logininfo.get(userID).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					websitePage mainPage = new websitePage(userID);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Wrong password");
				}

			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Account doesn't exist");
			}
		}
                
                if(e.getSource()==signUPButton){
                    frame.dispose();
                    new SignUp();
                }
	}	
}
    

