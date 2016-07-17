package horiversumObjects;

import misc.Settings;
import xml.loader.XmlHoproDataLoader;
import xml.saver.XmlHoproDataSaver;

public class Universe {
	
	private static HoproData hoproDataSet = null;
	
	private static boolean hoproFileIsAccessed = false;	
	
	public static HoproData getHoproDataSet(){
		while (hoproDataSet==null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return Universe.hoproDataSet;
	}
	
	
	public static void saveHoproDataSet(String filename){
		Universe.hoproDataSet.updateLastSaved();
		Thread planetSaverThread = new Thread(new XmlHoproDataSaver(filename, Universe.hoproDataSet),filename);		
		planetSaverThread.start();
	}
	
	
	public static void loadHoproDataSet(String filename){
		Thread planetLoaderThread = new Thread(new XmlHoproDataLoader(filename),filename);
		planetLoaderThread.start();
	}
	
	public static void save(){
		Universe.saveHoproDataSet(Settings.getHoproDataFile());
	}
	
	public static void load(){
		Universe.loadHoproDataSet(Settings.getHoproDataFile());
	}

	public static void hoproDataLoadingCallback(HoproData pmap2) {
		// This overwrite the earlier hoproDataSet. This may potentially lead to inconsistencies!
		Universe.hoproDataSet = pmap2;		
	}
	
	
	public static void setHoproDataFileLocked(boolean hoproFileIsAccessed){
		Universe.hoproFileIsAccessed = hoproFileIsAccessed;
	}
	
	public static boolean isHoproDataFileLocked(){
		return Universe.hoproFileIsAccessed;
	}
	

}
