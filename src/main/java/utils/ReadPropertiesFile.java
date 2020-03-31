package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile 
{
//	public Properties prop;

	
	public static String getPropertyValue(String PropertyKey)
	{
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\testData\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(PropertyKey);
	}
}
