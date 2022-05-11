package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    static Properties properties;


    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("testEnv.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String readProperty(String key){
        return properties.getProperty(key);
    }



}
