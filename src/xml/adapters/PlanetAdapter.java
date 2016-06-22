package xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import horiversumObjects.Planet;
import xml.XmlPlanet;

public class PlanetAdapter extends XmlAdapter<XmlPlanet,Planet>{

	@Override
	public XmlPlanet marshal(Planet p) throws Exception {
		XmlPlanet xml = new XmlPlanet();
		xml.transferDataToXml(p);
		return xml;
	}
	

	@Override
	public Planet unmarshal(XmlPlanet xml) throws Exception {
		Planet p = Planet.transferFromXml(xml);
		return p;
	}
	

}
