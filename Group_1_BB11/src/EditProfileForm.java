import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

public class EditProfileForm extends JInternalFrame implements ActionListener{

	JPanel headerPanel = new JPanel(new GridLayout(0,2)),
			contentPanel = new JPanel(new GridLayout(0,2,20, 15)),
			footerPanel = new JPanel(new GridLayout(0,2));
	
	JPanel editForm = new JPanel(new GridLayout(0,2, 20, 15)),
			passForm = new JPanel(new GridLayout(0,2, 20, 15));
	
	
	JPanel updatePanel = new JPanel(),
			changePanel = new JPanel();
	JLabel updateLabel = new JLabel("Update Profile"),
			changeLabel = new JLabel("Change Password");
	
	JLabel usernameLabel = new JLabel("Username"),
			emailLabel = new JLabel("User Email"),
			phoneLabel = new JLabel("User Phone"),
			addressLabel = new JLabel("User Address"),
			genderLabel = new JLabel("User Gender");
	
	JLabel oldPassLabel = new JLabel("Old Password"),
			newPassLabel = new JLabel("New Password"),
			retypePassLabel = new JLabel("Confirm Password");
	
	JPanel usernameLabelPanel = new JPanel(new BorderLayout()),
			usernameFieldPanel = new JPanel(new BorderLayout()),
			emailLabelPanel = new JPanel(new BorderLayout()),
			emailFieldPanel = new JPanel(new BorderLayout()),
			phoneLabelPanel = new JPanel(new BorderLayout()),
			phoneFieldPanel = new JPanel(new BorderLayout()),
			addressLabelPanel = new JPanel(new BorderLayout()),
			addressFieldPanel = new JPanel(new BorderLayout()),
			genderLabelPanel = new JPanel(new BorderLayout()),
			genderFieldPanel = new JPanel();
	
	JPanel oldPassLabelPanel = new JPanel(new BorderLayout()),
			oldPassFieldPanel = new JPanel(new BorderLayout()),
			newPassFieldPanel = new JPanel(new BorderLayout()),
			newPassLabelPanel = new JPanel(new BorderLayout()),
			retypePassLabelPanel = new JPanel(new BorderLayout()),
			retypePassFieldPanel = new JPanel(new BorderLayout());
	
	
	JTextField usernameField = new JTextField(),
			emailField = new JTextField(),
			phoneField = new JTextField();
	
	JTextArea addressField = new JTextArea();
	
	JRadioButton rMale = new JRadioButton("Male"),
			rFemale = new JRadioButton("Female");
	
	ButtonGroup genderGroup = new ButtonGroup();
	
			
	JPasswordField oldPassField = new JPasswordField(),
			newPassField = new JPasswordField(),
			retypePassField = new JPasswordField();
	
	JButton updateButton = new JButton("Update Profile"),
			changeButton = new JButton("Change Password");
	
	DatabaseConnection connection = new DatabaseConnection();
	
//	JInternalFrame interFrame = new JInternalFrame();
	
	public void frame() {
		setLayout(new BorderLayout());
//		interFrame.setVisible(true);

		updatePanel.add(updateLabel);
		changePanel.add(changeLabel);
		
		usernameLabelPanel.add(usernameLabel);
		usernameFieldPanel.add(usernameField);
		
		emailLabelPanel.add(emailLabel);
		emailFieldPanel.add(emailField);
		
		phoneLabelPanel.add(phoneLabel);
		phoneFieldPanel.add(phoneField);
		
		addressLabelPanel.add(addressLabel);
		addressFieldPanel.add(addressField);
		
		genderLabelPanel.add(genderLabel);
		genderFieldPanel.add(rMale);
		genderFieldPanel.add(rFemale);
		
		
		editForm.add(usernameLabelPanel);
		editForm.add(usernameFieldPanel);
		editForm.add(emailLabelPanel);
		editForm.add(emailFieldPanel);
		editForm.add(phoneLabelPanel);
		editForm.add(phoneFieldPanel);
		editForm.add(addressLabelPanel);
		editForm.add(addressFieldPanel);
		editForm.add(genderLabelPanel);
		editForm.add(genderFieldPanel);
		
		oldPassLabelPanel.add(oldPassLabel);
		oldPassFieldPanel.add(oldPassField);
		newPassLabelPanel.add(newPassLabel);
		newPassFieldPanel.add(newPassField);
		retypePassLabelPanel.add(retypePassLabel);
		retypePassFieldPanel.add(retypePassField);
		
		passForm.add(oldPassLabelPanel);
		passForm.add(oldPassFieldPanel);
		passForm.add(newPassLabelPanel);
		passForm.add(newPassFieldPanel);
		passForm.add(retypePassLabelPanel);
		passForm.add(retypePassFieldPanel);
		
		contentPanel.add(editForm);
		contentPanel.add(passForm);
		
		
		rMale.setActionCommand("Male");
		rFemale.setActionCommand("Female");
		
		genderGroup.add(rMale);
		genderGroup.add(rFemale);

		headerPanel.add(updatePanel);
		headerPanel.add(changePanel);
		
		footerPanel.add(updateButton);
		footerPanel.add(changeButton);
		
		updateButton.addActionListener(this);
		changeButton.addActionListener(this);
		
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
//		add(interFrame);
	}
	
	public EditProfileForm() {
		frame();
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new EditProfileForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = null,
				email = null,
				phone =  null,
				address = null,
				gender = null;
		
		try {
				username = usernameField.getText();
					email = emailField.getText();
					phone =  phoneField.getText();
					address = addressField.getText();
					gender = genderGroup.getSelection().getActionCommand();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		String oldPass = new String (oldPassField.getPassword()),
				newPass = new String(newPassField.getPassword()),
				retypePass = new String (retypePassField.getPassword());
		
		if(e.getSource() == updateButton) {
			if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "The value must be filled!" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			} else if (username.length() < 5 || username.length() > 30) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "Username invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);	
			}else if(!emailIsValid(email)) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "Email invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			} else if (!address.endsWith(" Street")) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "Address invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
			}else if(!phoneIsValid(phone)) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "Phone invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
				
			}else {
				
				int option = JOptionPane.showConfirmDialog(EditProfileForm.this, "Are You Sure Want To Update Profile", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				switch (option) {
				case JOptionPane.YES_OPTION:
					String sql = String.format ("UPDATE users SET UserName = '%s' , UserEmail = '%s', UserAddress = '%s', UserPhone = '%s', UserGender= '%s' WHERE UserID= '%s'"
					, username,email,address,phone,gender, Users.getUserId());
					connection.execute(sql);
			
				JOptionPane.showMessageDialog(EditProfileForm.this, "Successfully Updated Your Profile" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
			
					break;

				default:
					break;
				}
				
				
			}
		}else if(e.getSource() == changeButton) {
			
			
			if (newPass.isEmpty() || oldPass.isEmpty() || retypePass.isEmpty()) {
				JOptionPane.showMessageDialog(EditProfileForm.this, "The value must be filled!" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				String query = String.format( "SELECT UserPassword FROM users WHERE UserID = '%s'", Users.getUserId());
				
				ResultSet result = connection.executeQuery(query);
				
				try {
					result.next();
					if (oldPass.equals(result.getString("UserPassword"))) {
						if (newPass.equals(retypePass)) {
							int option = JOptionPane.showConfirmDialog(EditProfileForm.this, "Are You Sure Want To Change Your Password?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							switch (option) {
							case JOptionPane.YES_OPTION:
								String sql = String.format
								("UPDATE users SET UserPassword = '%s' WHERE UserID = '%s'"
								, newPass, Users.getUserId());
								connection.execute(sql);
							JOptionPane.showMessageDialog(EditProfileForm.this, "Successfully Change Your Password" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
						
								break;

							default:
								break;
							}
						}else {
							JOptionPane.showMessageDialog(EditProfileForm.this, "New Password not Match With Confirm Password" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
						}
					}else {
						JOptionPane.showMessageDialog(EditProfileForm.this, "Old Password Invalid" , "Message" ,JOptionPane.INFORMATION_MESSAGE);				
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			
			
			
		}
	}
	
	
	public boolean emailIsValid(String email) {
		 String emailValidation = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 Pattern emailPattern = Pattern.compile(emailValidation);
		 Matcher valid = emailPattern.matcher(email);
		 return valid.matches();
		 
	}
	public boolean passwordIsValid(String password) {
		String passwordValidation = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{5,30}$";
		Pattern passwordPattern = Pattern.compile(passwordValidation);
		 Matcher valid = passwordPattern.matcher(password);
		 return valid.matches();
	}

	public boolean phoneIsValid(String phone) {
		String phoneValidation = "^(?:[0-9] ?){11}[0-9]$";
		Pattern phonePattern = Pattern.compile(phoneValidation);
		 Matcher valid = phonePattern.matcher(phone);
		 return valid.matches();
	}

}
