package playground;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import horiversumObjects.Universe;
import horiversumObjects.User;
import listener.gui.MainFrame;
import misc.GlobalObjects;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException, InterruptedException {		
		GlobalObjects.logger = new MainFrame();
		Universe.load();
//		Thread.sleep(2000);
		System.out.println(Universe.getHoproDataSet().getAllPlanetsOfUser(User.getInstance("Flovie")));
//		Universe.saveAll();	
	}

}
