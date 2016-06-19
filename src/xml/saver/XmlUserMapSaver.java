package xml.saver;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import horiversumObjects.UserMap;
import misc.GlobalObjects;

public class XmlUserMapSaver {
	
	public static void save(UserMap umap, String filename){		 
		try {
			JAXBContext jc = JAXBContext.newInstance(UserMap.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			umap.updateLastSaved();
			marshaller.marshal(umap, new File(filename));
		} catch (JAXBException e) {
			GlobalObjects.errorLogger.logError(e);		
		}		
	}

}
