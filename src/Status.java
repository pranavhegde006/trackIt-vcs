import java.io.File;


public class Status {
	 
	public static boolean getStatus(File folder) throws Exception {
		String dbStatus = Database.getLatestHash();
		
		String currentStatus = GetInfo.getRepoHash(folder);

		return currentStatus.equals(dbStatus);
		
	}
	
}
