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
					GlobalObjects.logger.addLog("Identified Highscore List");
					int counter = 0;
					for(int j=1; j<elements.size();j++){
						Element e = elements.get(j);
						Elements entries = e.select("td");						
						if(entries.size()==9){
							User u = User.getInstance(entries.get(1).text());
							if(!entries.get(3).text().trim().equals("\u00a0")){
								u.setAlliance(entries.get(3).text().trim());
							}
							u.setActivityRatio(Integer.parseInt(entries.get(6).text().trim()));
							Score s = new Score();
							s.setScore(Long.parseLong(HtmlReader.cleanUpForParsingNumber(entries.get(7).text())));
							s.setRelScore(Float.parseFloat(HtmlReader.cleanUpForParsingNumber(entries.get(8).text())));;
							u.setScore(s);
							counter++;
						}						
					}
					GlobalObjects.logger.addSubLog("Added " + counter + " scores", 1);		
				}
				
			}
		}
		
	}

}
