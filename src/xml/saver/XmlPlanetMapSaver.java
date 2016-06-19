package xml.saver;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import horiversumObjects.PlanetMap;
import misc.GlobalObjects;

public class XmlPlanetMapSaver {
	
	public static void save(PlanetMap pmap, String filename){		 
		try {
			JAXBContext jc = JAXBContext.newInstance(PlanetMap.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			pmap.updateLastSaved();
			marshaller.marshal(pmap, new File(filename));
		} catch (JAXBException e) {
			GlobalObjects.errorLogger.logError(e);		
		}		
	}
	


}
