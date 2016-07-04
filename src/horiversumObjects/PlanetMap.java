package horiversumObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.adapters.PlanetMapAdapter;
import xml.adapters.SystemMapAdapter;

@XmlRootElement(name="planetMap")
public class PlanetMap {
	
	@XmlAttribute(name="created", required=false)
	Calendar lastSaved;
	
	@XmlElement(name="planets")
	@XmlJavaTypeAdapter(PlanetMapAdapter.class)
	private Map<String,Planet> ps = new HashMap<String,Planet>();	
	
	@XmlElement(name="galaxies")
	@XmlJavaTypeAdapter(SystemMapAdapter.class)
	private Map<String,GalaxySystem> galaxies = new HashMap<String,GalaxySystem>();
	
	public void addPlanet(Planet p){
		ps.put(p.uniqueId, p);
		if (!(galaxies.containsKey(p.getPosition().getGalaxy() + ":" + p.getPosition().getSystem()))){
			GalaxySystem gs = GalaxySystem.getInstance(p.getPosition().getGalaxy(), p.getPosition().getSystem());
			galaxies.put(p.getPosition().getGalaxy() + ":" + p.getPosition().getSystem(), gs);
		}
			
	}
	
	public boolean planetExists(String id){
		return ps.containsKey(id);
	}
	
	public Planet getPlanet(int galaxy, int system, int orbit){
		return getPlanet(new Coordinate(galaxy,system,orbit));
	}
	
	public Planet getPlanet(Coordinate c){
		return getPlanet(c.toString());
	}
	
	public Planet getPlanet(String id){
		if (planetExists(id)){
			return ps.get(id);			
		}else{
			return Planet.getInstance(id);
		}	
	}
	
	public void removePlanet(String id){
		this.ps.remove(id);
	}
	
	public void updateLastSaved(){
		this.lastSaved = Calendar.getInstance();
	}
	
	public String toString(){
		return this.ps.values().toString();
	}
	
	public boolean systemExists(int galaxy, int system){
		return galaxies.containsKey(galaxy + ":" + system);
	}
	
	public GalaxySystem getSystem(int galaxy, int system){
		if (systemExists(galaxy, system)){
			return this.galaxies.get(galaxy + ":" + system);
		}else{
			return null;
		}
	}
	
	public void addSystem(GalaxySystem s){
		this.galaxies.put(s.getGalaxy() + ":" + s.getSystem(), s);
	}	
	
	public List<Planet> getAllPlanetsOfUser(User u){
		List<Planet> results = new ArrayList<Planet>();
		for(Planet p: this.getHabitedPlanets()){
			if(p.getOwner().equals(u)){
				results.add(p);
			}
		}
		return results;
	}
	
	public List<Planet> getAllPlanets(){
		List<Planet> planetList = new ArrayList<Planet>();
		planetList.addAll(this.ps.values());
		return planetList;
	}
	
	public List<Planet> getHabitedPlanets(){
		List<Planet> planetList = new ArrayList<Planet>();
		for(Planet p: this.ps.values()){
			if(p.getOwner()!=null){
				planetList.add(p);
			}
		}
		return planetList;
	}

}
