package horiversumObjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="system")
@XmlAccessorType(XmlAccessType.FIELD)
public class GalaxySystem implements Comparable<GalaxySystem>{
	
	@XmlAttribute(name="galaxy", required=true)
	private Integer galaxy;
	
	@XmlAttribute(name="system", required=true)
	private Integer system;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String discoverer;
	
	@XmlElement
	private Float positionX;
	
	@XmlElement
	private Float positionY;
	
	@XmlElement
	private String owner;
	
	@XmlElement
	private Integer numberOfPlanets;
	
	@XmlElement
	private Integer numberOfColonies;



	public int getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(int galaxy) {
		this.galaxy = galaxy;
	}

	public int getSystem() {
		return system;
	}

	public void setSystem(int system) {
		this.system = system;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscoverer() {
		return discoverer;
	}

	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}

	public float getPositionX() {
		if(positionX==null){
			return Integer.MIN_VALUE;
		}else{
			return positionX;
		}
		
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		if(positionY==null){
			return Integer.MIN_VALUE;
		}else{
			return positionY;
		}
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getNumberOfPlanets() {
		return numberOfPlanets;
	}

	public void setNumberOfPlanets(int numberOfPlanets) {
		this.numberOfPlanets = numberOfPlanets;
	}

	public int getNumberOfColonies() {
		return numberOfColonies;
	}

	public void setNumberOfColonies(int numberOfColonies) {
		this.numberOfColonies = numberOfColonies;
	}
	
	public String toString(){
		return this.galaxy + ":" + this.system;
	}
	
	
	public static GalaxySystem getInstance(int galaxy, int system){
		GalaxySystem s;
		if (Universe.getHoproDataSet().systemExists(galaxy, system)){
			s = Universe.getHoproDataSet().getSystem(galaxy, system);
		}else{
			s = new GalaxySystem();
			s.galaxy = galaxy;
			s.system = system;
			Universe.getHoproDataSet().addSystem(s);
		}
		return s;
	}

	@Override
	public int compareTo(GalaxySystem arg0) {
		int result = this.galaxy.compareTo(arg0.getGalaxy());
		if (result==0){
			result = this.system.compareTo(arg0.getSystem());
		}
		return result;
	}
	
	public double calculateDistance(GalaxySystem s){
		double distance;
		if(this.galaxy == s.galaxy) {			
			distance = Math.sqrt(Math.pow((this.getPositionX() - s.getPositionX()), 2) + Math.pow((this.getPositionY() - s.getPositionY()), 2));						
		}else{
			// not yet implemented
			distance = Double.MAX_VALUE;
		}
		return distance;
	}
	
			
}
