package listener.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import horiversumObjects.Universe;
import misc.ErrorLogger;
import misc.Settings;
import misc.StandardLogger;

public class LogScreen extends JFrame implements ErrorLogger, StandardLogger, ActionListener{
	
	private JTextArea logField;
	private JButton saveButton;
	
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -61446748369816946L;

	public LogScreen(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Horiversum Listener Log");
		this.setSize(400, 600);
		this.setLayout(new BorderLayout());
		this.logField = new JTextArea();
		this.logField.setEditable(true);
		this.logField.setLineWrap(true);	
		this.setAlwaysOnTop(Settings.isAlwaysOnTop());
		DefaultCaret caret = (DefaultCaret)this.logField.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(this.logField);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane,BorderLayout.CENTER);
		
		this.saveButton = new JButton("Save");
		this.saveButton.addActionListener(this);
		this.add(this.saveButton, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void addLog(String s){
		this.logField.append(format.format(Calendar.getInstance().getTime()) + ": " + s);
		this.logField.append(System.lineSeparator());
	}
	
	public void addSubLog(String s,int hierachy){
		String tabs = "";
		for(int j=0;j<hierachy;j++){
			tabs = tabs + "-";
		}
		this.logField.append(tabs + "> " + s);
		this.logField.append(System.lineSeparator());
	}
	
	public void closeLogger(){
		this.setVisible(false);
		dispose();
	}

	@Override
	public void logError(Exception e) {
		this.addLog(e.toString());		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==this.saveButton){
			Universe.saveAll();			
		}
	}

}
