package xml;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import horiversumObjects.Ressources;
import horiversumObjects.Planet;

@XmlRootElement(name="planet")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPlanet implements Comparable<XmlPlanet>{
	@XmlAttribute(name="id", required=true)
	public String uniqueId;
	
	@XmlAttribute(required=true)
	public Calendar updated;
	
	@XmlElement(name="name")
	public String name;
	
	@XmlElement(name="type", required=false)
	public String type = null;
	
    @XmlElement(name="owner", required=false)
	public String owner = null;
	
	@XmlElement(name="orbitalRessources", required=false)
	public Ressources orbitalRessources = null;
	
	public void transferDataToXml(Planet p){
		this.uniqueId = p.getUniqueId();
		this.updated = p.getUpdated();
		this.name = p.getName();
		this.type = p.getType();
		if(p.getOwner() != null){
			this.owner = p.getOwner().getName();
		}		
		this.orbitalRessources = p.getOrbitalRessources();
	}

	@Override
	public int compareTo(XmlPlanet o) {
		return this.uniqueId.compareTo(o.uniqueId);
	}
	
}
