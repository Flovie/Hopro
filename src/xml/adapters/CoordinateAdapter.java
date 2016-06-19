package xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import horiversumObjects.Coordinate;

public class CoordinateAdapter extends XmlAdapter<String,Coordinate> {

	@Override
	public String marshal(Coordinate v) throws Exception {
		return v.toString();
	}

	@Override
	public Coordinate unmarshal(String v) throws Exception {
		return new Coordinate(v);
	}

}
