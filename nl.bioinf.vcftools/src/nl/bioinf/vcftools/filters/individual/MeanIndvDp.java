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
 * Include individuals
 *
 * @author Ashvin Ponnudurai <as.ponnudurai@st.hanze.nl>
 */
public class MeanIndvDp extends AbstractIndividualFilter {

    /**
     * Includes a individual if the mean coverage is in between the specified
     * range
     *
     * @param settings
     * @param vcfHeader
     * @param filterDependencies
     * @return
     * @author Ashvin Ponnudurai <as.ponnudurai@st.hanze.nl>
     */
    @Override
    public List<Boolean> filter(Settings settings, VcfHeader vcfHeader, FilterDependencies filterDependencies) {
        List<Boolean> individualsLeft = new ArrayList<>();
        // loops through all meanDp of a individual
        for (double i : filterDependencies.getMeanDp()) {
            // check if meanDp of an individual is in between the specified range
            if ((i > settings.getMinIndvMeanDp()) && (i < settings.getMaxIndvMeanDp())) {
                // keep individual
                individualsLeft.add(true);
            } else {
                // remove individual
                individualsLeft.add(false);
            }
        }
        return individualsLeft;
    }
}
