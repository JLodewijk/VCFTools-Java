/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.genotype;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filters.AbstractGenotypeFilter;

/**
 * Checks if genotype depth is between threshold given by the user
 * @author Marco
 */
public class Depth extends AbstractGenotypeFilter{
    
    @Override
    public List<Boolean> filter(VcfLine vcfLine, Settings settings) {
        int genoNum = vcfLine.getGenotypeNumber();
        List<Boolean> keep = new ArrayList<Boolean>();
        //loop through all genotypes
        for(int i=0; i<genoNum; i++){
            double dp = vcfLine.getGenotype(i).getDp();
            //checks if genotype depth is between threshold, if true add true to boolean list(keep genotype) 
            if((dp>settings.getMinDp()) && (dp<settings.getMaxDp())){
                keep.add(true);
            } else{
                keep.add(false);
            }
        }
        return keep;
    }
    
}
