package listener.gui.raidtool;

import horiversumObjects.Coordinate;
import horiversumObjects.Planet;
import horiversumObjects.User;

public class RaidToolRow {
	
	public String coordinate;
	public String planetName;
	public String typ;
	public double distance;
	public User owner;
	public String alliance="";
	public float relScore = 0;
	public float relScoreChange = 0;
	public float scoreChange = 0;
	public String activityStatus = "";
	public String status ="";
	
	public RaidToolRow(Planet p, Coordinate startCoordinate){
		this.coordinate = p.getUniqueId();
		this.planetName = p.getName();
		this.typ = Planet.getShortType(p.getType());
		this.owner = p.getOwner();		
		this.distance = startCoordinate.calculateDistance(p.getPosition());
		if(this.owner.getActivityStatus()!=null){
			if(this.owner.getActivityStatus()){
				this.activityStatus = "aktiv";
			}else{
				this.activityStatus = "inaktiv";
			}
		}		
		if(this.owner.getStatus() != null){
			this.status = this.owner.getStatus();
		}
		if(this.owner.getAlliance()!=null){
			this.alliance = this.owner.getAlliance();
		}
		if(this.owner.getScore()!=null){
			if(this.owner.getScore().getRelScore()!=null){
				this.relScore = this.owner.getScore().getRelScore();
			}			
			if(this.owner.getScore().getRelScoreChangePerDay() != null){
				this.relScoreChange = this.owner.getScore().getRelScoreChangePerDay();
			}
			if(this.owner.getScore().getScoreChange()!=null){
				this.scoreChange = this.owner.getScore().getScoreChange();
			}
		}				
	}

}
