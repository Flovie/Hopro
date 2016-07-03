package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Universe;
import horiversumObjects.User;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException, InterruptedException {		
		
		Universe.loadAll();
		Thread.sleep(2000);
		System.out.println(Universe.getPlanetMap().getAllPlanetsOfUser(User.getInstance("Flovie")));
//		Universe.saveAll();	
	}

}
