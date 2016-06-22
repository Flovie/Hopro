package horiversumObjects;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Score {
	
	@XmlElement(name="relScore",required=false)
	private Float relScore;
	@XmlElement(name="score",required=false)
	private Long score;
	
	@XmlAttribute(required=true)
	private Calendar updated;

	public Float getRelScore() {
		return relScore;
	}

	public void setRelScore(Float relScore) {
		this.relScore = relScore;
		this.update();
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
		this.update();
	}

	public Calendar getUpdated() {
		return updated;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
	
	public void update(){
		this.updated = Calendar.getInstance();
	}

}
