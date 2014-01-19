/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import java.util.Collection;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class ExcludeChromosome extends AbstractSiteFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        // chr input implementation (gets priority over the bed file)
        
        
        if (settings.getNotChr().containsKey(vcfLine.getChr())) {
            // loop trough list of positions in chromosome
            for (Object i:(Collection) settings.getNotChr().get(vcfLine.getChr())) {
                // if value is null the whole chromosome is accepted
                if (i == null) { return false; }
                // change positions collection item to list
                List<Integer> il = (List) i;
                // look for specific positions that are passed on
                if((vcfLine.getPosition() >= (Integer) il.get(0)) && (vcfLine.getPosition() <= (Integer) il.get(1))) { return false; }          
            }
        }
        
        // bed implementation 
        if (settings.getExludeBed().containsKey(vcfLine.getChr())) {
            // loop trough list of positions in chromosome
            for (Object i:(Collection) settings.getExludeBed().get(vcfLine.getChr())) {
                // change positions collection item to list
                List<Integer> il = (List) i;
                // look for specific positions that are passed on
                if((vcfLine.getPosition() >= (Integer) il.get(0)) && (vcfLine.getPosition() <= (Integer) il.get(1))) { return false; }          
            }
        }
        return true;
    }
    
}
