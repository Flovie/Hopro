package listener.htmlListeners;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.GalaxySystem;
import listener.HtmlReader;
import misc.GlobalObjects;

public class SystemMapListener extends HtmlListener {

	public void interpretHtml(Document htmlSource) {
		Elements titleElements = htmlSource.select("title");
		if (titleElements.first()!=null){
			String title = titleElements.first().text();
			// FILTER
			if (title.equalsIgnoreCase("Horizon - Galaxie")){				
				Elements tmp = htmlSource.select(".GalaxySystemLink, .GalaxySystemLinkColonized");
				if(!tmp.isEmpty()){
					GlobalObjects.logger.addLog("Identified Galaxy View");
				}
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
					GlobalObjects.logger.addSubLog("Found data for " + s.toString(),1);
					if (!noName){
						// Name
						startIndex = nextIndex + 3;
						nextIndex = entry.indexOf("<",startIndex);
						s.setName(entry.substring(startIndex,nextIndex));
						// Position
						startIndex = entry.indexOf("Position", nextIndex)+19;
						nextIndex = entry.indexOf("/",startIndex)-1;
						s.setPositionX(Float.parseFloat(HtmlReader.cleanUpForParsingNumber(entry.substring(startIndex,nextIndex))));
						startIndex = nextIndex + 3;
						nextIndex = entry.indexOf("<", startIndex);
						s.setPositionY(Float.parseFloat(HtmlReader.cleanUpForParsingNumber(entry.substring(startIndex, nextIndex))));
						// Planets
						if (entry.contains("Planeten")){
							startIndex = entry.indexOf("Planeten",nextIndex) +19;
							nextIndex = entry.indexOf("<",startIndex);
							s.setNumberOfPlanets(Integer.parseInt(HtmlReader.cleanUpForParsingNumber(entry.substring(startIndex, nextIndex))));						
						}						
						// Colonies
						if (entry.contains("Kolonien")){
							startIndex = entry.indexOf("Kolonien",nextIndex) + 19;
							nextIndex = entry.indexOf("<", startIndex);
							s.setNumberOfColonies(Integer.parseInt(HtmlReader.cleanUpForParsingNumber(entry.substring(startIndex, nextIndex))));							
						}
						if (entry.contains("Entdecker")){
							startIndex = entry.indexOf("Entdecker",nextIndex) + 20;
							nextIndex = entry.indexOf("<", startIndex);
							s.setDiscoverer(entry.substring(startIndex, nextIndex));													
						}
						if (entry.contains("Herrscher")){
							startIndex = entry.indexOf("Herrscher", nextIndex) + 20;
							nextIndex = entry.indexOf("<", startIndex);
							s.setOwner(entry.substring(startIndex, nextIndex));							
						}												
					}
				}				
			}
		}
		
	}
	
	

}
