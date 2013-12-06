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

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class Settings {

    private static Settings instance;
    private SettingsBasic settingsBasic;
    private SettingsSiteFilters settingsSiteFilters;
    private SettingsIndividualFilters settingsIndividualFilters;

    /**
     * Constructor for the settings class
     */
    public Settings() {
        settingsBasic = new SettingsBasic();
        settingsSiteFilters = new SettingsSiteFilters();
        settingsIndividualFilters = new SettingsIndividualFilters();
        try {
            this.load();
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Load settings files into memory
     * @throws IOException
     */
    public void load() throws IOException {

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

    public SettingsBasic getSettingsBasic() {
        return settingsBasic;
    }

    public SettingsSiteFilters getSettingsSiteFilters() {
        return settingsSiteFilters;
    }

    public SettingsIndividualFilters getSettingsIndividualFilters() {
        return settingsIndividualFilters;
    }

}
