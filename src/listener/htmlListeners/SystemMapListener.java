package listener.htmlListeners;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.GalaxySystem;
import misc.GlobalObjects;

public class SystemMapListener extends HtmlListener {

	public void interpretHtml(Document htmlSource) {
		Elements titleElements = htmlSource.select("title");
		if (titleElements.first()!=null){
			String title = titleElements.first().text();
			// FILTER
			if (title.equalsIgnoreCase("Horizon - Galaxie")){				
				Elements tmp = htmlSource.select(".GalaxySystemLink, .GalaxySystemLinkColonized");
				for(Element e: tmp){
					int startIndex = 0;
					int nextIndex = 0;					
					String entry = e.attr("onmouseover");
					// Find id
					startIndex = entry.indexOf("<td>System<td>")+21;
					nextIndex = entry.indexOf("-",startIndex)-1;
					boolean noName = false;
					if (nextIndex - startIndex >5){
						nextIndex = entry.indexOf("<tr>",startIndex);
						noName=true;
					}
					String[] id = entry.substring(startIndex, nextIndex).split(":");
					int galaxy = Integer.parseInt(id[0].trim());
					int system = Integer.parseInt(id[1].trim());
					GalaxySystem s = GalaxySystem.getInstance(galaxy, system);
					GlobalObjects.logger.addLog("Discovered data for " + s.toString());
					// Find name
					if (!noName){
						startIndex = nextIndex + 3;
						nextIndex = entry.indexOf("<tr>",startIndex);						
						s.setName(entry.substring(startIndex,nextIndex));
					}
				}
				
			}
		}
		
	}
	
	

}
