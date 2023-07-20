package Login_System;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Kenneth Odgien
 */
public class SignUp implements ActionListener{
    
    JFrame frame = new JFrame();
    JLabel HeadS = new JLabel("REGISTRATION FORM");
    JLabel firstnameLabel = new JLabel("First name: ");
    JTextField firstnameID = new JTextField();
    JLabel lastnameLabel = new JLabel("Last name: ");
    JTextField lastnameID = new JTextField();
    JLabel nameLabel = new JLabel("Username: ");
    JTextField nameID = new JTextField();
    JPasswordField password = new JPasswordField("");
    JLabel passwordLabel = new JLabel("Password: ");
    JPasswordField confirmpass = new JPasswordField();
    JLabel confirmpassLabel = new JLabel("Confirm Password: ");
    JTextField emailAdd = new JTextField();
    JLabel emailLabel = new JLabel("Email: ");
    JButton submitBtn = new JButton("Submit");
    JLabel messageLabel = new JLabel();
    JCheckBox terms = new JCheckBox("By using this app/site, you understand and agree to the terms");
    
    SignUp(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(520, 620);
	frame.setLayout(null);
	frame.setVisible(true);
        
        HeadS.setFont(new Font("Klavika", Font.PLAIN, 30));
        HeadS.setBounds(100,20,1000, 40);
        
        //Labels
        firstnameLabel.setBounds(60,100,80,25);
        lastnameLabel.setBounds(60,150,80,25);
        nameLabel.setBounds(60,200,80,25);
        passwordLabel.setBounds(60,250,80,25);
        confirmpassLabel.setBounds(12,300, 200,25);
        emailLabel.setBounds(85,350,80,25);
        messageLabel.setBounds(125,350,250,35);
        messageLabel.setFont(new Font("Klavika",Font.ITALIC,15));
        
        //Fields
        firstnameID.setBounds(140,100,200,25);
        lastnameID.setBounds(140,150,200,25);
        nameID.setBounds(140,200,200,25);
        password.setBounds(140,250,200,25);
        confirmpass.setBounds(140,300,200,25);
        emailAdd.setBounds(140,350,200,25);
        
        submitBtn.setBounds(130,420,100,25);
        submitBtn.setFocusable(false);
        
        terms.setFont(new Font("Arial", Font.PLAIN, 10));
        terms.setBounds(135, 380, 500, 20);
        terms.addActionListener(this);
        
                
        frame.add(HeadS);
        frame.add(firstnameLabel);
        frame.add(lastnameLabel);
        frame.add(firstnameID);
        frame.add(lastnameID);
        frame.add(nameLabel);
        frame.add(passwordLabel);
        frame.add(confirmpassLabel);
        frame.add(emailLabel);
        frame.add(nameID);
        frame.add(password);
        frame.add(confirmpass);
        frame.add(emailAdd);
        frame.add(submitBtn);
        frame.add(terms);
        
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource()==submitBtn) {
					
	}
    }
}