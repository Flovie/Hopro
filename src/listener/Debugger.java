package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Debugger {
	
	public static void saveString(String s){
		saveString(s,"String");		
	}
	
	public static void saveString(String s,String filename){
		try {			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
			bw.write(s);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
