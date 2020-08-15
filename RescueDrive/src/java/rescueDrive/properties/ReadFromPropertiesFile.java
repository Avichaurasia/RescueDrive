/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rescueDrive.properties;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author User
 */
public class ReadFromPropertiesFile {

    Properties properties = null;
    InputStream is = null;

    public ReadFromPropertiesFile() {
        try {
            properties = new Properties();
            is = getClass().getResourceAsStream("configuration.properties");
            properties.load(is);
        } catch (Exception e) {
            System.out.println("unable to load file properties " + e);
        }
    }

    public String getUploadPath() {
        return properties.getProperty("uploadPath");
    }

}
