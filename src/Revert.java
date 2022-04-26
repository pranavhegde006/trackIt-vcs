import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Revert {
	public static void revert(String hashval) throws Exception {
		String remoteLink = getRemoteLink(hashval);
		clearCurrentDirectory();
		createRepo(); 
		
		File folder = new File(remoteLink);
		for(File file : folder.listFiles()) {
			Push.copyDir(file.getPath(), "src/test");
		}
		
		System.out.println("Repo reverted to the specified commit successfully!");
	}
	
	
	public static String getRemoteLink(String hashval) throws Exception {
		final Connection connection = Database.connect();
		if(connection == null)
			return "NULL";
		String ans = new String();
		ResultSet rs = null;
		String q = new String();
		   
		q += "'";
		for(int i = 0; i < hashval.length(); i++) {
			q += hashval.charAt(i);
		}
		q += "'";
		int rows = 0;
		try {
			String query = "select * from remote where remotenumber = (select commitid from commits where hashval = " + q + " limit 1) limit 1";
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);		 
			while(rs.next()) {
				rows++;
				ans = rs.getString(2);
			}
		}
		
		catch(Exception e) {
				throw e;
		}
		
		if(rows == 0)
			return "NULL";
		
		return ans;
	}
	
	
	public static void clearCurrentDirectory() throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("rm -r src/test");
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			System.out.println(line);	
		}
		b.close();
	}
	
	public static void createRepo() throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("mkdir src/test");
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			System.out.println(line);	
		}
		b.close();
	}
	
}
