package listener.gui.raidtool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import misc.GlobalObjects;

public class RaidTool extends JFrame{
	

	private static final long serialVersionUID = 844521352931960193L;
	
	private JTable raidToolTable; 
	private RaidTableModel tableData;

	public RaidTool(){
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addTable();
		this.pack();
		this.setVisible(true);
		this.toFront();
	}
	
	
	private void addTable(){		
		this.tableData = new RaidTableModel();
		this.tableData.setOriginCoordinate(GlobalObjects.currentPosition);
		this.raidToolTable = new JTable(this.tableData);
		this.raidToolTable.setFillsViewportHeight(true);
		TableRowSorter<RaidTableModel> sorter = new TableRowSorter<RaidTableModel>(this.tableData);
		this.raidToolTable.setRowSorter(sorter);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
//		this.raidToolTable.setRowSorter(new TableRowSorter<RaidTableModel>());
		
		// Columnwidth
		this.raidToolTable.getColumnModel().getColumn(0).setMaxWidth(60);
		this.raidToolTable.getColumnModel().getColumn(0).setMinWidth(60);
		
		JScrollPane scrollPane = new JScrollPane(this.raidToolTable);
		scrollPane.setPreferredSize(new Dimension(1000,600));
		this.getContentPane().add(scrollPane,BorderLayout.CENTER);
	}

}
