/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.bioinf.vcftools.filehandlers.MaskFileReader;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;

/**
 *
 * @author Ashvin
 */
public class Mask extends AbstractSiteFilter {

        public boolean filter(VcfLine vcfLine, Settings settings) {
        /* call MaskFilereader to get maskMap(multiMap), False = -inverMask, True = inverted*/
        MaskFileReader mf = new MaskFileReader(settings.getMaskFile(),false);  
        
        ArrayList<Integer> digitList = new ArrayList<Integer>(); 
        Boolean test =true;
        /* check if  chromsome specified in mask file is also contained in the VCF file*/
        if(mf.getMaskMap().containsKey(vcfLine.getChr())){
          
          /*add all digits of the sequence to a ArrayList so value of each site can be determed easily*/
          for(Object digit:(Collection) mf.getMaskMap().get(vcfLine.getChr())){
             digitList.add(Integer.parseInt(String.valueOf(digit)));
          }
          /*if value of chromosome is higher than -min mask, VCF line is removed*/
          if(digitList.get(vcfLine.getPosition()-1) >= settings.getMaskMin()){
              test = false;}

        }
        return test;
        
        
        
        
        
        
        
        
    }

  
}
