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
	private Integer activityRatio;
	private Score score;
	private String status;
	
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
	public static User transferFromXml(XmlUser xml){
		User u = new User(xml.userId);
		if (xml.alliance != null){
			u.alliance = xml.alliance;
		}
		if (xml.activityStatus!= null){
			u.activityStatus = xml.activityStatus;
		}	
		if (xml.activityRatio!=null){
			u.activityRatio = xml.activityRatio;
		}
		if (xml.status!=null){
			u.status = xml.status;
		}
		if(xml.planets==null){
			u.planets = new HashSet<Planet>();
		}else{
			for(String s: xml.planets){
				Planet p = Planet.getInstance(s);
				if (p.getOwner().userId.equals(u.userId)){
					u.planets.add(p);
				}							
			}
		}			
		u.score = xml.score;
		u.updated = xml.updated;	
		return u;
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
	
	public void setActivityRatio(int activityRatio){
		this.activityRatio = activityRatio;
		this.update();
	}
	
	public void setScore(Score score){
		this.score = score;
		this.update();
	}
	
	public void setStatus(String status) {
		this.status = status;
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
	
	public Integer getActivityRatio(){
		return this.activityRatio;
	}

	public String getName() {
		return userId;
	}
	
	public String toString(){
		return userId;
	}
	
	public Score getScore(){
		return this.score;
	}
	
	public Set<Planet> getPlanets(){
		return this.planets;
	}
	
	public Calendar getUpdated(){
		return this.updated;
	}
	
	public String getStatus() {
		return status;
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
