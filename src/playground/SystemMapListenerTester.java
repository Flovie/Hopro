package playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import listener.HtmlReader;
import listener.htmlListeners.SystemMapListener;

public class SystemMapListenerTester {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File("./input/systemMap.html")));
		String line = br.readLine();
		StringBuffer sb = new StringBuffer();		
		while(line!=null){
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		HtmlReader htmlReader = new HtmlReader(sb.toString());
		htmlReader.addListener(new SystemMapListener());
		Thread readerThread = new Thread(htmlReader,htmlReader.toString());
    	readerThread.start();		
	}

}
