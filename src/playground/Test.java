package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Ressources;
import horiversumObjects.Planet;
import horiversumObjects.Universe;
import horiversumObjects.User;
import listener.gui.LogScreen;
import misc.GlobalObjects;
import xml.saver.XmlPlanetMapSaver;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {		
		
//		Unmarshaller unmarshaller = jc.createUnmarshaller();
//		PlanetCollection pc = (PlanetCollection) unmarshaller.unmarshal(new FileInputStream(new File("./output/test.xml")));
//		Planet p3 = pc.getPlanet(2);
		
		Universe.loadAll();
		
		Planet p = Planet.getInstance(1,805,1);
		p.setName("test");
		Ressources o = new Ressources();
		o.setIron(100);
//		o.setMinerals(200);
		p.setOrbitalRessources(o);
		p.setType("ice");
		Planet p2 = Planet.getInstance(1,805,10);
		p2.setName("test2");
		p2.setOwner(User.getInstance("Dummer typ von neb..."));
		p2.setType("vulcano");
		Planet p3 = Planet.getInstance(1,805,2);
		p3.setName("test3");
		p3.setOwner(User.getInstance("Dumm"));
		p3.setType("vulcano");
		
		User.getInstance("Dummer typ von nebenan");
		
//		Universe.getPlanetMap().removePlanet("1:805:10");
		
		Universe.saveAll();
		
//		Ressources o = new Ressources();
//		o.setIron(1000);
//		Universe.getPlanetMap().getPlanet(1, 810, 10).setOrbitalRessources(o);;
		
//		Planet p = Planet.getInstance(1, 1, 1);
//		Universe.getPlanetMap().getPlanet(2,1,1);
//		
//		GlobalObjects.logger = new LogScreen();
//		GlobalObjects.logger.addLog(Universe.getPlanetMap().toString());
		
		
//		PlanetMap ps = new PlanetMap();
//		ps.addPlanet(p3);
		
		
				
	}

}
