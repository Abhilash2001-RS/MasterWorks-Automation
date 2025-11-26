package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvironmentHelper extends BaseFramework {

    static Properties properties;
    private static final Logger logger = LogManager.getLogger(EnvironmentHelper.class);

    public static Properties loadProperties(){
        if(properties != null)
            return properties;

        Properties prop = getProperties();
        properties = prop;
        return prop;
    }


    public static String getPropertyValue(String propertyName){
        if(properties == null)
            properties = loadProperties();

        return getProperties().getProperty(propertyName);
    }

    /**
     * Get Properties.
     *
     * @return Properties.
     */
    public static Properties getProperties(){
        String rootPath = Paths.get(userDir, Constants.CONFIG_PROPERTIES_PATH).toString();

        Properties props = new Properties();
        try{
            props.load(new FileInputStream(rootPath));
        }catch (Exception e){
            logger.error("Error while getting properties" + e.getMessage());
        }

        return props;
    }
}
