/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        Cli cli = new Cli(args);
        Settings settings = cli.getSettings();
        try {
            VcfProcessor vcfReader = new VcfProcessor(settings);
        } catch (IOException ex) {
            Logger.getLogger(VcfTools.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
