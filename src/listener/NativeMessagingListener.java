package listener;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.nio.charset.StandardCharsets;

import horiversumObjects.Universe;
import listener.gui.LogScreen;
import listener.htmlListeners.BeschaffungsreportListener;
import listener.htmlListeners.GalaxyListener;
import listener.htmlListeners.HighscoreListener;
import listener.htmlListeners.SystemMapListener;
import misc.GlobalObjects;
import misc.Settings;

public class NativeMessagingListener {
	
	
		
	
	public static void main(String[] args){
		try {
			GlobalObjects.logger = new LogScreen();
			
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(System.in));
			
			// Start log GUI
			GlobalObjects.logger.addLog("Start des Loggers");
			
			// Load
			Universe.loadAll();			
			
			long lastSaved = System.currentTimeMillis();
		    
		    while(true){		    			    	
		        int length = 0;
		        String in = "";
		       
		        byte[] bytes = new byte[4];
		        System.in.read(bytes, 0, 4);		        
		        length = getInt(bytes);		        		        
		        
		        if (length > 0){
		        	byte[] data = new byte[length];		        	
		        	
		        	// read the full data into the buffer
		        	try{
		        		dataInputStream.readFully(data);
		        	} catch (EOFException e){
		        		System.err.println("An EOF error occured. Ignoring this input.");
		        		continue;
		        	}			        			        

		        	in = new String(data, StandardCharsets.UTF_8);
		        	if(in.equals("\"!STOP!\"")){
		        		System.err.println("Received Stop signal");
		        		GlobalObjects.logger.addLog("Ending...");
		        		GlobalObjects.logger.closeLogger();
		        		break;		        		
		        	}
//		        	System.err.println(in);
		        	HtmlReader htmlReader = new HtmlReader(in);
		        	htmlReader.addListener(new GalaxyListener());
		        	htmlReader.addListener(new SystemMapListener());
		        	htmlReader.addListener(new BeschaffungsreportListener());
		        	htmlReader.addListener(new HighscoreListener());
		        	Thread readerThread = new Thread(htmlReader,htmlReader.toString());
		        	readerThread.start();		        	
		        }
		        
		        // Saving every $saveIntervall$ seconds		        
		        if ((System.currentTimeMillis()-lastSaved)/1000 >=  Settings.getSaveIntervall()){
		        	Universe.saveAll();
		        	lastSaved = System.currentTimeMillis();		        	
		        }

		    }
		    // Save the planet map a last time before ending
		    Universe.saveAll();
		} catch (Exception e) {
			GlobalObjects.errorLogger.logError(e);    
		}
	}
	
    /**
     * read the message size from Chrome.
     * @param bytes
     * @return
     */
    public static int getInt(byte[] bytes) {
        return  (bytes[3]<<24) & 0xff000000|
                (bytes[2]<<16) & 0x00ff0000|
                (bytes[1]<< 8) & 0x0000ff00|
                (bytes[0]<< 0) & 0x000000ff;
    }
 
    /**
     * transform the length into the 32-bit message length.
     * @param length
     * @return
     */
    public static byte[] getBytes(int length) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ( length      & 0xFF);
        bytes[1] = (byte) ((length>>8)  & 0xFF);
        bytes[2] = (byte) ((length>>16) & 0xFF);
        bytes[3] = (byte) ((length>>24) & 0xFF);
        return bytes;
    }   

}
