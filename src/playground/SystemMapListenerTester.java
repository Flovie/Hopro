package playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import horiversumObjects.Universe;
import listener.HtmlReader;
import listener.htmlListeners.GalaxyListener;
import listener.htmlListeners.SystemMapListener;

public class SystemMapListenerTester {
	
	public static void main(String[] args) throws IOException{
		Universe.load();
		System.out.println(Universe.getHoproDataSet().getSystem(1, 815));
		BufferedReader br = new BufferedReader(new FileReader(new File("./input/systemMap.html")));
		String line = br.readLine();
		StringBuffer sb = new StringBuffer();		
		while(line!=null){
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		HtmlReader htmlReader = new HtmlReader(sb.toString());
		htmlReader.addListener(new GalaxyListener());
		htmlReader.addListener(new SystemMapListener());
		htmlReader.run();
//		System.out.println(Universe.getPlanetMap().getSystem(1, 815));
    	Universe.save();
	}

}
