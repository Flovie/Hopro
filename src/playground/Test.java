package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Universe;
import misc.Settings;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {		
		
		Universe.loadAll();
		Universe.saveAll();
		
				
	}

}
