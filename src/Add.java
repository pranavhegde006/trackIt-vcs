
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Add {
	
	public static void addFile(MyFile files[]) throws Exception {
		deleterows();
		final Connection connection = Database.connect();
		for(MyFile file: files) {
			try {
				PreparedStatement st = connection.prepareStatement("INSERT INTO FILES VALUES (?, ?, ?, ?, ?, ?)");
				st.setString(1, file.fileName);
				st.setInt(2,  file.size);
				st.setString(3, file.hash);
				st.setString(4,  file.link);
				st.setString(5, file.owner);
				st.setInt(6,  file.repoid);
				st.executeUpdate();
				st.close();
			}
			catch(Exception e) {
				return ;
			}
			
			System.out.println("Files added successfully!");
		}
		
		int dbFileCount = 0, currentFileCount = files.length;
		ResultSet rs = null;
		int dbSize= 0, currentSize = 0;
		for(MyFile file: files) {
			currentSize += file.size;
		}
	
		try {
			String query = "select * from repos where repoid = " + files[0].repoid;
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				dbFileCount = rs.getInt(2);
				dbSize = rs.getInt(3);
			}
		}
		catch(Exception e) {
			throw e;
		}
		
		
		if(dbFileCount != currentFileCount || dbSize != currentSize) {
			String SQLupdate = "UPDATE repos SET filecount = ?, size = ? WHERE repoid = ?";
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(SQLupdate);
				prepareStatement.setInt(1, currentFileCount);
				prepareStatement.setInt(2, currentSize);
				prepareStatement.setInt(3, files[0].repoid);
				prepareStatement.executeUpdate();
			}
			catch(Exception e) {
				throw e;
			}
		}
		
	}
	
	
	private static void deleterows() {	
		final Connection connection = Database.connect();
		if(connection == null)
			return;
		try {
			String query = "DELETE FROM files";
			Statement statement = connection.createStatement();
			statement.executeQuery(query);		 
		}
		catch(Exception e){
			return;
		}
	}
}
