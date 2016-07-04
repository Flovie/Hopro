package horiversumObjects;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

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
	private Map<Calendar,Ressources> stolenRessources = null;
	
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
	
	public static Planet transferFromXml(XmlPlanet xml){
		Planet p = new Planet(xml.uniqueId);
		p.name = xml.name;
		p.type = xml.type;
		if(xml.owner != null){
			p.owner = User.getInstance(xml.owner);
		}		
		p.updated = xml.updated;
		p.orbitRessources = xml.orbitalRessources;
		if(xml.stolenRessources!=null){
			p.initializeStolenRessources();
			for(Ressources r: xml.stolenRessources.getStolenRessources()){
				p.stolenRessources.put(r.getUpdated(), r);
			}
		}
		return p;
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
		this.update();
	}
	
	public void removeOwner(){
		if (this.owner!=null){
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
	
	public void update(Planet p){
		if(this.uniqueId.equalsIgnoreCase(p.uniqueId)){
			boolean isNewer = this.getUpdated().compareTo(p.getUpdated()) < 1;
			if(p.type != null){
				if(isNewer || this.type == null){
					this.type = p.type;
				}				
			}
			if(p.type != null){
				if(isNewer || this.name == null){
					this.name = p.name;
				}				
			}
			if(p.getOrbitalRessources() != null){
				if(this.orbitRessources != null){
					if(this.orbitRessources.getUpdated().compareTo(p.getOrbitalRessources().getUpdated()) < 1){
						this.orbitRessources = p.getOrbitalRessources();
					}					
				}else{
					this.orbitRessources = p.getOrbitalRessources();
				}
			}
			if(p.owner != null){
				if(isNewer || this.owner == null){
					this.owner = p.owner;
				}				
			}
			if(p.getStolenRessources() != null){
				this.initializeStolenRessources();
				for(Ressources r: p.getStolenRessources().values()){
					if(!this.stolenRessources.containsKey(r.getUpdated())){
						this.stolenRessources.put(r.getUpdated(), r);
					}
				}
			}
			if(isNewer){
				this.updated = p.updated;
			}
		}
	}
	
	public void addStolenRessources(Ressources r){
		if (this.stolenRessources == null){
			this.initializeStolenRessources();
			this.stolenRessources.put(r.getUpdated(), r);
		}else{
			if(!this.stolenRessources.containsKey(r.getUpdated())){
				this.stolenRessources.put(r.getUpdated(), r);
			}
		}
		
		
	}
	
	private void initializeStolenRessources(){
		if(this.stolenRessources==null){
			this.stolenRessources = new TreeMap<Calendar,Ressources>();
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
	
	public Map<Calendar,Ressources> getStolenRessources(){
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
	
	public static String getShortType(String longType){
		switch(longType){
		case "Dschungelplanet": return "JUPL";
		case "Eisenerzplanet": return "IDPL";
		case "Eisplanet": return "ICPL";
		case "Ethanozeanplanet": return "EOPL";
		case "Felsenplanet": return "RKPL";
		case "Lavaplanet": return "LVPL";
		case "Mineralinselplanet": return "MIPL";
		case "Säurenplanet": return "ACPL";
		case "Standard Planet": return "STPL";
		case "Staubplanet": return "MUPL";
		case "Vulkanplanet": return "VUPL";
		case "Waldplanet": return "FOPL";		
		case "Wasserplanet": return "WAPL";
		case "Wasserstoffplanet": return "HYPL";								
		case "Wolkenplanet": return "CLPL";
		case "Wüstenplanet": return "DSPL";
		default: return longType;
		}
	}
	

}
