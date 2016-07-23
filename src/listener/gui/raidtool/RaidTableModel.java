package listener.gui.raidtool;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import horiversumObjects.Coordinate;
import horiversumObjects.Planet;
import horiversumObjects.Universe;

public class RaidTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String[] columnNames = {"","Name","Entfernung","Typ","Spieler","Allianz","Aktiv","Status","rel. Score","d(relScore)/dt [1/d]","d(totScore)/dt [1/d]"};
	
	private List<RaidToolRow> data = new ArrayList<RaidToolRow>();	
	
	public Coordinate startPoint = new Coordinate("1:1:1");
	
	public RaidTableModel(){
		this.update();
	}
	

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	public String getColumnName(int col) {
        return this.columnNames[col];
    }
	
	public void setOriginCoordinate(Coordinate c){
		this.startPoint = c;
		this.update();
	}
	
	public void update(){
		this.data.clear();
		List<Planet> planets = Universe.getHoproDataSet().getHabitedPlanets();		
		for(Planet p:planets){
			this.data.add(new RaidToolRow(p,startPoint));
		}
	}
	
	public Class<?> getColumnClass(int c){
		if(this.data.size()>=1){
			return this.getValueAt(0, c).getClass();
		}else{
			return Object.class;
		}		
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
//		private String[] columnNames = {"","Name","Entfernung","Typ","Spieler","Allianz","Aktiv","Status","rel. Score","d(relScore)/dt [1/d]"};
		RaidToolRow r = data.get(rowIndex);
		switch (columnIndex){
		case 0:
			return r.coordinate;
		case 1:
			return r.planetName;
		case 2:
			return Math.round(r.distance*100)/100.0;		
		case 3:
			return r.typ;			
		case 4:
			return r.owner.getName();
		case 5:
			return r.alliance;
		case 6:
			return r.activityStatus;
		case 7:
			return r.status;
		case 8:
			return r.relScore;
		case 9:
			return r.relScoreChange;
		case 10:
			return r.scoreChange;
		default:
			return null;			
		}
	}


}
