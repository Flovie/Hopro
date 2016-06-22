package misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StandardLoggerImpl implements StandardLogger{
	
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void addLog(String s) {
		System.out.println(format.format(Calendar.getInstance().getTime()) + ": " + s);		
	}

	@Override
	public void addSubLog(String s, int hierachy) {
		String tabs = "";
		for(int j=0;j<hierachy;j++){
			tabs = tabs + "-";
		}
		System.out.println(tabs + "> " + s);
		
	}

	@Override
	public void closeLogger() {
		// TODO Auto-generated method stub
		
	}
	
}
