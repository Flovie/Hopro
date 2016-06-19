package xml.loader;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import horiversumObjects.UserMap;
import misc.GlobalObjects;

public class XmlUserMapLoader {
	
	public static UserMap load(String filename){
		UserMap umap = null;
		try{
			JAXBContext jc = JAXBContext.newInstance(UserMap.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			umap = (UserMap) unmarshaller.unmarshal(new FileInputStream(new File(filename)));
			GlobalObjects.logger.addLog("Successfully loaded users from " + filename);
		}catch(Exception e){
			GlobalObjects.errorLogger.logError(e);			
			umap = new UserMap();
		}
		return umap;	
	}

}
