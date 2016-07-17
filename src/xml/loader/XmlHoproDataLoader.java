package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import horiversumObjects.HoproData;
import horiversumObjects.Universe;
import misc.GlobalObjects;

public class XmlHoproDataLoader implements Runnable{
	
	private String filename;
	
	private HoproData pmap = null;
	
	public XmlHoproDataLoader(String filename){
		this.filename = filename;
	}
	

	@Override
	public void run() {
		try{
			while(Universe.isHoproDataFileLocked()){
				// wait
				Thread.sleep(500);
			}
			Universe.setHoproDataFileLocked(true);
			JAXBContext jc = JAXBContext.newInstance(HoproData.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			this.pmap = (HoproData) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully loaded data from " + filename);
			Universe.setHoproDataFileLocked(false);
			Universe.hoproDataLoadingCallback(pmap);			
		}catch(Exception e){
			Universe.setHoproDataFileLocked(false);
			Universe.hoproDataLoadingCallback(new HoproData());
			GlobalObjects.errorLogger.logError(e);						
		}		
	}

}
