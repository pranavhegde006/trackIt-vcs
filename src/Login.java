
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
	static String username;
	static String password;
	static boolean status = false;
	
	private static Login temp;
	
	private Login(){
		loginCLI();
	}
	
	static Login loginUser() {
		if(temp == null) {
			temp = new Login();
		}
		return temp;
	}
	
	public static boolean auth(String dbHash) {
		
		String userPassHash = GetInfo.getMD5(password);
		
		if(dbHash.equals(userPassHash))
			return true;
		
		return false;
	}
	
	public static void loginCLI() {
		System.out.println("Welcome to TrackIt! Login to proceed...\n");
	    Scanner scanner = new Scanner (System.in);
	    System.out.print("Enter your username: ");
	    String userInp = scanner.nextLine();
	    System.out.print("Enter your password: ");
	    String passInp = scanner.nextLine(); 
	    scanner.close();
	    
	    setCredential(userInp, passInp);
	    String passDB = getPassHash(userInp);
	    
	    if(auth(passDB)) {
	    	status = true;
	    	System.out.println("Successfully logged you in!");
	    	return ;
	    }
	     
	    System.out.println("There was a problem logging you in!\n\n");
	}
	
	public static void setCredential(String uname, String pass) {
		try {
			username = uname;
			password = pass;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public static String getPassHash(String userInp) {
		final Connection connection = Database.connect();
		if(connection == null)
			return "NULL";
		String ans = new String();
		ResultSet rs = null;
		String q = new String();
		   
		q += "'";
		for(int i = 0; i < userInp.length(); i++) {
			q += userInp.charAt(i);
		}
		q += "'";
		
		int rows = 0;
		try {
			String query = "select * from credentials where username = " + q;
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getString(2);
			}
		}
		
		catch(Exception e) {
				return "NULL";
		}
		
		if(rows == 0) {
			System.out.println("User doesn't exist!");
			return "NULL";
		}	
		
		return ans;
	}
	
}
