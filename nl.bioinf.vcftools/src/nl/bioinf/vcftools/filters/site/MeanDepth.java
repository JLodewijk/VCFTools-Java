/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSimpleFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class MeanDepth extends AbstractSimpleFilter {

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return settings.getMinMeanDp() >= ((double)vcfLine.getDp()/(double)vcfLine.getGenotypeNumber())  && settings.getMaxMeanDp() <= ((double)vcfLine.getDp()/(double)vcfLine.getGenotypeNumber());
    }
    
}
