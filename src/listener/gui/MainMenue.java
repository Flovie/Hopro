package listener.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import listener.gui.raidtool.RaidTool;

public class MainMenue extends JPanel implements ActionListener{

	private JButton btRaidTool;
	
	private static final long serialVersionUID = 1L;
	
	public MainMenue(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		// Raid tool button
		this.btRaidTool = new JButton("Raidtool");
		this.btRaidTool.addActionListener(this);		
		this.add(this.btRaidTool, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btRaidTool){
			RaidTool rt = new RaidTool();	
		}
		
	}

}
