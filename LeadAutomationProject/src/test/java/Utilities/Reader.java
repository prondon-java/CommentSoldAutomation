package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class Reader {
	
	static Properties configFile;

	static {

		try {

			String filePath = "src/test/resources/testData/testData.properties";
			FileInputStream input = new FileInputStream(filePath);
			configFile = new Properties();
			configFile.load(input);
			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return configFile.getProperty(key);
	}
}
