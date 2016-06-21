package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import misc.Settings;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {		
		
		System.out.println(Settings.getUsersFile());
		System.out.println(Settings.getPlanetsFile());
		System.out.println(Settings.getSaveIntervall());
		System.out.println(Settings.isDebuggingMode());
		System.out.println(Settings.isAlwaysOnTop());
		
				
	}

}
