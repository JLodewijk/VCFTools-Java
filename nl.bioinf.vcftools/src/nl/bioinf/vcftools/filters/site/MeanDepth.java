/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * Check if meandepth is between threshold given by the user
 * @author mhroelfes <marcoroelfes@gmail.com>
 * 
 */
public class MeanDepth extends AbstractSiteFilter {
     /**
     * Check if meandepth is between threshold given by the user
     * @param vcfLine
     * @param settings
     * @return
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        
        int genoNum = vcfLine.getGenotypeNumber();
        double genoDept = 0;
        // calculate total depth
        for (int i = 0; i < genoNum; i++) {
            VcfGenotype genotype = vcfLine.getGenotype(i);
            genoDept = genoDept + genotype.getDp();
        }
        //check if depth is between range
        return settings.getMinMeanDp() <= ((double) genoDept / (double) vcfLine.getGenotypeNumber()) && settings.getMaxMeanDp() >= ((double) genoDept / (double) vcfLine.getGenotypeNumber());

    }

}
