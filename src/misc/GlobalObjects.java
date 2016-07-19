package misc;

import horiversumObjects.Coordinate;

public class GlobalObjects {
	
	public static StandardLogger logger = new StandardLoggerImpl();
	public static ErrorLogger errorLogger = new ErrorLoggerImpl();
	
	public static Coordinate currentPosition = new Coordinate(1,1,1);
	

}
