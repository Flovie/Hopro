package misc;

import java.util.Calendar;

public class StandardLoggerImpl implements StandardLogger{

	@Override
	public void addLog(String s) {
		System.out.println(Calendar.getInstance().getTime().toString() + ": " + s);		
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
