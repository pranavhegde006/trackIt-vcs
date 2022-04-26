

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetInfo { 
	
	public static String getFileOwnerName(String path) throws IOException{
		Path myPath = Paths.get("");
        
        UserPrincipal userPrincipal = Files.getOwner(myPath);
        String owner = userPrincipal.getName();
        
        return owner;
	}
	

	public static String getHash(File file) throws IOException{
		
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
	
	public static String calculateChecksum(MessageDigest digest, File file) throws IOException{
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
	
	public static String getMD5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
  
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	public static String getRepoHash(File folder) throws IOException {
		String totalHash = new String();
		
		for (final File fileEntry : folder.listFiles()) {		        
			String hash1 = GetInfo.getHash(fileEntry);
			totalHash += hash1;
		}
		
		totalHash = getMD5(totalHash);
		return totalHash;
	}
	

	
}
