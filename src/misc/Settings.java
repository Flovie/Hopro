package misc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import xml.loader.XmlSettingsLoader;

@XmlRootElement(name="settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {
	
	@XmlElement(name="planetsFile")
	private String planetsXmlFile = "./planets.xml";
	@XmlElement(name="usersFile")
	private String usersXmlFile = "./users.xml";
	@XmlElement
	private int saveIntervall = 300; // seconds
	@XmlElement
	private boolean debuggingMode = false;
	@XmlElement
	private boolean alwaysOnTop = false;
	
	@XmlTransient
	private static Settings settings = null;
	
	@XmlTransient
	public static boolean loaded = false;
	
	public static String getPlanetsFile(){		
		if(!Settings.loaded){
			Settings.loadSettings();
			Settings.waitForLoading();
		};
		return Settings.settings.planetsXmlFile;
	}
	
	public static String getUsersFile(){
		if(!Settings.loaded){
			Settings.loadSettings();
			Settings.waitForLoading();
		};
		return Settings.settings.usersXmlFile;
	}
	
	public static int getSaveIntervall(){
		if(!Settings.loaded){
			Settings.loadSettings();
			Settings.waitForLoading();
		};
		while(Settings.loaded==false){}
		return Settings.settings.saveIntervall;
	}
	
	public static boolean isDebuggingMode(){
		if(!Settings.loaded){
			Settings.loadSettings();
			Settings.waitForLoading();
		};
		while(Settings.loaded==false){}
		return Settings.settings.debuggingMode;
	}
	
	public static boolean isAlwaysOnTop(){
		if(!Settings.loaded){
			Settings.loadSettings();
			Settings.waitForLoading();
		};
		while(Settings.loaded==false){}
		return Settings.settings.alwaysOnTop;
	}
	
	public static void waitForLoading(){
		while(Settings.loaded==false){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				GlobalObjects.errorLogger.logError(e);
			}
		}
	}
	
	private static void loadSettings(){	
		Thread settingsLoaderThread = new Thread(new XmlSettingsLoader("./settings.xml"),"settingsLoader");
		settingsLoaderThread.start();
	}
	
	public static void loadingCallback(Settings settings){			
		Settings.settings = settings;
		Settings.loaded = true;
	}
	
	public static boolean isLoaded(){
		return Settings.loaded;
	}

}
