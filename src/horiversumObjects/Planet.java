package horiversumObjects;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.XmlPlanet;
import xml.adapters.PlanetAdapter;

@XmlRootElement(name="planet")
@XmlJavaTypeAdapter(PlanetAdapter.class)
public class Planet implements Comparable<Planet> {
	
	// ID	
	public final String uniqueId;
	
	// position
	private final Coordinate position;	
	
	// optional parameters
	private String type = null;
	private String name = null;
	
	private Ressources orbitRessources = null;
	
	// Ownership
	private User owner = null;
	
	// stolen ressources
	private Ressources stolenRessources = null;
	
	// time
	private Calendar updated = null;	
	
	private Planet(String id){
		this.uniqueId = id;
		this.position = new Coordinate(id);		
		this.update();
	}
	
	public void update(){
		this.updated = Calendar.getInstance();
	}
	
	public void transferDataFromXml(XmlPlanet xml){
		this.name = xml.name;
		this.type = xml.type;
		if(xml.owner != null){
			this.owner = User.getInstance(xml.owner);
			this.owner.addPlanet(this);
		}		
		this.updated = xml.updated;
		this.orbitRessources = xml.orbitalRessources;
		this.stolenRessources = xml.stolenRessources;
	}
	
	// SETTERS
	
	public void setName(String name){
		this.name = name;
		this.update();
	}
	
	public void setType(String type){
		this.type = type;
		this.update();
	}
	
	public void setOwner(User owner){
		this.removeOwner();
		this.owner = owner;
		User.getInstance(owner.getName()).addPlanet(this);
		this.update();
	}
	
	public void removeOwner(){
		if (this.owner!=null){
			this.owner.removePlanet(this);
			this.owner = null;
			this.update();
		}		
	}
	
	public void setOrbitalRessources(Ressources o){
		this.orbitRessources = o;
		this.update();
	}
	
	public void setUpdated(Calendar updated){
		this.updated = updated;
	}
	
	public void addStolenRessources(Ressources r){
		if (this.stolenRessources==null){
			this.stolenRessources = r;
		}else{
			// only new results are allowed
			if(r.getUpdated().compareTo(this.stolenRessources.getUpdated())>0){
				this.stolenRessources.add(r);
			}
		}
	}
	
	// GETTERS
	
	public User getOwner(){
		return this.owner;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public Ressources getOrbitalRessources(){
		return this.orbitRessources;
	}
	
	public Ressources getStolenRessources(){
		return this.stolenRessources;
	}
	
	public Coordinate getPosition(){
		return this.position;
	}
	
	public String toString(){
		return this.position + " " + this.name + " (" + this.owner + ")";
	}
	
	public String getUniqueId(){
		return this.uniqueId;
	}
	
	public static Planet getInstance(Coordinate c){
		return Planet.getInstance(c.toString());
	}
	
	public static Planet getInstance(int galaxy, int system, int orbit){
		return Planet.getInstance(new Coordinate(galaxy,system,orbit));
	}
	
	public static Planet getInstance(String id) {
		if (Universe.getPlanetMap().planetExists(id)){
			return Universe.getPlanetMap().getPlanet(id);
		}else{
			Planet p = new Planet(id);
			Universe.getPlanetMap().addPlanet(p);
			return p;
		}
		
	}
	
	public Calendar getUpdated(){
		return this.updated;
	}

	@Override
	public int compareTo(Planet arg0) {
		return this.position.compareTo(arg0.getPosition());
//		return this.uniqueId.compareTo(arg0.uniqueId);
	}

	

}
