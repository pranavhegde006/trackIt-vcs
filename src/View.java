import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class View {
	
	public static void getRepo() throws Exception {
		final Connection connection = Database.connect();
		if(connection == null)
			System.out.println("CONNECTION NULL");
		
		ResultSet rs = null;

		
		System.out.println("RepoName" + "\t" + "FileCount" + "\t" + "RepoSize" + "\t" + "RepoLink" + "\t\t\t\t\t\t\t\t\t\t" + "RepoOwner");
		try {
			String query = "select * from repos order by reponame";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2) + "\t\t" + rs.getInt(3) + "\t\t" + rs.getString(5) + "\t\t" + rs.getString(7));
		
			}
		}
		
		catch(Exception e) {
				throw e;
		}
		
	}
	
	
	public static void getFiles() throws Exception {
		
		final Connection connection = Database.connect();
		if(connection == null)
			System.out.println("CONNECTION NULL");
		
		ResultSet rs = null;

		System.out.println("FileName" + "\t" + "FileSize" + "\t" + "FileLink" + "\t\t\t\t\t\t\t\t\t\t\t" + "FileOwner");
		try {
			String query = "select * from files order by filename";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2) + "\t\t" + rs.getString(4) + "\t\t" + rs.getString(5));

			}
		}
		
		catch(Exception e) {
				throw e;
		}
		

	}
	
	
	
	public static void getCommits() throws Exception {
		
		final Connection connection = Database.connect();
		if(connection == null)
			System.out.println("CONNECTION NULL");
		
		ResultSet rs = null;
		
		System.out.println("CommitID" + "\t" + "HashValue");
		try {
			String query = "select * from commits order by commitid";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2));

			}
		}
		
		catch(Exception e) {
				throw e;
		}
		

	}
	
	
	public static void getRemote() throws Exception {
		
		final Connection connection = Database.connect();
		if(connection == null)
			System.out.println("CONNECTION NULL");
		
		ResultSet rs = null;

		
		System.out.println("RemoteName" + "\t" + "RemoteLink" + "\t\t" + "RemoteNumber");
		try {
			String query = "select * from remote order by remotenumber";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getInt(3));
			
			}
		}
		
		catch(Exception e) {
				throw e;
		} 
		

	}
	
	
	public static void displayFiles(String link) {
		
	}
	
}
