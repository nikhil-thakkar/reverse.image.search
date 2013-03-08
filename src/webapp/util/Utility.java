package webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Utility {
	
	public static Properties PROPERTIES =null;
	public static String propertyFile ="Fileupload.properties";

	
	static {
		PROPERTIES = new Properties();
		try {
			String path = new Utility().getPath();
			path = path.replaceAll("%20", " ");
			File f = new File(path + propertyFile);
			PROPERTIES.load(new FileInputStream(f));
		
		}catch (Exception e) {
			
		}
	}
	
	public static Properties getProperties(String propertyFile){
		
		Properties pr = new Properties();
		try {
			String path = new Utility().getPath();
			path = path.replaceAll("%20", " ");
			File f = new File(path + propertyFile);
			pr.load(new FileInputStream(f));
			return pr;
		}catch (Exception e) {
			
		}

		return null;
	}
	
	public String getPath()
	{
		return getClass().getResource("/").getPath();
	}
	public static Properties getProperties(){
		return PROPERTIES;
	}


}
