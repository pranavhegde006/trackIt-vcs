import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Commit {
	public static void commit(File folder) throws Exception {
		boolean status = Status.getStatus(folder);
		if(status == false) {
			commitToDB(folder);
		}
		else {
			System.out.println("Repo updtodate");
			return;
		}
		System.out.println("Changes committed!");
	}
	
	
	private static void commitToDB(File folder) throws Exception{
		final Connection connection = Database.connect();
		int commitid = getLastCommitid() + 1;
		String hash = GetInfo.getRepoHash(folder);
		int repoid = 1; //HARDCODED REPOID VALUE		
	
		
		try {
			PreparedStatement st = connection.prepareStatement("INSERT INTO commits VALUES (?, ?, ?)");
			st.setInt(1, commitid);
			st.setString(2, hash);
			st.setInt(3, repoid);
			st.executeUpdate();
			st.close();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	
	private static int getLastCommitid() {
		final Connection connection = Database.connect();
		if(connection == null)
			return -1;
		int ans = -1;
		ResultSet rs = null;

		int rows = 0;
		try {
			String query = "SELECT * FROM commits order by commitid desc limit 1";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getInt(1);
			}
		}
		
		catch(Exception e) {
				return -1;
		}
		
		if(rows == 0) {
			return 0;
		}	
		
		
		return ans;
	}
	
}
