package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import misc.GlobalObjects;
import misc.Settings;

public class XmlSettingsLoader implements Runnable{
	
	private String filename;
	public Settings settings;
	
	public XmlSettingsLoader(String filename){
		this.filename = filename;
	}
	
	@Override
	public void run() {
		try{
			JAXBContext jc = JAXBContext.newInstance(Settings.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			this.settings = (Settings) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
//			GlobalObjects.logger.addLog("Successfully parsed Settings.");
			Settings.loadingCallback(this.settings);
		}catch(Exception e){
			Settings.loadingCallback(new Settings());
			GlobalObjects.errorLogger.logError(e);			
		}		
	}

}
