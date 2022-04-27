import java.io.*;
import java.util.Scanner;

public class App {
	
	
	public static void main(String[] args) throws Exception {
		
		Login user = Login.loginUser();
		System.out.println();
		
		File folder = new File("/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/test");
		int fileCount = folder.list().length;
		MyFile[] files = new MyFile[fileCount];
		int k = 0;
		for (final File fileEntry : folder.listFiles()) {
			files[k++] = new MyFile(fileEntry.getName(), fileEntry.getAbsolutePath(), 1);
	    }
		
		Scanner scanner = new Scanner(System.in);
		String userInput = new String();
		
		
		if(Login.status == false) {
			System.out.println("User not logged in!");
			return;
		}
		
		for(int i = 0; i < files.length; i++) {
			if(!Login.username.equals(GetInfo.getFileOwnerName(files[i].link))) {
				System.out.println("User not authorized to make changes!");
				return;
			}
		}
		
			
		if(args.length == 1) {	
			switch(args[0]) {
				
			  	case "add":
			  		Add.addFile(files);
			  		break;
			  	case "commit":
			  		Commit.commit(folder);
			  		break;
			  	case "push":
			  		Push.push(folder);
			  		break;
			  	
			  	default:
			  		System.out.println("Exiting from TrackIt!");
			  		return ;
			}
		}
		
		else if (args.length == 2) {
			if(args[0].equals("view")) {
				switch(args[1]) {
					case "files":
				  		View.getFiles();
				  		break;
				  	case "remote":
				  		View.getRemote();
				  		break;
				  	case "repos":
				  		View.getRepo();
				  		break;
				  	case "commits":
				  		View.getCommits();
				  		break;
				  	case "all":
				  		System.out.println("\n");
				  		View.getFiles();
				  		System.out.println("\n");
				  		View.getRemote();
				  		System.out.println("\n");
				  		View.getRepo();
				  		System.out.println("\n");
				  		View.getCommits();
				  		System.out.println("\n");
				  	default:
				  		System.out.println("Exiting from TrackIt!");
				  		return;
				}
			}
			
			else if(args[0].equals("revert")) {
		  		String hashval = args[1];
		  		Revert.revert(hashval);
			}
		}
		else if(args.length == 3) {
			if(args[0].equals("add") && args[1].equals("commit") && args[2].equals("push")) {
				Add.addFile(files);
		  		Commit.commit(folder);
		  		Push.push(folder);
			}
		}
		
	  		
	  		
		}

}
