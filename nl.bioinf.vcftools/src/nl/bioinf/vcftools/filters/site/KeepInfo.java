/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * Check if info exists in vcfLine and rejects if not
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class KeepInfo extends AbstractSiteFilter {
     /**
     * Check if info exists in vcfLine and rejects if not
     * @param vcfLine
     * @param settings
     * @return filter result
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //loop through info which needs to be kept
        for (String i : settings.getKeepInfo()) {
            //checks if info exists
            if (vcfLine.getAttributeAsString(i) == null){
                return false;
            }
        }
        return true; 
        
    


    }


    
}
