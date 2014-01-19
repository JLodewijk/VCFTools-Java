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
 * checks if genotype quality is above threshold
 * @author Marco
 */
public class Quality extends AbstractGenotypeFilter{

    @Override
    public List<Boolean> filter(VcfLine vcfLine, Settings settings) {
        
        int genoNum = vcfLine.getGenotypeNumber();
        List<Boolean> keep = new ArrayList<Boolean>();
        //loops through genotypes in vcfLine
        for(int i=0; i<genoNum; i++){
           double gq = vcfLine.getGenotype(i).getGq();
           //checks if Quality is above thershold, if true add true to boolean list
           if(gq>settings.getMinGq()){
                keep.add(true);
            }else{
                keep.add(false);
            }
            
        }
        return keep;
    }
        
    
}
