
//import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Database {

	static Connection connect() {
		Connection connection = null;			
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vcs", "postgres", "1234");	
			
			if(connection == null) {
				return null;
			}
		}
		catch(Exception e){
			return null;
		}
		
		return connection;
	}
	
	
	public static String getLatestHash() throws Exception {
		final Connection connection = connect();
		if(connection == null)
			return "no conn";
		String ans = new String();
		ResultSet rs = null;

		int rows = 0;
		try {
			String query = "select * from commits order by commitid desc limit 1";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getString(2);
			}
		}
		
		catch(Exception e) {
			throw e;
//				return "NULL";
		}
		
		if(rows == 0) {
			return "NULL";
		}	
		
		return ans;

	}
	
	
	public static String getFileHash(String fileName){
		
		final Connection connection = connect();
		if(connection == null)
			return "NULL";
		String ans = new String();
		ResultSet rs = null;
		String q = new String();
		   
		q += "'";
		for(int i = 0; i < fileName.length(); i++) {
			q += fileName.charAt(i);
		}
		q += "'";
		
		int rows = 0;
		try {
			String query = "select * from files where filename = " + q;
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getString(3);
			}
		}
		
		catch(Exception e) {
				return "NULL";
		}
		
		if(rows == 0) {
			System.out.println("Query results empty!");
			return "NULL";
		}	
		
		return ans;
	}

	
	public static int getFileCount(int repoid) {
		final Connection connection = connect();
		if(connection == null)
			return -1;
		int ans = -1;
		ResultSet rs = null;

		int rows = 0;
		try {
			String query = "select * from repos where repoid = " + repoid;
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getInt(2);
			}
		}
		
		catch(Exception e) {
				return -1;
		}
		
		if(rows == 0) {
			System.out.println("Query results empty!");
			return -1;
		}	
		
		
		return ans;
	}
	
	
	public static int getRemoteNumber() throws Exception {
		final Connection connection = connect();
		if(connection == null)
			return -1;
		int ans = 0;
		ResultSet rs = null;

		int rows = 0;
		try {
			String query = "select * from remote order by remotenumber desc limit 1";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getInt(3);
			}
		}
		
		catch(Exception e) {
				throw e;
		}
		
		return ans;
	}
	
	
	public static int getCommitID() throws Exception {
		final Connection connection = connect();
		if(connection == null)
			return -1;
		int ans = 0;
		ResultSet rs = null;

		int rows = 0;
		try {
			String query = "select * from commits order by commitid desc limit 1";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getInt(1);
			}
		}
		
		catch(Exception e) {
				throw e;
		}
		
		if(rows == 0)
			return -1;
		
		return ans;
	}
	
}

