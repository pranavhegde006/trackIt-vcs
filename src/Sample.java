
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Sample {
	public static void main(String []args) throws Exception {
		
		File folder = new File("/home/pranavhegde006/Documents/PES/PES Sem 6/CS353 OOAD/Mini-Project/src/test");
		push("src/test/a.txt", "src/remote/r1");
		
	}
	
	public static void push(String path1, String path2) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
//		String[] s = new String[3];
		
		Process p = r.exec("cp " + path1 + " " + path2);
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			System.out.println(line);	
		}
		b.close();
		
//		
// 		for(int i = 0; i < 3; i++) {
//			p = r.exec("cp src/test/" + s[i] + " src/remote/r2/");
//			p.waitFor();
//			b = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			line = "";
//
//			while ((line = b.readLine()) != null) {
//				System.out.println(line);	
//			}
//			b.close();
//		}
		

	}
	
}
