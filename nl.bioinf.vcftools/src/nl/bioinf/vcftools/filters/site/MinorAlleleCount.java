/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * this class filters a vcf line on allele count, returns true if line has to be kept, returns false if line is rejected
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class MinorAlleleCount extends AbstractSiteFilter {
     /**
     * filters a vcf line on allele count, returns true if line has to be kept, returns false if line is rejected
     * @param vcfLine
     * @param settings
     * @return
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        Object valObj = vcfLine.getAttribute("AC");
        //System.out.println(valObj.getClass().getName());
        if (valObj instanceof String) {
            //handle single value
            int val = Integer.parseInt((String) valObj);
            return val < settings.getMaxMac() && val > settings.getMac();
        } else {
            boolean keep = true;
            //ArrayList value
            ArrayList<String> values = (ArrayList<String>) valObj;
            List<Integer> valuesInteger = new ArrayList<Integer>();
            //set String ArrayList to Double ArrayList            
            for (String str : values) {
                valuesInteger.add(str != null ? Integer.parseInt(str) : null);
            }
            //for every double in ArrayList check if between threshold
            for (double dValue : valuesInteger) {
                if (dValue < settings.getMaxMac() && dValue > settings.getMac()) {
                    keep =  true;
                } else {
                    keep=  false;
                    break;

                }
              
            }
            return keep;  
            //return if line has to be kept or not
           

        }
        
    }
    
}
