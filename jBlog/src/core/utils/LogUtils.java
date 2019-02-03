package core.utils;

import java.util.Date;

public class LogUtils {

	public static void logInfo(String massage){
		System.out.println("#INFO (" + new Date() + "): " + massage); 
	}

	public static void logErrore(String massage){
		System.out.println("#ERRORE (" + new Date() + "): " + massage);		
	}	
	
}
