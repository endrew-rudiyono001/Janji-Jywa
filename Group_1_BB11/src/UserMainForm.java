
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class UserMainForm extends JFrame implements ActionListener{


	JMenuBar menuBar = new JMenuBar();
	JMenu profileMenu = new JMenu("Profile");
	JMenu transactionMenu = new JMenu("Transaction");
	JMenuItem editProfile = new JMenuItem("Edit Profile");
	JMenuItem logOff = new JMenuItem("Log Off");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem buyBeverage = new JMenuItem("Buy Beverage");
	JMenuItem viewTransactionBeverage = new JMenuItem("View Transaction Beverage");

	JLabel welcomeLabel;
	
	JDesktopPane desktop = new JDesktopPane();

	public void set() {
		profileMenu.add(editProfile);
		profileMenu.add(logOff);
		profileMenu.add(exit);
		
		welcomeLabel = new JLabel("Welcome to Janji Jywa, " + Users.getUserName());
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		transactionMenu.add(buyBeverage);
		transactionMenu.add(viewTransactionBeverage);
		viewTransactionBeverage.addActionListener(this);
		exit.addActionListener(this);
		buyBeverage.addActionListener(this);
		logOff.addActionListener(this);
		editProfile.addActionListener(this);
		menuBar.add(profileMenu);
		menuBar.add(transactionMenu);
		setJMenuBar(menuBar);

		add(welcomeLabel, BorderLayout.CENTER);
	}

	
	public void frame() {
		setSize(870,670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Janji Jywa");
	}
	
	public UserMainForm() {
		set();
		frame();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new UserMainForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewTransactionBeverage) {
			desktop.removeAll();
			remove(welcomeLabel);
			desktop.add(new TransactionForm());
			add(desktop);
		}else if(e.getSource() == exit) {
			dispose();
		}else if(e.getSource() == buyBeverage) {
			desktop.removeAll();
			remove(welcomeLabel);
			desktop.add(new BuyBeverageFormRev1());
			add(desktop);
		}else if(e.getSource() == editProfile) {
			desktop.removeAll();
			remove(welcomeLabel);
			desktop.add(new EditProfileForm());
			add(desktop);
		}
		
	}

}
