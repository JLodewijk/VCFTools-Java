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
 *
 * @author Marco
 */
public class Depth extends AbstractGenotypeFilter{
    
    @Override
    public List<Boolean> filter(VcfLine vcfLine, Settings settings) {
        int number = vcfLine.getGenotypeNumber();
        List<Boolean> keep = new ArrayList<Boolean>();
        for(int i=1; i<number++; i++){
            double dp = vcfLine.getGenotype(i).getDp();
            if((dp>minDp && dp<maxDp)){
                keep.add(true);
            } else{
                keep.add(false);
            }
        }
        return keep;
    }
    
}
