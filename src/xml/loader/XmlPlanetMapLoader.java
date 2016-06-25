package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import horiversumObjects.PlanetMap;
import horiversumObjects.Universe;
import misc.GlobalObjects;

public class XmlPlanetMapLoader implements Runnable{
	
	private String filename;
	
	private PlanetMap pmap = null;
	
	public XmlPlanetMapLoader(String filename){
		this.filename = filename;
	}
	

	@Override
	public void run() {
		try{
			JAXBContext jc = JAXBContext.newInstance(PlanetMap.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			this.pmap = (PlanetMap) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully loaded planets from " + filename);
			Universe.planetMapCallback(pmap);
		}catch(Exception e){
			GlobalObjects.errorLogger.logError(e);			
			this.pmap = new PlanetMap();
		}		
	}

}
