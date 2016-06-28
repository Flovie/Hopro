package xml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import horiversumObjects.Planet;
import horiversumObjects.Ressources;

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
	
	@XmlElement(name="stolenRessources", required=false)
	public XmlStolenRessources stolenRessources = null;
	
	
	public void transferDataToXml(Planet p){
		this.uniqueId = p.getUniqueId();
		this.updated = p.getUpdated();
		this.name = p.getName();
		this.type = p.getType();
		if(p.getOwner() != null){
			this.owner = p.getOwner().getName();
		}		
		this.orbitalRessources = p.getOrbitalRessources();
		if (p.getStolenRessources() != null){
			// this is required for nested elements in xml
			this.stolenRessources = new XmlStolenRessources();
			this.stolenRessources.stolenRessources = new ArrayList<Ressources>();
			this.stolenRessources.stolenRessources.addAll(p.getStolenRessources().values());
		}
	}

	@Override
	public int compareTo(XmlPlanet o) {
		return this.uniqueId.compareTo(o.uniqueId);
	}
	
	
	public static class XmlStolenRessources{
		@XmlElement(name="raid", required=false)
		private List<Ressources> stolenRessources = null;
		
		public List<Ressources> getStolenRessources(){
			return this.stolenRessources;
		}
	}
	
}
