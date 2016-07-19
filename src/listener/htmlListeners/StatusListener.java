package listener.htmlListeners;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.Coordinate;
import misc.GlobalObjects;

public class StatusListener extends HtmlListener{

	@Override
	public void interpretHtml(Document htmlSource) {
		// get current planet name
		Elements e = htmlSource.select(".PlanetSelectBox > option[selected]");
		if(!e.isEmpty()){
			String planetName = e.text();
			Elements planetTicks = htmlSource.select(".TickPlanetEntry");
			for(Element el: planetTicks){
				String planetInfos = el.attr("onmouseover");
				if(planetInfos.contains(planetName)){
					int beginIndex = planetInfos.indexOf("CAPTION")+10;
					int endIndex = planetInfos.indexOf(planetName)-3;
					String coordinateString = planetInfos.substring(beginIndex, endIndex);
					Coordinate c = new Coordinate(coordinateString);
					if(!GlobalObjects.currentPosition.equals(c)){
						GlobalObjects.currentPosition = c;
						GlobalObjects.logger.addLog("Changed current Position to " + planetName + " (" + c + ")");
					}
				}
			}
			// PREPARATION FOR MESSAGE NOTIFICATION
//			Elements temp = htmlSource.select("td.Monitor img");
//			System.out.println(temp);
		}
	}
}
