package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Universe;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {		
		
		Universe.loadAll();
		Universe.saveAll();
		Universe.saveAll();
		Universe.saveAll();
		Universe.loadAll();		
	}

}
