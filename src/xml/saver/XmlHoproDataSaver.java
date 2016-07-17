package xml.saver;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import horiversumObjects.HoproData;
import horiversumObjects.Universe;
import misc.GlobalObjects;

public class XmlHoproDataSaver implements Runnable{	
	
	private String filename;
	private HoproData pmap;
	
	public XmlHoproDataSaver(String filename, HoproData pmap){
		this.filename = filename;
		this.pmap = pmap;
	}

	@Override
	public void run() {		
		try {
			while(Universe.isHoproDataFileLocked()){
				// wait
				Thread.sleep(500);
			}
			Universe.setHoproDataFileLocked(true);
			JAXBContext jc = JAXBContext.newInstance(HoproData.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);			
			marshaller.marshal(this.pmap, new File(filename));
			GlobalObjects.logger.addLog("Data successfully saved to " + filename);
			Universe.setHoproDataFileLocked(false);
		} catch (JAXBException | InterruptedException e) {
			Universe.setHoproDataFileLocked(false);
			GlobalObjects.errorLogger.logError(e);		
		}				
	}
	


}
