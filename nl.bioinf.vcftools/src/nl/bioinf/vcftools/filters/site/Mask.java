/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;


import java.util.ArrayList;
import java.util.Collection;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;

/**
 *
 * Check if sites of a chromosome can be excluded based on the mask file
 * @author Ashvin <as.ponnudurai@st.hanze.nl>
 */
public class Mask extends AbstractSiteFilter {

        @Override
        public boolean filter(VcfLine vcfLine, Settings settings) {

        
        ArrayList<Integer> digitList = new ArrayList<>(); 
        Boolean test =true;
        //check if  chromsome specified in mask file is also contained in the VCF file
        if(settings.getMask().containsKey(vcfLine.getChr())){

          //add all digits of the sequence to a ArrayList so value of each site can be determed easily
            for(Object digit:(Collection) settings.getMask().get(vcfLine.getChr())){
             digitList.add(Integer.parseInt(String.valueOf(digit)));
          }
          //if value of chromosome is higher than -min mask, VCF line is removed
          if(digitList.get(vcfLine.getPosition()-1) > settings.getMaskMin()){
              test = false;}
        }
        return test;
        
        
        
        
        
        
        
        
    }

  
}
