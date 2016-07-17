package horiversumObjects;

import java.util.Calendar;

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
	
	public void setStatus(String status) {
		this.status = status;
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
		if(this.score==null){
			this.score = new Score();
			return this.score;
		}else{
			return this.score;
		}		
	}
	
	
	public Calendar getUpdated(){
		return this.updated;
	}
	
	public String getStatus() {
		return status;
	}


	public static User getInstance(String id) {
		id = id.replace("...", "*");		
		if (Universe.getHoproDataSet().userExists(id)){
			Universe.getHoproDataSet().getUser(id);
			return Universe.getHoproDataSet().getUser(id);
		}else{
			User u = new User(id);
			Universe.getHoproDataSet().addUser(u);
			return u;
		}		
	}


	@Override
	public int compareTo(User o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}

}
