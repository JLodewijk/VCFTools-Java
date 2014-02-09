/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mhroelfes
 * @author Sergio Bondietti <sergio@bondietti.nl>
 * @author aponnudurai
 */
public class VcfTools {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cli cli = null;
        try {
            cli = new Cli(args);
        } catch (Exception ex) {
            Logger.getLogger(VcfTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        Settings settings = cli.getSettings();
        try {
            VcfProcessor vcfReader = new VcfProcessor(settings);
        } catch (IOException ex) {
            Logger.getLogger(VcfTools.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
