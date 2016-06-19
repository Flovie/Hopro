package misc;

public interface ErrorLogger {
	
	public void logError(Exception e);
	
	public void closeLogger();

}
