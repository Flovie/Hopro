package horiversumObjects;

import misc.Settings;
import xml.loader.XmlPlanetMapLoader;
import xml.loader.XmlUserMapLoader;
import xml.saver.XmlPlanetMapSaver;
import xml.saver.XmlUserMapSaver;

public class Universe {
	
	private static UserMap umap = new UserMap();
	private static PlanetMap pmap = new PlanetMap();
	
	private static boolean userMapFileLocked = false;
	private static boolean planetMapFileLocked = false;
	
	public static PlanetMap getPlanetMap(){
		return Universe.pmap;
	}
	
	public static UserMap getUserMap(){
		return Universe.umap;
	}
	
	public static void savePlanetMap(String filename){
		Universe.pmap.updateLastSaved();
		Thread planetSaverThread = new Thread(new XmlPlanetMapSaver(filename, Universe.pmap),filename);		
		planetSaverThread.start();
	}
	
	public static void saveUserMap(String filename){	
		Universe.umap.updateLastSaved();
		Thread userSaverThread = new Thread(new XmlUserMapSaver(filename, Universe.umap),filename);
		userSaverThread.start();		
	}
	
	public static void loadPlanetMap(String filename){
		Thread planetLoaderThread = new Thread(new XmlPlanetMapLoader(filename),filename);
		planetLoaderThread.start();
	}
	
	public static void loadUserMap(String filename){
		Thread userLoaderThread = new Thread(new XmlUserMapLoader(filename),filename);
		userLoaderThread.start();
	}	
	
	public static void saveAll(){
		Universe.savePlanetMap(Settings.getPlanetsFile());
		Universe.saveUserMap(Settings.getUsersFile());
	}
	
	public static void loadAll(){
		Universe.loadPlanetMap(Settings.getPlanetsFile());
		Universe.loadUserMap(Settings.getUsersFile());
	}

	public static void planetMapCallback(PlanetMap pmap2) {
		// This overwrite the earlier pmap. This may potentially lead to inconsistencies!
		Universe.pmap = pmap2;		
	}
	
	public static void userMapCallback(UserMap umap2) {
		// This overwrite the earlier umap. This may potentially lead to inconsistencies!
		Universe.umap = umap2;		
	}
	
	public static void setUserMapFileLocked(boolean userMapFileIsAccessed){
		Universe.userMapFileLocked = userMapFileIsAccessed;
	}
	
	public static void setPlanetMapFileLocked(boolean planetMapFileIsAccessed){
		Universe.planetMapFileLocked = planetMapFileIsAccessed;
	}
	
	public static boolean isUserMapFileLocked(){
		return Universe.userMapFileLocked;
	}
	
	public static boolean isPlanetMapFileLocked(){
		return Universe.planetMapFileLocked;
	}

}
