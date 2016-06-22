package horiversumObjects;

import misc.GlobalObjects;
import misc.Settings;
import xml.loader.XmlPlanetMapLoader;
import xml.loader.XmlUserMapLoader;
import xml.saver.XmlPlanetMapSaver;
import xml.saver.XmlUserMapSaver;

public class Universe {
	
	private static UserMap umap = new UserMap();
	private static PlanetMap pmap = new PlanetMap();
	
	public static PlanetMap getPlanetMap(){
		return Universe.pmap;
	}
	
	public static UserMap getUserMap(){
		return Universe.umap;
	}
	
	public static void savePlanetMap(String filename){
		XmlPlanetMapSaver.save(pmap, filename);
		GlobalObjects.logger.addLog("Planets saved to " + filename);
	}
	
	public static void saveUserMap(String filename){		
		XmlUserMapSaver.save(umap, filename);
		GlobalObjects.logger.addLog("Users saved to " + filename);
	}
	
	public static void loadPlanetMap(String filename){
		// This overwrite the earlier pmap. This may potentially lead to inconsistencies!
		Universe.pmap = XmlPlanetMapLoader.load(filename);
	}
	
	public static void loadUserMap(String filename){
		// This overwrite the earlier umap. This may potentially lead to inconsistencies!
		Universe.umap = XmlUserMapLoader.load(filename);
	}	
	
	public static void saveAll(){
		Universe.savePlanetMap(Settings.getPlanetsFile());
		Universe.saveUserMap(Settings.getUsersFile());
	}
	
	public static void loadAll(){
		Universe.loadPlanetMap(Settings.getPlanetsFile());
		Universe.loadUserMap(Settings.getUsersFile());
	}

}
