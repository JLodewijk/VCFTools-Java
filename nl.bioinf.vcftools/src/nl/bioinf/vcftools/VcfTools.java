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
     */
    public static void main(String[] args) throws ParseException {
    // TODO code application logic here

          Cli cli = new Cli(args);      
          Settings settings = cli.getSettings();
          VcfReader vcfReader = new VcfReader(settings); 
          
//        System.out.println("bleep");
//        System.out.println(settings.getInputFile());
//       System.out.println(Settings.getInstance().getInputFile());
    }

}
