package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import misc.GlobalObjects;
import misc.Settings;

public class XmlSettingsLoader {
	
	public static Settings loadSettings(String filename){
		Settings settings = null;
		try{
			JAXBContext jc = JAXBContext.newInstance(Settings.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			settings = (Settings) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully parsed Settings.");
		}catch(Exception e){
			GlobalObjects.errorLogger.logError(e);			
			settings = new Settings();
		}
		return settings;
	}

}
