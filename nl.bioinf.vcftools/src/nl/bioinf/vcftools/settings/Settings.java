/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.settings;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class Settings {

    private static Settings instance;
    private String configFile;
    private XMLConfiguration config;

    /**
     * Constructor for the settings class
     */
    public Settings() {
        this.configFile = "defaultConfig.xml";
        this.load();
    }
    
    public Settings(String configFile) {
        this.configFile = configFile;
        this.load();
    }    
    /**
     * Load settings files into memory
     */
    public void load() {
        try {
            this.config = new XMLConfiguration("tables.xml");
        } catch (ConfigurationException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Save settings from memory to file
     * @throws IOException
     */
    public void save() throws IOException {
       
    }

    public static Settings getInstance() {
        return instance;
    }

}
