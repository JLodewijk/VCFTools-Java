/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

/**
 *
 * @author mhroelfes
 */
public class NlBioinfVcftools {
    private Settings settings;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
      
      Cli c = new Cli();
      c.parse(args);
//       settings= c.getSettings();
      
//      System.out.println(settings);
//       System.out.println(Settings.getInstance().getInputFile());
  }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
  
}
