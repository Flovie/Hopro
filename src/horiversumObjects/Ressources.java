package horiversumObjects;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ressources {
	
	@XmlElement(name="iron",required=false)
	private Integer iron;
	@XmlElement(name="mineral",required=false)
	private Integer minerals;
	@XmlElement(name="fuel", required=false)
	private Integer fuel;
	@XmlAttribute(required=true)
	private Calendar updated;
		
	public int getIron(){
		return this.iron;
	}
		
	public int getMinerals(){
		return this.minerals;
	}
		
	public int getFuel(){
		return this.fuel;
	}
		
	public Calendar getUpdated(){
		return this.updated;
	}
	
	public void setIron(int iron){
		this.iron = iron;
		this.update();
	}
	
	public void setMinerals(int minerals){
		this.minerals = minerals;
		this.update();
	}
	
	public void setFuel(int fuel){
		this.fuel = fuel;
		this.update();
	}
	
	private void update(){
		this.updated = Calendar.getInstance();
	}
	
	public void add(Ressources r){
		this.iron = this.iron + r.getIron();
		this.minerals = this.minerals + r.getMinerals();
		this.fuel = this.fuel + r.getFuel();
	}
	
}
