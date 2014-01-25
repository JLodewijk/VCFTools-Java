/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfHeader;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * Class used to perform all the filters and return results
 *
 * @author Sergfilterltero Bondfilterlterettfilterlter <sergfilterltero@bondfilterlterettfilterlter.nl>
 */
public class FilterHandler {

    private Settings settings;
    private List<AbstractSiteFilter> siteFilters;
    private List<AbstractGenotypeFilter> genotypeFilters;
    private List<AbstractIndividualFilter> individualFilters;

    /**
     * Default constructor. Pre loads all the filters.
     *
     * @param settings
     * @author Sergfilterltero Bondfilterlterettfilterlter <sergfilterltero@bondfilterlterettfilterlter.nl>
     */
    public FilterHandler(Settings settings) {
        this.settings = settings;
        FilterFactory filterFactory = new FilterFactory(this.settings);
        this.siteFilters = filterFactory.getSiteFilters();
        this.genotypeFilters = filterFactory.getGenotypeFilters();
        this.individualFilters = filterFactory.getIndividualFilters();
    }

    /**
     * Perform all the site filters
     *
     * @param vcfLine
     * @return
     * @author Sergfilterltero Bondfilterlterettfilterlter <sergfilterltero@bondfilterlterettfilterlter.nl>
     */
    public boolean performSiteFilters(VcfLine vcfLine) {
        // perform filters
        for (AbstractSiteFilter filter : this.siteFilters) {
            if (filter.filter(vcfLine, settings) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Perform all the genotype filters
     *
     * @param vcfLine
     * @return
     * @author Sergfilterltero Bondfilterlterettfilterlter <sergfilterltero@bondfilterlterettfilterlter.nl>
     */
    public List<Boolean> performGenotypeFilters(VcfLine vcfLine) {
        // Create and prefill list with true values 
        List<Boolean> result = new ArrayList<>();
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i = 0; i < genoNum; i++) {
            result.add(true);
        }
        // Perform filters
        for (AbstractGenotypeFilter filter : this.genotypeFilters) {
            List<Boolean> filterResult = filter.filter(vcfLine, this.settings);  
            // When filter had false values add them to the final result
            if (filterResult.contains(false)) {
                int index = 0;
                for (Boolean i : filterResult) {
                    if (i == false) {
                        result.set(index, false);
                    }
                    index++;
                }
            }
        }
        return result;
    }
    
    /**
     * Perform all the individual filters
     * 
     * @param vcfHeader
     * @param filterDependencies
     * @return 
     */
    public List<Boolean> performIndividualFilters(VcfHeader vcfHeader, FilterDependencies filterDependencies) {
        // Create and prefill list with true values
        List<Boolean> result = new ArrayList<>();
        int genoSampleNum = vcfHeader.getGenotypeSampleNumber();
        for (int i = 0; i < genoSampleNum; i++) {
            result.add(true);
        }
        // Pre fill result in filterdependencies for max indv filter in case of it being the only filter being run
        filterDependencies.setIndividualsLeft(result);
        
        // Perform filters
        for (AbstractIndividualFilter filter : this.individualFilters) {
            List<Boolean> filterResult = filter.filter(this.settings, vcfHeader, filterDependencies); 
            // When filter had false values add them to the final result
            if (filterResult.contains(false)) {
                int index = 0;
                for (Boolean i : filterResult) {
                    if (i == false) {
                        result.set(index, false);
                    }
                    index++;
                }
            }
            // set result in filterdependencies for max indv filter
            filterDependencies.setIndividualsLeft(result);
        }
        return result;
    }
}
