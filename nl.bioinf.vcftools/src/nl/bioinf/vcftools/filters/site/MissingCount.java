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
 * check if the missingcount is not too high
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class MissingCount extends AbstractSiteFilter{
     /**
     * check if the missingcount is not too high
     * @param vcfLine
     * @param settings
     * @return filter result
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //if missingcount is not higher than threshold return true, else false
        return vcfLine.getAttributeAsDouble("Dels") < settings.getMaxMissingCount();
    }
    

  
}
