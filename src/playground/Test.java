package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Ressources;
import horiversumObjects.Planet;
import horiversumObjects.Universe;
import horiversumObjects.User;
import listener.gui.LogScreen;
import misc.GlobalObjects;
import misc.Settings;
import xml.saver.XmlPlanetMapSaver;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {		
		
		System.out.println(Settings.getUsersFile());
		System.out.println(Settings.getPlanetsFile());
		System.out.println(Settings.getSaveIntervall());
		System.out.println(Settings.isDebuggingMode());
		System.out.println(Settings.isAlwaysOnTop());
		
				
	}

}
