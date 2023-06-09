package Login_System;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Kenneth Odgien
 */
public class SignUp extends Accounts implements ActionListener{
    
    JFrame frame = new JFrame();
    JLabel HeadS = new JLabel("REGISTRATION FORM");
    JTextField nameID = new JTextField();
    JLabel nameLabel = new JLabel("Username: ");
    JPasswordField password = new JPasswordField("");
    JLabel passwordLabel = new JLabel("Password: ");
    JPasswordField confirmpass = new JPasswordField();
    JLabel confirmpassLabel = new JLabel("Confirm Password: ");
    JTextField emailAdd = new JTextField();
    JLabel emailLabel = new JLabel("Email: ");
    JButton submitBtn = new JButton("Submit");
    JLabel messageLabel = new JLabel();
    
    //Constructor
    SignUp(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(520, 420);
	frame.setLayout(null);
	frame.setVisible(true);
        
        HeadS.setFont(new Font("Klavika", Font.PLAIN, 30));
        HeadS.setBounds(100,20,1000, 40);
        
        //Labels
        nameLabel.setBounds(60,100,80,25);
        passwordLabel.setBounds(60,150,80,25);
        confirmpassLabel.setBounds(12,200, 200,25);
        emailLabel.setBounds(85,250,80,25);
        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font("Klavika",Font.ITALIC,15));
        
        //Fields
        nameID.setBounds(140,100,200,25);
        password.setBounds(140,150,200,25);
        confirmpass.setBounds(140,200,200,25);
        emailAdd.setBounds(140,250,200,25);
        
        submitBtn.setBounds(130,300,100,25);
        submitBtn.setFocusable(false);
        
                
        frame.add(HeadS);
        frame.add(nameLabel);
        frame.add(passwordLabel);
        frame.add(confirmpassLabel);
        frame.add(emailLabel);
        frame.add(nameID);
        frame.add(password);
        frame.add(confirmpass);
        frame.add(emailAdd);
        frame.add(submitBtn);
        
        
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource()==submitBtn) {
					
	}
    }
}