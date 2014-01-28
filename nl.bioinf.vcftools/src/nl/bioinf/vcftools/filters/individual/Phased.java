/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.individual;

import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfHeader;
import nl.bioinf.vcftools.filters.AbstractIndividualFilter;
import nl.bioinf.vcftools.filters.FilterDependencies;
import nl.bioinf.vcftools.filters.site.RemovePhased;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class Phased extends AbstractIndividualFilter {
    /**
     * Filters any individuals that have their genotype unphased.
     *
     * @param settings
     * @param vcfHeader
     * @param filterDependencies
     * @return
     */
    @Override
    public List<Boolean> filter(Settings settings, VcfHeader vcfHeader, FilterDependencies filterDependencies) {
        if(filterDependencies.getPhased().contains(false)){
             RemovePhased r =  new RemovePhased();
             r.filter(null, settings);
        
        }
        return filterDependencies.getPhased();
    }
    
}
