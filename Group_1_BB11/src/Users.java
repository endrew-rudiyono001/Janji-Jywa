
public class Users {

	 static String userId, userName,userEmail,userPassword,userDoB,userGender,userAddress,userPhone,userRole;
	
	

	private static Users user = null;
	
	 
	 public static void loginStatement(String id, String userName, String userEmail, String userPassword, String userDoB, String userGender,
				String userAddress, String userPhone, String userRole) {
		 
		 if(user == null) {
			 user = new Users(id, userName,userEmail,userPassword,userDoB,userGender,userAddress,userPhone,userRole);
		 }
	 }
	 
	 
	
	public Users(String userId, String userName, String userEmail, String userPassword, String userDoB, String userGender,
			String userAddress, String userPhone, String userRole) {
		super();
		Users.userId = userId;
		Users.userName = userName;
		Users.userEmail = userEmail;
		Users.userPassword = userPassword;
		Users.userDoB = userDoB;
		Users.userGender = userGender;
		Users.userAddress = userAddress;
		Users.userPhone = userPhone;
		Users.userRole = userRole;
	}
	
	


	public static String getUserId() {
		return userId;
	}


	public static void setId(String userId) {
		Users.userId = userId;
	}


	public static String getUserName() {
		return userName;
	}


	public static void setUserName(String userName) {
		Users.userName = userName;
	}


	public static String getUserEmail() {
		return userEmail;
	}


	public static void setUserEmail(String userEmail) {
		Users.userEmail = userEmail;
	}


	public static String getUserPassword() {
		return userPassword;
	}


	public static void setUserPassword(String userPassword) {
		Users.userPassword = userPassword;
	}


	public static String getUserDoB() {
		return userDoB;
	}


	public static void setUserDoB(String userDoB) {
		Users.userDoB = userDoB;
	}


	public static String getUserGender() {
		return userGender;
	}


	public static void setUserGender(String userGender) {
		Users.userGender = userGender;
	}


	public static String getUserAddress() {
		return userAddress;
	}


	public static void setUserAddress(String userAddress) {
		Users.userAddress = userAddress;
	}


	public static String getUserPhone() {
		return userPhone;
	}


	public static void setUserPhone(String userPhone) {
		Users.userPhone = userPhone;
	}


	public static String getUserRole() {
		return userRole;
	}


	public static void setUserRole(String userRole) {
		Users.userRole = userRole;
	}


}
