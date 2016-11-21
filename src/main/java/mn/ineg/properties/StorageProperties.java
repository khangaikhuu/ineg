/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.properties;

/**
 *
 * @author developer
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("properties")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    public final String uploadingdir = "/Users/developer/upload";

    private String location = uploadingdir;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
