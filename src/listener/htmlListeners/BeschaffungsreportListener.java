package listener.htmlListeners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.Planet;
import horiversumObjects.Ressources;
import listener.HtmlReader;
import misc.GlobalObjects;

public class BeschaffungsreportListener extends HtmlListener{

	@Override
	public void interpretHtml(Document htmlSource) {
		Elements titleElements = htmlSource.select("title");

		if (titleElements.first()!=null){
			String title = titleElements.first().text();
			// FILTER
			if (title.equalsIgnoreCase("Horizon - Nachrichten")){
				Elements elements = htmlSource.select("div.MessageContainerBodyBox a.MessageReportBattle");
				if (!elements.isEmpty()){
					if(elements.first().text().equalsIgnoreCase("Beschaffungsreport")){
						elements = htmlSource.select("div.MessageContainerBodyBox td.MessageTableCellLeft");
						SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyyHH:mm:ss");
						try {
							Ressources r = new Ressources();
							Calendar c = Calendar.getInstance();
							try{
								c.setTime(format.parse(elements.first().text().replace("\\n","")));
							} catch (Exception e){
								format = new SimpleDateFormat("dd.MM.yyyyHH:mm:ss");
								c.setTime(format.parse(elements.first().text()));
							}
							r.setUpdate(c);
							elements = htmlSource.select("div.MessageContainerBodyBox table.MessageTable td.MessageTableCellLeft");
							if(elements.size()>1){								
								Element e = elements.get(1);
								String planiInformation = e.select("a").first().text();
								String id = planiInformation.substring(planiInformation.indexOf("(")+1, planiInformation.indexOf(")")).trim();
								Planet p = Planet.getInstance(id);
								elements = e.select("p");
								if(elements.size()>1){
									e = elements.get(1);
									String beute = e.text();
									int startIndex = beute.indexOf(":")+2;
									int nextIndex = startIndex;
									if(beute.contains("Eisenerz")){		
										nextIndex = beute.indexOf("Eisenerz")-1;
										r.setIron(Integer.parseInt(HtmlReader.cleanUpForParsingNumber(beute.substring(startIndex, nextIndex))));
										startIndex = nextIndex+10;
									}
									if(beute.contains("Minerale")){		
										nextIndex = beute.indexOf("Minerale")-1;
										r.setMinerals(Integer.parseInt(HtmlReader.cleanUpForParsingNumber(beute.substring(startIndex, nextIndex))));
										startIndex = nextIndex+10;
									}
									if(beute.contains("Treibstoff")){		
										nextIndex = beute.indexOf("Treibstoff")-1;
										r.setFuel(Integer.parseInt(HtmlReader.cleanUpForParsingNumber(beute.substring(startIndex, nextIndex))));
										startIndex = nextIndex+12;
									}
									p.addStolenRessources(r);
									GlobalObjects.logger.addLog("Identified Beschaffungsreport (" + p.uniqueId + "): " + c.getTime());
									GlobalObjects.logger.addSubLog("Eisen: " + r.getIron(), 1);
									GlobalObjects.logger.addSubLog("Minerale: " + r.getMinerals(), 1);
									GlobalObjects.logger.addSubLog("Treibstoff: " + r.getFuel(), 1);
								}			
							}					
						} catch (ParseException e) {
							GlobalObjects.errorLogger.logError(e);
						}
						
						
					}	
				}
//				
			}
		}
		
	}

}
