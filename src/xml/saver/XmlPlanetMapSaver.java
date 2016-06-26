package xml.saver;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import horiversumObjects.PlanetMap;
import horiversumObjects.Universe;
import misc.GlobalObjects;

public class XmlPlanetMapSaver implements Runnable{	
	
	private String filename;
	private PlanetMap pmap;
	
	public XmlPlanetMapSaver(String filename, PlanetMap pmap){
		this.filename = filename;
		this.pmap = pmap;
	}

	@Override
	public void run() {		
		try {
			while(Universe.isPlanetMapFileLocked()){
				// wait
				Thread.sleep(500);
			}
			Universe.setPlanetMapFileLocked(true);
			JAXBContext jc = JAXBContext.newInstance(PlanetMap.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);			
			marshaller.marshal(this.pmap, new File(filename));
			GlobalObjects.logger.addLog("Planets successfully saved to " + filename);
			Universe.setPlanetMapFileLocked(false);
		} catch (JAXBException | InterruptedException e) {
			Universe.setPlanetMapFileLocked(false);
			GlobalObjects.errorLogger.logError(e);		
		}				
	}
	


}
