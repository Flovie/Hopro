package horiversumObjects;

public class Coordinate implements Comparable<Coordinate>{
	
	private int galaxy;
	private int system;
	private int orbit;
	
	public Coordinate(int galaxy,int system,int orbit){
		this.galaxy = galaxy;
		this.system = system;
		this.orbit = orbit;
	}
	
	public Coordinate(String s){
		String[] infos = s.split(":");
		this.galaxy = Integer.parseInt(infos[0].trim());
		this.system = Integer.parseInt(infos[1].trim());
		this.orbit = Integer.parseInt(infos[2].trim());
	}
	
	public String toString(){
		return this.galaxy + ":" + this.system + ":" + this.orbit;
	}
	
	public int getGalaxy(){
		return this.galaxy;		
	}
	
	public int getSystem(){
		return this.system;
	}
	
	public int getOrbit(){
		return this.orbit;
	}

	@Override
	public int compareTo(Coordinate arg0) {
		if (this.galaxy==arg0.galaxy){
			if(this.system==arg0.system){
				return Integer.compare(this.orbit, arg0.orbit);
			}else{
				return Integer.compare(this.system, arg0.system);
			}
		}else{
			return Integer.compare(this.galaxy,arg0.galaxy);
		}		
	}
	
	public boolean equals(Coordinate c){
		if(c.getGalaxy() == this.galaxy && c.getOrbit() == this.getOrbit() && c.getSystem() == this.getSystem()){
			return true;
		}else{
			return false;
		}
	}
	
	public Double calculateDistance(Coordinate c){
		Double distance;
		if(Universe.getHoproDataSet().systemExists(this.galaxy, this.system) && Universe.getHoproDataSet().systemExists(c.galaxy, c.system)){
			GalaxySystem system1 = Universe.getHoproDataSet().getSystem(this.galaxy, this.system);
			GalaxySystem system2 = Universe.getHoproDataSet().getSystem(c.galaxy, c.system);
			distance = system1.calculateDistance(system2);
		}else{
			distance = Double.NaN;
		}					
		return distance;
	}

}
