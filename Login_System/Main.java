package Login_System;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Kenneth Odgien
 */
public class Main {

	public static void main(String[] args) {
                
                Accounts idandPasswords = new Accounts();
				
		LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());

	}
        
        public class websitePage {

	JFrame frame = new JFrame();
	JLabel greetingLabel = new JLabel("");
	
	websitePage(String userID){
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		greetingLabel.setBounds(10,0,600,35);
		greetingLabel.setFont(new Font(null,Font.PLAIN,25));
		greetingLabel.setText("Welcome to GOSO main Website");
		
		frame.add(greetingLabel);
		
	}
    }
        
}
