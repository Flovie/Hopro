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
	private static Settings settings;
	
	public static String getPlanetsFile(){
		if(Settings.settings==null){
			Settings.loadSettings();
		};
		return Settings.settings.planetsXmlFile;
	}
	
	public static String getUsersFile(){
		if(Settings.settings==null){
			Settings.loadSettings();
		};
		return Settings.settings.usersXmlFile;
	}
	
	public static int getSaveIntervall(){
		if(Settings.settings==null){
			Settings.loadSettings();
		};
		return Settings.settings.saveIntervall;
	}
	
	public static boolean isDebuggingMode(){
		if(Settings.settings==null){
			Settings.loadSettings();
		};
		return Settings.settings.debuggingMode;
	}
	
	public static boolean isAlwaysOnTop(){
		if(Settings.settings==null){
			Settings.loadSettings();
		};
		return Settings.settings.alwaysOnTop;
	}
	
	private static void loadSettings(){
		Settings.settings = XmlSettingsLoader.loadSettings("./settings.xml");
	}

}
