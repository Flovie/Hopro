package playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import horiversumObjects.Universe;
import listener.HtmlReader;
import listener.htmlListeners.HighscoreListener;

public class HighscoreListenerTester {
	
	public static void main(String[] args) throws IOException{
		Universe.loadAll();
		BufferedReader br = new BufferedReader(new FileReader(new File("./input/highscore.html")));
		String line = br.readLine();
		StringBuffer sb = new StringBuffer();		
		while(line!=null){
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		HtmlReader htmlReader = new HtmlReader(sb.toString());
    	htmlReader.addListener(new HighscoreListener());
    	htmlReader.run();  
    	Universe.saveAll();
	}

}
