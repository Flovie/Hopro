package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import listener.htmlListeners.GalaxyListener;
import misc.GlobalObjects;

public class FileHtmlListener {
	

	public static void main(String[] args) {
		
		
		// Start log GUI
		GlobalObjects.logger.addLog("Start des Loggers");
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("input/vorTidying.txt")));
			StringBuffer s = new StringBuffer();
			String l;
			l = br.readLine();
			while(l!=null){
				s.append(l);
				l = br.readLine();
			}
			HtmlReader htmlReader = new HtmlReader(s.toString());
        	htmlReader.addListener(new GalaxyListener());
        	Thread readerThread = new Thread(htmlReader,htmlReader.toString());
        	readerThread.start();		        	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				    

	}

}
