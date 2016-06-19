package horiversumObjects;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.XmlUser;
import xml.adapters.UserAdapter;

@XmlRootElement(name="user")
@XmlJavaTypeAdapter(UserAdapter.class)
public class User implements Comparable<User>{
	
	// Unique Id = name
	private final String userId;
		
	// Misc
	private String alliance;
	private Boolean activityStatus;
	private Long score;
	
	// Planets
	private Set<Planet> planets = new HashSet<Planet>();
	
	// time
	private Calendar updated = null;	
	
	private User(String name){
		this.userId = name;
	}
	
	public void update(){
		this.updated = Calendar.getInstance();
	}
	
	// XML Functions
	public void transferDataFromXml(XmlUser xml){
		if (this.alliance != null){
			this.alliance = xml.alliance;
		}
		if (xml.activityStatus!= null){
			this.activityStatus = xml.activityStatus;
		}		
		if(xml.planets==null){
			this.planets = new HashSet<Planet>();
		}else{
			for(String s: xml.planets){
				Planet p = Planet.getInstance(s);
				if (p.getOwner().userId.equals(this.userId)){
					this.planets.add(p);
				}							
			}
		}			
		this.score = xml.score;
		this.updated = xml.updated;		
	}
	
	
	// SETTERS	
	public void setAlliance(String alliance) {
		this.alliance = alliance;
		this.update();
	}

	public void setActivityStatus(boolean activityStatus) {
		this.activityStatus = activityStatus;
		this.update();
	}
	
	public void setScore(long score){
		this.score = score;
		this.update();
	}
	
	public void addPlanet(Planet p){
		if (!(this.planets.contains(p))){
			this.planets.add(p);
		}		
		this.update();
	}
	
	public void removePlanet(Planet p){
		this.planets.remove(p);
		this.update();
	}
	
	
	// GETTERS	
	public String getAlliance() {
		return alliance;
	}
	
	public Boolean getActivityStatus() {
		return activityStatus;
	}

	public String getName() {
		return userId;
	}
	
	public String toString(){
		return userId;
	}
	
	public Long getScore(){
		return this.score;
	}
	
	public Set<Planet> getPlanets(){
		return this.planets;
	}
	
	public Calendar getUpdated(){
		return this.updated;
	}


	public static User getInstance(String id) {
		id = id.replace("...", "*");		
		if (Universe.getUserMap().userExists(id)){
			Universe.getUserMap().getUser(id);
			return Universe.getUserMap().getUser(id);
		}else{
			User u = new User(id);
			Universe.getUserMap().addUser(u);
			return u;
		}		
	}


	@Override
	public int compareTo(User o) {
		return this.getName().compareTo(o.getName());
	}
		

}
