import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegisterForm extends JFrame implements ActionListener, MouseListener{

	JPanel headerPanel = new JPanel(),
			contentPanel = new JPanel(new GridLayout(0,2, 30 , 30)),
			footerPanel = new JPanel(new GridLayout(2,1));
	

	
	JLabel headerLabel = new JLabel("Register Form"),
			idLabel = new JLabel("ID"),
			usernameLabel = new JLabel("User Name"),
			emailLabel = new JLabel("Email"),
			phoneLabel = new JLabel("Phone"),
			addressLabel = new JLabel("Address"),
			passwordLabel = new JLabel("Password"),
			genderLabel = new JLabel("Gender"),
			roleLabel = new JLabel("Role"),
			linkLabel = new JLabel("Sign In");
	
	
	JTextField idField = new JTextField(),
			usernameField = new JTextField(),
			emailField = new JTextField(),
			phoneField = new JTextField();
	
	JTextArea addressField = new JTextArea();
	
	JPasswordField passwordField = new JPasswordField();
	
	JRadioButton rFemale = new JRadioButton("Female"),
			rMale = new JRadioButton("Male");
	
	ButtonGroup genderGroup = new ButtonGroup();
	
	String[] role = new String[] {"Admin" , "Customer"};
	JComboBox<String> roleCbx = new JComboBox<String>(role);
	
	JButton signinBtn = new JButton("Register");
	
	JPanel idLabelPanel = new JPanel(new BorderLayout()),
			idFieldPanel = new JPanel(new BorderLayout()),
			usernameLabelPanel = new JPanel(new BorderLayout()),
			usernameFieldPanel = new JPanel(new BorderLayout()),
			emailLabelPanel = new JPanel(new BorderLayout()),
			emailFieldPanel = new JPanel(new BorderLayout()),
			phoneLabelPanel = new JPanel(new BorderLayout()),
			phoneFieldPanel = new JPanel(new BorderLayout()),
			addressLabelPanel = new JPanel(new BorderLayout()),
			addressFieldPanel = new JPanel(new BorderLayout()),
			passwordLabelPanel = new JPanel(new BorderLayout()),
			passwordFieldPanel = new JPanel(new BorderLayout()),
			genderLabelPanel = new JPanel(new BorderLayout()),
			genderGroupPanel = new JPanel(),
			roleLabelPanel = new JPanel(new BorderLayout()),
			roleComboPanel = new JPanel(new BorderLayout()),
			buttonPanel = new JPanel();
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public void set() {
		
		setLayout(new BorderLayout(5,5));
		//header
		headerPanel.add(headerLabel);
		
//		//content
//		idField.setPreferredSize(new Dimension(200, 40));
//		usernameField.setPreferredSize(new Dimension(200, 40));
//		emailField.setPreferredSize(new Dimension(200, 40));
//		phoneField.setPreferredSize(new Dimension(200, 40));
//		addressField.setPreferredSize(new Dimension(200, 135));
//		passwordField.setPreferredSize(new Dimension(200, 40));
//		roleCbx.setPreferredSize(new Dimension(200,40));
		idField.setEditable(false);
		
		idLabelPanel.add(idLabel);
		idFieldPanel.add(idField);
		usernameLabelPanel.add(usernameLabel);
		usernameFieldPanel.add(usernameField);
		emailLabelPanel.add(emailLabel);
		emailFieldPanel.add(emailField);
		phoneLabelPanel.add(phoneLabel);
		phoneFieldPanel.add(phoneField);
		addressLabelPanel.add(addressLabel);
		addressFieldPanel.add(addressField);
		passwordLabelPanel.add(passwordLabel);
		passwordFieldPanel.add(passwordField);
		genderLabelPanel.add(genderLabel);
		rMale.setActionCommand("Male");
		rFemale.setActionCommand("Female");
		genderGroup.add(rMale);
		genderGroup.add(rFemale);
		genderGroupPanel.add(rMale);
		genderGroupPanel.add(rFemale);
		roleLabelPanel.add(roleLabel);
		roleComboPanel.add(roleCbx);
		
		contentPanel.add(idLabelPanel);
		contentPanel.add(idFieldPanel);
		contentPanel.add(usernameLabelPanel);
		contentPanel.add(usernameFieldPanel);
		contentPanel.add(emailLabelPanel);
		contentPanel.add(emailFieldPanel);
		contentPanel.add(phoneLabelPanel);
		contentPanel.add(phoneFieldPanel);
		contentPanel.add(addressLabelPanel);
		contentPanel.add(addressFieldPanel);
		contentPanel.add(passwordLabelPanel);
		contentPanel.add(passwordFieldPanel);
		contentPanel.add(genderLabelPanel);
		contentPanel.add(genderGroupPanel);
		contentPanel.add(roleLabelPanel);
		contentPanel.add(roleComboPanel);
		
		generateId();
		
		//footer
		signinBtn.setPreferredSize(new Dimension(100, 30));
		signinBtn.addActionListener(this);
		
		linkLabel.addMouseListener(this);
		
		buttonPanel.add(signinBtn);
		footerPanel.add(buttonPanel);
		footerPanel.add(linkLabel);
		
		linkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
	}
	
	public void frame() {
		setSize(750,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Register Form");
	}
	
	public RegisterForm() {
		frame();
		set();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RegisterForm();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == linkLabel) {
			new LoginForm();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		//validasi value
	
		String id=null,
				username = null,
				email = null,
				phone = null,
				address = null,
				password = null,
				gender = null,
				role = null;
		
		try {
				id = idField.getText();
				username = usernameField.getText();
				email = emailField.getText();
				phone = phoneField.getText();
				address = addressField.getText();
				password = new String(passwordField.getPassword());
				gender = genderGroup.getSelection().getActionCommand();
				role = (String) roleCbx.getSelectedItem();
		}catch(Exception a){
			a.printStackTrace();
		}
		if(e.getSource() == signinBtn) {
			if (id.isEmpty() || username.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || gender.isEmpty() || role.isEmpty()) {
				JOptionPane.showMessageDialog(RegisterForm.this, "The value must be filled!" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			}else if (username.length() < 5 || username.length() > 30) {
				JOptionPane.showMessageDialog(RegisterForm.this, "Username invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);	
			}else if(!emailIsValid(email)) {
				JOptionPane.showMessageDialog(RegisterForm.this, "Email invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			} else if(!passwordIsValid(password)) {
				JOptionPane.showMessageDialog(RegisterForm.this, "Password invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			}else if (!address.endsWith(" Street")) {
				JOptionPane.showMessageDialog(RegisterForm.this, "Address invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
			}else if(!phoneIsValid(phone)) {
				JOptionPane.showMessageDialog(RegisterForm.this, "Phone invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
				
			}else {
				String sql = String.format
						("INSERT INTO users(UserID,Username,UserEmail,UserPassword,UserGender,UserAddress,UserPhone,UserRole) VALUE('%s','%s','%s','%s','%s','%s','%s','%s')"
						, id,username,email,password,gender,address,phone,role);
				connection.execute(sql);
				
				JOptionPane.showMessageDialog(RegisterForm.this, "Successfully Registered" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
				
				new LoginForm();
				dispose();
			}
	}
		
	}	
	
	public void generateId() {
		String sql = "SELECT CAST(RIGHT(MAX(UserID), 3) AS INTEGER) AS 'totalUser' FROM users";
		ResultSet result = connection.executeQuery(sql);
		String newIdValue = new String();
		try {
		if (result.next()) {
			int len = result.getInt("totalUser");
			newIdValue += "US";
			newIdValue += String.format("%03d", (len+1));
			idField.setText(newIdValue);
			len++;
		}
	} catch (Exception e2) {
		e2.printStackTrace();
	}
}

	
	public boolean emailIsValid(String email) {
		boolean itsOne = false;
		int valid = 0;
		boolean emailValid = true;
		
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				valid++;
			}
		}
		
		if(valid == 1) {
			itsOne = true;
		}else {
			itsOne = false;
		}
		
		if(email.startsWith("@")  || email.endsWith("@") || email.startsWith(".") || email.endsWith(".") || !email.endsWith(".com") || itsOne == false) {
			emailValid = false;
		} else {
			emailValid = true;
		}
		
		return emailValid;
	}
	
	
	public boolean passwordIsValid(String password) {
		boolean passwordValid = true;
		
		
		if (password.length() < 5 || password.length() > 30) {
			passwordValid = false;
		}else {
			for (int i = 0; i < password.length(); i++) {
				if (!(password.charAt(i) >= 'a' && password.charAt(i) <= 'z')  || !(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') && (password.charAt(i) >= '0' && password.charAt(i) <= '9')) {
					passwordValid = true;
				}else {
					passwordValid = false;
				}
			}
		}
		
		return passwordValid;
	}
	
	
	public boolean phoneIsValid(String phone) {
		boolean phoneValid = true;
		
		if (phone.length() < 12) {
			phoneValid = false;
		}else {
			for (int i = 0; i < phone.length(); i++) {
				if (phone.charAt(i) >= '0' && phone.charAt(i) <= '9') {
					phoneValid = true;
				}else {
					phoneValid = false;
				}
			}
		}
		
		return phoneValid;
	}
	

}
