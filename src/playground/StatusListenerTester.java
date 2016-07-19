package playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import listener.HtmlReader;
import listener.htmlListeners.StatusListener;

public class StatusListenerTester {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("./input/status.html")));
		String line = br.readLine();
		StringBuffer sb = new StringBuffer();		
		while(line!=null){
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		HtmlReader htmlReader = new HtmlReader(sb.toString());
    	htmlReader.addListener(new StatusListener());
    	htmlReader.run();  

	}

}
