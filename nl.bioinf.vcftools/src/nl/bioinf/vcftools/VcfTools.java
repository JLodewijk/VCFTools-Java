/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import org.apache.commons.cli.ParseException;

/**
 *
 * @author mhroelfes
 * @author Sergio Bondietti <sergio@bondietti.nl>
 * @author aponnudurai
 */
public class VcfTools {

    /**
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Settings settingstest = new Settings();
        
//        try {
//            settingstest.save("defaultConfig.xml");
//        } catch (IOException ex) {
//            Logger.getLogger(VcfTools.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        Cli cli = new Cli(args);
        Settings settings = cli.getSettings();
        System.out.println("bleep");
        
        VcfProcessor vcfReader = new VcfProcessor(settings);

//        System.out.println("bleep");
//        System.out.println(settings.getInputFile());
//       System.out.println(Settings.getInstance().getInputFile());
    }

}
