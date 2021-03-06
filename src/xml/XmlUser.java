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
import horiversumObjects.Score;
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
	public Score score;	
	
	@XmlElement(required=false)
	public Integer activityRatio;
	
	@XmlElement(required=false)
	public String status;
	
	@XmlAttribute(required=true)
	public Calendar updated;

	
	
	public void transferDataToXml(User u){
		this.userId = u.getName();
		this.alliance = u.getAlliance();
		if (u.getActivityStatus()!=null){
			this.activityStatus = u.getActivityStatus();
		}
		if(u.getActivityRatio()!=null){
			this.activityRatio = u.getActivityRatio();
		}
		if(u.getStatus()!=null){
			this.status = u.getStatus();
		}
		this.score = u.getScore();
		this.updated = u.getUpdated();
	}
	

	@Override
	public int compareTo(XmlUser o) {
		return this.userId.compareTo(o.userId);
	}
	
	

}
