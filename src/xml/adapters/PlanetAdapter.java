package xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import horiversumObjects.Coordinate;
import horiversumObjects.Planet;
import horiversumObjects.Universe;
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
		Planet p = Universe.getPlanetMap().getPlanet(new Coordinate(xml.uniqueId));
		p.transferDataFromXml(xml);
		return p;
	}
	

}
