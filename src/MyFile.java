
import java.io.*;

public class MyFile {
	String fileName;
	int size;
	String hash;
	String link;
	String owner;
	int repoid;
	String permissions;
	
	MyFile(String fileName, String link, int repoid) throws IOException{
		this.fileName = fileName;
		this.link = link;
		File file = new File(link);
		this.size = (int) file.length();
		this.owner = GetInfo.getFileOwnerName(link);
		this.hash = GetInfo.getHash(file);
		this.repoid = repoid;
	}
	
	MyFile(){
		
	}
	
}
