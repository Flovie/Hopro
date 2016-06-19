package misc;

public interface StandardLogger {
	
	public void addLog(String s);
	public void addSubLog(String s, int hierachy);
	public void closeLogger();

}
