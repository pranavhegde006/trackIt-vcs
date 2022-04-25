import java.io.*;

public class App {
	public static void main(String[] args) throws Exception {
		
		File folder = new File("/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/test");		
		String folderPath = "/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/test/";		
		String filePath = "src/test/b.txt";
        File file = new File(filePath);
        String path = file.getPath();
        String absPath = file.getAbsolutePath();
		
		
		String owner = GetInfo.getFileOwnerName(absPath);
		
//		File file = new File(path);
		String hash = GetInfo.getHash(file);
		
		String res = GetInfo.getRepoHash(folder);
		
		String latestHash = Database.getLatestHash();
		
		boolean stat = Status.getStatus(folder);
		
		int fc = Database.getFileCount(1);
		
		
		int fileCount = folder.list().length, k = 0;
		MyFile[] files = new MyFile[fileCount];
		
		for (final File fileEntry : folder.listFiles()) {
			files[k++] = new MyFile(fileEntry.getName(), fileEntry.getAbsolutePath(), 1);
	    }

		
//		Revert.revert("f6da9b9a8579da1071f37434aacebab0");
		
		Add.addFile(files);
		Commit.commit(folder);
		Push.push(folder);
//		
		System.out.println();
		
		View.getRemote();
		System.out.println();
		View.getCommits();
		System.out.println();
		View.getFiles();
		System.out.println();
		View.getRepo();
		System.out.println();
		

	}
	
	
}
