package xml.saver;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import horiversumObjects.Universe;
import horiversumObjects.UserMap;
import misc.GlobalObjects;

public class XmlUserMapSaver implements Runnable{
	
	private String filename;
	private UserMap umap;
	
	public XmlUserMapSaver(String filename, UserMap umap){
		this.filename = filename;
		this.umap = umap;
	}

	@Override
	public void run() {		
		try {
			while(Universe.isUserMapFileLocked()){
				// wait
				Thread.sleep(500);
			}
			Universe.setUserMapFileLocked(true);
			JAXBContext jc = JAXBContext.newInstance(UserMap.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);			
			marshaller.marshal(umap, new File(filename));
			Universe.setUserMapFileLocked(false);
			GlobalObjects.logger.addLog("Users successfully saved to " + filename);
		} catch (JAXBException | InterruptedException e) {
			Universe.setUserMapFileLocked(true);
			GlobalObjects.errorLogger.logError(e);		
		}	
	}

}
