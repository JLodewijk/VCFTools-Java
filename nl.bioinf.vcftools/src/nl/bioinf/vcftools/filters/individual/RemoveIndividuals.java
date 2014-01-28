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
 * Removes an individual if given by the user, else include an individual
 *
 * @author Marco
 */
public class RemoveIndividuals extends AbstractIndividualFilter {

    /**
     * Removes an individual if given by the user, else include an individual
     *
     * @param settings
     * @param vcfHeader
     * @param filterDependencies
     * @return
     * @author Marco Roelfes
     */
    @Override
    public List<Boolean> filter(Settings settings, VcfHeader vcfHeader, FilterDependencies filterDependencies) {
        List<Boolean> individualsLeft = new ArrayList<>();
        //loops to all individuals in header
        for (String indv : vcfHeader.getGenotypeSamples()) {
            //if list of individuals to keep contains the individual in the header.
            if (settings.getKeepIndv().contains(indv)) {
                //remove individual
                individualsLeft.add(false);
            } else {
                //include individual
                individualsLeft.add(true);
            }
        }
        return individualsLeft;
    }
}
