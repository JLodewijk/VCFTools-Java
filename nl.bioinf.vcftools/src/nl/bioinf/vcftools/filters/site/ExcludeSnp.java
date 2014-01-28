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
 * This class can exclude a specific snp on their ID, returns false if line has to be removed.
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class ExcludeSnp extends AbstractSiteFilter{

    /**
     * This class can exclude a specific snp on their ID, returns false if line has to be removed.
     * @param vcfLine
     * @param settings
     * @return boolean, true is keeping a site
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //checks if vcfline has a ID
        if(vcfLine.getId() != null){
            //returns false if the vcfline Id is given by the user
            return !settings.getExcludeSnp().contains(vcfLine.getId());
        } else{
            return false;
        }
    }
    
}
