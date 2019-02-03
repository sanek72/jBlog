package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Properies {
	
    private Properties prop = null;
    private InputStream input = null;	

	public Properies() {
		init();
	}
	
	private void init(){
		prop  = new Properties();

		try {
			String path = new File(".").getCanonicalPath();		
			input = new FileInputStream(Constants.GLOBALCFGPATH);
			prop.load(input);
			input.close();			
		} catch (Exception  e) {
			e.getMessage();
			throw new Error("Error loading configuration file :" + Constants.GLOBALCFGPATH);
		}
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}	
	
}
