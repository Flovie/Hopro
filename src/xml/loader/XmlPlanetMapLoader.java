package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import horiversumObjects.PlanetMap;
import misc.GlobalObjects;

public class XmlPlanetMapLoader {
	
	public static PlanetMap load(String filename){
		PlanetMap pmap = null;
		try{
			JAXBContext jc = JAXBContext.newInstance(PlanetMap.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			pmap = (PlanetMap) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully loaded planets from " + filename);
		}catch(Exception e){
			GlobalObjects.errorLogger.logError(e);			
			pmap = new PlanetMap();
		}
		return pmap;	
	}

}
