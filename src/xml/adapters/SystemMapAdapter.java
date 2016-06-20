package xml.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import horiversumObjects.GalaxySystem;

public class SystemMapAdapter extends XmlAdapter<SystemMapAdapter.AdaptedSystemList,Map<String,GalaxySystem>>{
	
	
	public static class AdaptedSystemList{
		public List<GalaxySystem> system = new ArrayList<GalaxySystem>();
	}

	@Override
	public AdaptedSystemList marshal(Map<String, GalaxySystem> arg0) throws Exception {
		AdaptedSystemList result = new AdaptedSystemList();
		result.system.addAll(arg0.values());
		Collections.sort(result.system);
		return result;
	}

	@Override
	public Map<String, GalaxySystem> unmarshal(AdaptedSystemList v) throws Exception {
		Map<String,GalaxySystem> systemsMap = new HashMap<String,GalaxySystem>();
		if(v!=null){
			for(GalaxySystem s: v.system){
				systemsMap.put(s.getGalaxy() + ":" + s.getSystem(), s);
			}
		}				
		return systemsMap;
	}

}
