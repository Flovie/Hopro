package xml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import horiversumObjects.Planet;
import horiversumObjects.User;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlUser implements Comparable<XmlUser>{
	
	@XmlAttribute(name="id", required = true)
	public String userId;
	
	@XmlElement(required = false)
	public String alliance;
	
	@XmlAttribute(name="active", required=false)
	public Boolean activityStatus;
	
	@XmlElement(required=false)
	public Long score;
	
	@XmlElement(required=false)
	public List<String> planets = null;
	
	@XmlAttribute(required=true)
	public Calendar updated;
	
	
	public void transferDataToXml(User u){
		this.userId = u.getName();
		this.alliance = u.getAlliance();
		if (u.getActivityStatus()!=null){
			this.activityStatus = u.getActivityStatus();
		}		
		this.score = u.getScore();
		if (u.getPlanets()!=null){
			this.planets = new ArrayList<String>();
			for(Planet p: u.getPlanets()){				
				this.planets.add(p.uniqueId);
				Collections.sort(this.planets);
			}
		}
		this.updated = u.getUpdated();
	}
	

	@Override
	public int compareTo(XmlUser o) {
		return this.userId.compareTo(o.userId);
	}
	
	

}
