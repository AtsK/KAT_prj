package ee.ut.math.tvt.KAT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Map;

public class ReadPropertiesFile {
	public Properties readProps(String fileLocation) {
		try {
			File file = new File(fileLocation);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

//Reading properties example
//ReadPropertiesFile appProps = new ReadPropertiesFile();
//Properties appProperties = appProps.readProps("application.properties");
//Properties versionProperties = appProps.readProps("version.properties");
//Enumeration aEnuKeys = appProperties.keys();
//while (aEnuKeys.hasMoreElements()) {
//	String key = (String) aEnuKeys.nextElement();
//	String value = appProperties.getProperty(key);
//	System.out.println(key + ": " + value);
//}
//Enumeration vEnuKeys = versionProperties.keys();
//while (vEnuKeys.hasMoreElements()) {
//	String key = (String) vEnuKeys.nextElement();
//	String value = appProperties.getProperty(key);
//	System.out.println(key + ": " + value);
//}
//appProps.readProps("version.properties");