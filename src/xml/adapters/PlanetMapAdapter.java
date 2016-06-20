package xml.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import horiversumObjects.Planet;

public class PlanetMapAdapter extends XmlAdapter<PlanetMapAdapter.AdaptedPlanetsList,Map<String,Planet>>{
	
	
	public static class AdaptedPlanetsList{
		@XmlJavaTypeAdapter(PlanetAdapter.class)
		public List<Planet> planet = new ArrayList<Planet>();
	}

	@Override
	public AdaptedPlanetsList marshal(Map<String, Planet> arg0) throws Exception {
		AdaptedPlanetsList result = new AdaptedPlanetsList();
		result.planet.addAll(arg0.values());
		Collections.sort(result.planet);
		return result;
	}

	@Override
	public Map<String, Planet> unmarshal(AdaptedPlanetsList v) throws Exception {
		Map<String,Planet> planetsMap = new HashMap<String,Planet>();
		if(v!=null){
			for(Planet p: v.planet){
				planetsMap.put(p.uniqueId, p);
			}
		}
					
		return planetsMap;
	}

}
