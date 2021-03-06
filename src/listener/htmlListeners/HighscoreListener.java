package listener.htmlListeners;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.Score;
import horiversumObjects.User;
import listener.HtmlReader;
import misc.GlobalObjects;

public class HighscoreListener extends HtmlListener{

	@Override
	public void interpretHtml(Document htmlSource) {
		Elements titleElements = htmlSource.select("title");

		if (titleElements.first()!=null){
			String title = titleElements.first().text();
			// FILTER
			if (title.equalsIgnoreCase("Little Horizon - The utility monitor")){
				Elements elements = htmlSource.select("div.StatWrapper table.Stat_Table tr");
				if(elements.size()>1){
					// Options
					Elements selectedElements = htmlSource.select("select option[selected]");
					if (selectedElements.size()==4){
						String modeOption = selectedElements.get(0).text();
						String playerOption = selectedElements.get(1).text();
						String typeOption = selectedElements.get(2).text();
//						String rangeOption = selectedElements.get(3).text();
						if(modeOption.equals("Normal")){
							GlobalObjects.logger.addLog("Identified Highscore List (" + playerOption + " - " + typeOption +")");					
							int counter = 0;
							for(int j=1; j<elements.size();j++){
								Element e = elements.get(j);
								Elements entries = e.select("td");	
								if(entries.size()==9){
									switch (playerOption){
									case "Spieler":
										switch (typeOption){
										case "Gesamter Highscore":		
											this.addPlaverTotalHighscore(entries);
											break;
										}
										
									case "alliance":
									}
									counter++;
								}						
							}
							GlobalObjects.logger.addSubLog("Added " + counter + " scores", 1);
						}
					}
					
							
				}
				
			}
		}
		
	}
	
	private void addPlaverTotalHighscore(Elements entries){
		User u = User.getInstance(entries.get(1).text());
		if(!entries.get(3).text().trim().equals("\u00a0")){
			u.setAlliance(entries.get(3).text().trim());
		}
		u.setStatus(entries.get(5).text().trim());
		u.setActivityRatio(Integer.parseInt(entries.get(6).text().trim()));
		Score s = u.getScore();
		s.setScore(Long.parseLong(HtmlReader.cleanUpForParsingNumber(entries.get(7).text())), Float.parseFloat(HtmlReader.cleanUpForParsingNumber(entries.get(8).text())));	
	}

}
