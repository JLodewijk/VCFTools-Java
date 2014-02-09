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
 * check if keep info exists
 * @author mhroelfes <macoroelfes@gmail.com>
 */
public class RemoveInfo extends AbstractSiteFilter {
     /**
     * check if keep info exists
     * @param vcfLine
     * @param settings
     * @return filter result
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //loop through all info to be kept
        for (String i : settings.getKeepInfo()) {
           //if info exists return false
            if (vcfLine.getAttributeAsString(i) != null) {
                return false;
            }
        }
        return true;
    }

}
