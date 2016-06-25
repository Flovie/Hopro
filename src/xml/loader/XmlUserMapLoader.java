package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import horiversumObjects.Universe;
import horiversumObjects.UserMap;
import misc.GlobalObjects;

public class XmlUserMapLoader implements Runnable{
	
	private String filename;
	
	private UserMap umap = null;
	
	public XmlUserMapLoader(String filename){
		this.filename = filename;
	}
	
	
	public static UserMap load(String filename){
		UserMap umap = null;
		
		return umap;	
	}


	@Override
	public void run() {
		try{
			JAXBContext jc = JAXBContext.newInstance(UserMap.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			this.umap = (UserMap) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully loaded users from " + filename);
			Universe.userMapCallback(this.umap);
		}catch(Exception e){
			GlobalObjects.errorLogger.logError(e);			
			this.umap = new UserMap();
		}		
	}

}
