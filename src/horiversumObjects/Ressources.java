package horiversumObjects;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ressources {
	
	@XmlAttribute(name="iron",required=false)
	private Integer iron;
	@XmlAttribute(name="mineral",required=false)
	private Integer minerals;
	@XmlAttribute(name="fuel", required=false)
	private Integer fuel;
	@XmlAttribute(required=true)
	private Calendar updated;
	
	@XmlTransient
	private boolean autoupdate = true;
		
	public int getIron(){
			return this.iron;
	}
		
	public int getMinerals(){
			return this.minerals;
	}
		
	public int getFuel(){
//		if(this.fuel==null){
//			return 0;
//		}else{
			return this.fuel;
//		}		
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
		if(this.autoupdate){
			this.updated = Calendar.getInstance();
		}
	}
	
	public void setUpdate(Calendar c){
		this.updated = c;
		this.autoupdate=false;
	}
	
	public void add(Ressources r){
		if(this.iron != null){
			this.iron = this.iron + r.getIron();
		}else{
			this.iron = r.getIron();
		}
		if(this.minerals != null){
			this.minerals = this.minerals + r.getMinerals();
		}else{
			this.minerals = r.getMinerals();
		}
		if(this.fuel != null){
			this.fuel = this.fuel + r.getFuel();
		}else{
			this.fuel = r.getFuel();
		}
		this.update();
	}
	
	public long getSum(){
		long sum = 0;
		if(this.iron!=null){
			sum = sum + this.iron;
		}
		if(this.minerals!=null){
			sum = sum + this.minerals;
		}
		if(this.fuel!=null){
			sum = sum + this.fuel;
		}
		return sum;
	}
	
}
