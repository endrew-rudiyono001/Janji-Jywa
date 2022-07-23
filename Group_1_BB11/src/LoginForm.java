import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.Resultset;

public class LoginForm extends JFrame implements ActionListener, MouseListener{

	
	DatabaseConnection connection = new DatabaseConnection();
	
	JPanel headerPanel = new JPanel(),
			contentPanel = new JPanel(new GridLayout(2,0)),
			footerPanel = new JPanel(new GridLayout(2,1));
	
	JLabel headerLabel = new JLabel("Login Form"),
			emailLabel = new JLabel("Email"),
			passLabel = new JLabel("Password"),
			linkLabel = new JLabel("Sign Up Here");
	
	JTextField emailField = new JTextField();
	
	JPasswordField passField = new JPasswordField();
	
	JButton loginBtn = new JButton("Login");
	
	JPanel emailLabelPanel = new JPanel(),
			emailFieldPanel = new JPanel(),
			passLabelPanel = new JPanel(),
			passFieldPanel = new JPanel(),
			buttonPanel = new JPanel();

	private Users user;
	
	
	public void set() {
		//header
		headerPanel.add(headerLabel);
		
		//content
		emailField.setPreferredSize(new Dimension(200, 40));
		passField.setPreferredSize(new Dimension(200, 40));
		
		emailLabelPanel.add(emailLabel);
		emailFieldPanel.add(emailField);
		passLabelPanel.add(passLabel);
		passFieldPanel.add(passField);
		
		contentPanel.add(emailLabelPanel);
		contentPanel.add(emailFieldPanel);
		contentPanel.add(passLabelPanel);
		contentPanel.add(passFieldPanel);
		
		//footer
		loginBtn.setPreferredSize(new Dimension(100, 30));
		loginBtn.addActionListener(this);
	
		buttonPanel.add(loginBtn);
		linkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		linkLabel.addMouseListener(this);
		
		footerPanel.add(buttonPanel);
		footerPanel.add(linkLabel);
		
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
	}
	
	public void frame() {
		setSize(450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Login Form");
	}
	
	public LoginForm() {
		frame();
		set();
		setVisible(true);
	}

	public static void main(String[] args) {
	new LoginForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String email = emailField.getText();
		String password = new String(passField.getPassword());
		
		if (e.getSource() == loginBtn) {
			if(email.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(LoginForm.this, "Wrong Email/Password!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
			}else {
				String sql = String.format(
						"SELECT * FROM users WHERE UserEmail = '%s' AND UserPassword = '%s'",
						email,password
				);
				
				ResultSet result = connection.executeQuery(sql);
				
				
				try {
					result.next();
					user = null;
					if (email.equals(result.getString("UserEmail")) && password.equals(result.getString("UserPassword"))) {
						if(result.getString("UserRole").equals("Customer")) {
							Users.loginStatement(result.getString("UserID"),
									result.getString("UserName"), 
									result.getString("UserEmail"), 
									result.getString("UserPassword"), 
									result.getString("UserDoB"), 
									result.getString("UserGender"), 
									result.getString("UserAddress"), 
									result.getString("UserPhone"), 
									result.getString("UserRole"));
							new UserMainForm();
							dispose();
							
						}else if (result.getString("UserRole").equals("Admin")){
							Users.loginStatement(result.getString("UserID"),
									result.getString("UserName"), 
									result.getString("UserEmail"), 
									result.getString("UserPassword"), 
									result.getString("UserDoB"), 
									result.getString("UserGender"), 
									result.getString("UserAddress"), 
									result.getString("UserPhone"), 
									result.getString("UserRole"));
							new AdminMainForm();
							dispose();
						}
				}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(LoginForm.this, "Wrong Email/Password!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
					e2.printStackTrace();
				}
			}
		}
	}

	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == linkLabel) {
			new RegisterForm();
			dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
