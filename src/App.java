import java.io.*;
import java.security.*;

public class App {
	public static void main(String[] args) {
//		File file = new File("/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/a.txt");
		File folder = new File("/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        String fileName = file.getName();
		        String hashval = new String();
				String fileID = new String();
				
				try {
					hashval = getHash(file);
				}
				catch(Exception e) {
					System.out.println(e);
				}
//				System.out.println("The file name is: " + fileName + " and the hash value of the file is " + hashval);
		    }
		}
		
	}
	
	private static String getHash(File file) throws IOException{
		
		String shaChecksum = new String();
		try {
			MessageDigest shaDigest = MessageDigest.getInstance("MD5");
			shaChecksum = calculateChecksum(shaDigest, file);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return shaChecksum;
	}
	
	private static String calculateChecksum(MessageDigest digest, File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[1024];
		int bytesCount = 0; 
	    
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		};
	   
		fis.close();
		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++){
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
