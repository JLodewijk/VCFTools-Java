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
 * excludes SNPs based on position
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class ExcludePositions extends AbstractSiteFilter {

    /**
     * excludes SNPs based on position
     * @param vcfLine
     * @param settings
     * @return boolean, true is keeping a site
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //checks if postion is given by the user
        return !settings.containsExcludePositions(vcfLine.getChr(), vcfLine.getPosition());

    }
}
