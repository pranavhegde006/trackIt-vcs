import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Push {
	
	public static void push(File folder) throws Exception{
		
		
		boolean status = Status.getRemoteStatus();
		if(status == false) {
			int remoteNumber = Database.getRemoteNumber() + 1;
			String dirname = "src/remote/r" + remoteNumber;
			createDir(dirname);
			
			for(File file: folder.listFiles()) {
				String filePath = file.getPath().substring(69);
				copyDir(filePath, dirname);
			}
			
			savePushToDB(dirname, remoteNumber);
		}
		else {
			System.out.println("Remote set to latest commit");
			return;
		}
		System.out.println("Commit pushed!");
		
		
	
		
	}
	
	public static void createDir(String dirname) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("mkdir " + dirname);
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			System.out.println(line);	
		}
		b.close();
	}
	
	public static void copyDir(String filePath, String dirPath) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("cp " + filePath + " " + dirPath);
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			System.out.println(line);	
		}
		b.close();
	}
	
	public static void addFirstRow() {
		final Connection connection = Database.connect();
		try {
			PreparedStatement st = connection.prepareStatement("INSERT INTO remote VALUES (?, ?, ?)");
			st.setString(1, "remote/r1");
			st.setString(2,  "src/remote/r1");
			st.setInt(3, 1);
			st.executeUpdate();
			st.close();
		}
		catch(Exception e) {
			return ;
		}

	}
	
	
	public static void savePushToDB(String remoteLink, int remoteNumber) {
		String remoteName = remoteLink.substring(4);
		final Connection connection = Database.connect();
		try {
			PreparedStatement st = connection.prepareStatement("INSERT INTO remote VALUES (?, ?, ?)");
			st.setString(1, remoteName);
			st.setString(2,  remoteLink);
			st.setInt(3, remoteNumber);
			st.executeUpdate();
			st.close();
		}
		catch(Exception e) {
			return ;
		}
	}
	
}
