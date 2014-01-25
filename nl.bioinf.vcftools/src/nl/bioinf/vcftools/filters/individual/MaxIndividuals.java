/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.individual;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfHeader;
import nl.bioinf.vcftools.filters.AbstractIndividualFilter;
import nl.bioinf.vcftools.filters.FilterDependencies;

/**
 * Maximum individuals filter 
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class MaxIndividuals extends AbstractIndividualFilter {

    /**
     * Return a list of randomly kept individuals (depending on the max individuals count)
     * @param settings
     * @param vcfHeader
     * @param filterDependencies
     * @return 
     */
    @Override
    public List<Boolean> filter(Settings settings, VcfHeader vcfHeader, FilterDependencies filterDependencies) {
        boolean breakout = false;
        List<Boolean> individualsLeft = new ArrayList<>();

        // If max indv is 0 then just put all items to false
        if (settings.getMaxIndv() <= 0) {
            for (Boolean i : filterDependencies.getIndividualsLeft()) {
                individualsLeft.add(false);
            }
        } else {
            individualsLeft = filterDependencies.getIndividualsLeft();
            // else we loop untill we randomly excluded individuals till we are at the max individuals
            while (breakout == false) {
                // Create a list of index numbers of individuals that are left
                int index = 0;
                List<Integer> leftIndividualsIndexes = new ArrayList<>();
                for (Boolean left : individualsLeft) {
                    if (left == true) {
                        leftIndividualsIndexes.add(index);
                    }
                    index++;
                }
                // If the list of indexes is longer than the max individuals wanted 
                if (leftIndividualsIndexes.size() > settings.getMaxIndv()) {
                    // pick a random individual to remove
                    individualsLeft.set((int) (Math.random() * leftIndividualsIndexes.size()), false);
                } // if list of indexes is shorter then break out of the loop
                else {
                    breakout = true;
                }
            }
        }
        return individualsLeft;
    }

}
