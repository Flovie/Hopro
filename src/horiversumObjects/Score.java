package horiversumObjects;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Score {
	
	@XmlAttribute(name="relScore",required=false)
	private Float relScore;
	@XmlAttribute(name="score",required=false)
	private Long score;
	@XmlAttribute(name="relScoreChangePerDay",required=false)
	private Float relScoreChange;
	@XmlAttribute(name="scoreChangePerDay",required=false)
	private Float scoreChange;
	
	@XmlAttribute(required=true)
	private Calendar updated;

	public Float getRelScore() {
		return relScore;
	}
	
	public Float getRelScoreChangePerDay(){
		return this.relScoreChange;
	}
	
	public Float getScoreChange(){
		return this.scoreChange;
	}

	public Long getScore() {
		return score;
	}
	
	public Calendar getUpdated() {
		return updated;
	}

	
	// Setters
	public void setScore(Long score, Float relScore) {
		float timeSinceLastUpdate;
		if(this.updated!=null){
			timeSinceLastUpdate = Calendar.getInstance().getTimeInMillis() - this.updated.getTimeInMillis(); // ms
		}else{
			timeSinceLastUpdate = Long.MAX_VALUE;
		}		
		// allow only one update per half day
		if(timeSinceLastUpdate > (12 * 60 * 60 * 1000)){
			if(score!=null){
				if(this.score!=null){
					this.scoreChange = (float) ((score - this.score) / (((float)timeSinceLastUpdate) / 24.0f / 60.0f / 60.0 / 1000.0));
				}
				this.score = score;
			}
			if(relScore!=null){
				if(this.relScore != null){					
					this.relScoreChange = (float) ((relScore - this.relScore) / (((float)timeSinceLastUpdate) / 24.0f / 60.0f / 60.0 / 1000.0)); 
				} 
				this.relScore = relScore;
			}
			this.update();
		}		
	}
	
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
	
	public void update(){
		this.updated = Calendar.getInstance();
	}
	
	public String toString(){
		return this.score + ", " + this.relScore + " (" + this.updated.getTime() + ")";
	}

}
