package misc;

public class ErrorLoggerImpl implements ErrorLogger {

	@Override
	public void logError(Exception e) {
		e.printStackTrace();		
	}

	@Override
	public void closeLogger() {
		// TODO Auto-generated method stub
		
	}
	

}
