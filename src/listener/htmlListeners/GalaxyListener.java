package listener.htmlListeners;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horiversumObjects.Coordinate;
import horiversumObjects.GalaxySystem;
import horiversumObjects.Planet;
import horiversumObjects.Ressources;
import horiversumObjects.Universe;
import horiversumObjects.User;
import listener.HtmlReader;
import misc.GlobalObjects;


public class GalaxyListener extends HtmlListener {

	@Override
	public void interpretHtml(Document htmlSource) {

		Elements titleElements = htmlSource.select("title");

		if (titleElements.first()!=null){
			String title = titleElements.first().text();
			// FILTER
			if (title.equalsIgnoreCase("Horizon - Galaxie")){
				// localize galaxy table			
				Elements tmp2 = htmlSource.select("div.GalaxyContainer > div.GalaxyContainerHead > div.GalaxyContainerHeadText");
				if(tmp2!=null){
					String systemHeader = tmp2.first().text();
					// extract informations from galaxy header
					String systemId = null;
					String systemName = null;
					boolean systemIdentified = true;
					try{
						if(systemHeader.contains("entdeckt")){
							systemId = systemHeader.substring(systemHeader.indexOf("(System")+8,systemHeader.indexOf("entdeckt")-1).trim();
						}else{
							systemId = systemHeader.substring(systemHeader.indexOf("(System")+8,systemHeader.indexOf(")")).trim();
						}						
						systemName = systemHeader.substring(0, systemHeader.indexOf("(System")).trim();
					}catch(StringIndexOutOfBoundsException e){
						systemIdentified = false;
					}
					if(systemIdentified){
						GlobalObjects.logger.addLog("Identified system " + systemId + " (" + systemName + ")");

						// Location of system
						int galaxy = Integer.parseInt(systemId.substring(0, systemId.indexOf(":")));
						int system = Integer.parseInt(systemId.substring(systemId.indexOf(":")+1));
						
						GalaxySystem galaxySystem = GalaxySystem.getInstance(galaxy, system);
						galaxySystem.setName(systemName);

						//get orbit list
						Elements eList = htmlSource.select("table.OrbitTable > tbody > tr:not(.OrbitTableHead)");
						for(Element e:eList){							
							// Orbital position
							int orbit = 0;
							try{
								orbit = Integer.parseInt(e.select("td:eq(0)").text().trim());
							} catch (NumberFormatException exeption){
								// Something went wrong -> mabye Dyson sphere (not yet implemented)
								continue;
							}
							Coordinate c = new Coordinate(galaxy,system,orbit);

							// Type
							tmp2 = e.select("td:eq(1) img[border]");
							Planet p = null;				
							if (tmp2.first()!=null){
								p = Planet.getInstance(c);
								p.setType(tmp2.get(0).attr("title"));
								Ressources o = null;
								if (tmp2.size()>1){
									o = new Ressources();
									String orbitalRessources = tmp2.get(1).attr("title");									
									Integer iron = null;
									Integer minerals = null;
									Integer fuel = null;
									if (orbitalRessources.contains("Eisenerz")){
										iron = Integer.parseInt(HtmlReader.cleanUpForParsingNumber(orbitalRessources.substring(orbitalRessources.indexOf("Eisenerz:")+10,orbitalRessources.indexOf(" ", orbitalRessources.indexOf("Eisenerz:")+10))));
										o.setIron(iron);
									}
									if (orbitalRessources.contains("Minerale")){
										minerals = Integer.parseInt(HtmlReader.cleanUpForParsingNumber(orbitalRessources.substring(orbitalRessources.indexOf("Minerale:")+10,orbitalRessources.indexOf(" ", orbitalRessources.indexOf("Minerale:")+10))));
										o.setMinerals(minerals);
									}
									if (orbitalRessources.contains("Treibstoff")){
										fuel = Integer.parseInt(HtmlReader.cleanUpForParsingNumber(orbitalRessources.substring(orbitalRessources.indexOf("Treibstoff:")+12,orbitalRessources.indexOf(" ", orbitalRessources.indexOf("Treibstoff:")+12))));
										o.setFuel(fuel);
									}																																									
								}
								p.setOrbitalRessources(o);
							}					

							String alliance = null;
							boolean activityStatus = false;


							if (p!=null){
								// Filter the Turniers
								if(!p.getType().contains("Turnier")){
									// Planet name
									Element spalte2 = e.select("td:eq(2)").first();
									if(spalte2.text().contains("...") && spalte2.select("a")!=null){									
										p.setName(spalte2.select("a").attr("title").substring(5));
									}else{
										p.setName(spalte2.text());
									}								

									// Owner
									Element tmp = e.select("td:eq(3):not([colspan]").first();
									if(tmp!=null){		
										User u = User.getInstance(tmp.text());
										p.setOwner(u);

										// Alliance
										tmp = e.select("td:eq(4) > table > tbody > tr > td > a").first();
										if (tmp!=null){
											alliance = tmp.text();
										}else{
											alliance = "";
										}
										u.setAlliance(alliance);
										

										// acitivity
										String activityString = e.select("td:eq(5)").first().text().trim();
										if ((activityString.equals("active")) || (activityString.equals("online"))){
											activityStatus = true;
										}
										if (activityString.equals("inactive")){
											activityStatus = false;
										}
										u.setActivityStatus(activityStatus);
									}else{
										p.removeOwner();
									}
									GlobalObjects.logger.addSubLog("Found planet " + p, 1);
								}else{
									GlobalObjects.logger.addSubLog("Ignored Tunier at " + c, 1);
								}								
							}else{
								if (Universe.getPlanetMap().planetExists(c.toString())){
									Universe.getPlanetMap().removePlanet(c.toString());
									GlobalObjects.logger.addSubLog("Removed Planet at " + c.toString(), 1);
								}
							}
						}

					}
				}
			}

		}

	}

}
