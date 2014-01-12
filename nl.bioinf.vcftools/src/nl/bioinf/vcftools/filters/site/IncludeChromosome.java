/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import java.util.Collection;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSimpleFilter;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class IncludeChromosome extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        // chr input implementation (gets priority over the bed file)
        if (settings.getChr().containsKey(vcfLine.getChr())) {
            // loop trough list of positions in chromosome
            for (Object i:(Collection) settings.getChr().get(vcfLine.getChr())) {
                // if value is null the whole chromosome is accepted
                if (i == null) { return true; }
                // change positions collection item to list
                List<Integer> il = (List) i;
                // look for specific positions that are passed on
                if((vcfLine.getPosition() >= (Integer) il.get(0)) && (vcfLine.getPosition() <= (Integer) il.get(1))) { return true; }          
            }
        }
        // bed implementation 
        if (settings.getBed().containsKey(vcfLine.getChr())) {
            // loop trough list of positions in chromosome
            for (Object i:(Collection) settings.getBed().get(vcfLine.getChr())) {
                // change positions collection item to list
                List<Integer> il = (List) i;
                // look for specific positions that are passed on
                if((vcfLine.getPosition() >= (Integer) il.get(0)) && (vcfLine.getPosition() <= (Integer) il.get(1))) { return true; }          
            }
        }
        return false;
    }
}
