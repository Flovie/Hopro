package horiversumObjects;

public class GalaxySystem {
	
	private int galaxy;
	
	private int system;
	
	private String name;
	
	private String discoverer;
	
	private float positionX;
	
	private float positionY;
	
	private String owner;
	
	private int numberOfPlanets;
	
	private int numberOfColonies;



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
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
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
		if (Universe.getPlanetMap().systemExists(galaxy, system)){
			s = Universe.getPlanetMap().getSystem(galaxy, system);
		}else{
			s = new GalaxySystem();
			s.galaxy = galaxy;
			s.system = system;
		}
		return s;
	}
	
			
}
