
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class AdminMainForm extends JFrame implements ActionListener{ 


	JMenuBar menuBar = new JMenuBar();
	JMenu profileMenu = new JMenu("Profile");
	JMenu manageMenu = new JMenu("Menu");
	JMenuItem editProfile = new JMenuItem("Edit Profile");
	JMenuItem logOff = new JMenuItem("Log Off");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem manageBeverage = new JMenuItem("Manage Beverage");
	JInternalFrame manageBeverageFrame = new JInternalFrame(),
			editProfileFrame = new JInternalFrame();
	
	JLabel welcomeLabel;
	
	String sql;
	
	DatabaseConnection connection = new DatabaseConnection();
//	String userId;
//	String user;
	
	JDesktopPane desktop = new JDesktopPane();
	Users user = null;
	
	
	public void set() {

		profileMenu.add(editProfile);
		profileMenu.add(logOff);
		profileMenu.add(exit);
		
		welcomeLabel = new JLabel("Welcome to Janji Jywa, " + Users.getUserName());
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		logOff.addActionListener(this);
		editProfile.addActionListener(this);
		manageMenu.add(manageBeverage);
		
		menuBar.add(profileMenu);
		menuBar.add(manageMenu);
		manageBeverage.addActionListener(this);
		exit.addActionListener(this);

		setJMenuBar(menuBar);

		add(welcomeLabel);
	}

	
	public void frame() {
		setSize(870,670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Janji Jywa");
	}	
	
	public AdminMainForm() {
		set();
		frame();
		System.out.println(Users.getUserId());
		setVisible(true);
	}
	
	public void manageBeverage() {
		
	}

	public static void main(String[] args) {
		new AdminMainForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logOff) {
			new LoginForm();
			dispose();
		}else if(e.getSource() == manageBeverage) {
			desktop.removeAll();
			remove(welcomeLabel);
			desktop.add(new ManageBeverageRev1());
			add(desktop);
		}else if(e.getSource() == exit) {
			dispose();
		}else if (e.getSource() == editProfile) {
			desktop.removeAll();
			remove(welcomeLabel);
			desktop.add(new EditProfileForm());
			add(desktop);
		}
		
	} 


}
