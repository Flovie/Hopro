package listener.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import misc.ErrorLogger;
import misc.Settings;
import misc.StandardLogger;

public class MainFrame extends JFrame implements ErrorLogger, StandardLogger, ActionListener{
	
	private LogScreen logScreen;
	private MainMenue mainMenue;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7793845977131647077L;
	
	public MainFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Hopro");
		this.setAlwaysOnTop(Settings.isAlwaysOnTop());
		this.setLayout(new FlowLayout());
		this.mainMenue = new MainMenue();		
		this.add(mainMenue);
		this.logScreen = new LogScreen();
		this.logScreen.setPreferredSize(new Dimension(400,600));
		this.logScreen.setMinimumSize(new Dimension(400,600));
		this.add(logScreen);
		this.setVisible(true);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLog(String s) {
		this.logScreen.addLog(s);
	}

	@Override
	public void addSubLog(String s, int hierachy) {
		this.logScreen.addSubLog(s, hierachy);
		
	}

	@Override
	public void logError(Exception e) {
		this.logScreen.addLog(e.toString());
		
	}

	@Override
	public void closeLogger() {
		this.setVisible(false);
		dispose();		
	}

}
