package listener;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import listener.htmlListeners.HtmlListener;

public class HtmlReader implements Runnable{
	
	private List<HtmlListener> listeners = new ArrayList<HtmlListener>();
	
	private final String htmlSource;
	
	private static int counter = 0;
	
	private final int uniqueId = HtmlReader.counter++;
	
	public HtmlReader(String htmlSource){
		this.htmlSource = htmlSource;
	}

	@Override
	public void run() {
		Document doc = this.parseHtml(this.htmlSource);
		for(HtmlListener listener: listeners){
			listener.interpretHtml(doc);
		}
		
	}
	
	public void addListener(HtmlListener htmlListener){
		this.listeners.add(htmlListener);
	}
	
	private Document parseHtml(String htmlSource){		
			Document doc = Jsoup.parse(tidyUpHtmlSource(htmlSource));
//			
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
			return doc;

			
	}
	
	public String toString() {
		return "HtmlReader_" + this.uniqueId; 
	}
	
	public static String cleanUpForParsingNumber(String s){
		String result = s.trim().replace("\\", "").replace("'","").replace(",", ".");
		return result;
	}
	
	private String tidyUpHtmlSource(String htmlSource){
		String result = htmlSource.replace("\\\"", "\"");
		Debugger.saveString(htmlSource,"lastHtmlRead.html");
		return result;
	}


}