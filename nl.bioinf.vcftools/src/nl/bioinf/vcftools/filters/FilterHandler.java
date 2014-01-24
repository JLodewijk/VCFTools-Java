/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * Class used to perform all the filters and return conditions
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterHandler {

    private Settings settings;
    private List<AbstractSiteFilter> siteFilters;
    private List<AbstractGenotypeFilter> genotypeFilters;

    /**
     * Default constructor. Pre loads all the filters.
     *
     * @param settings
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public FilterHandler(Settings settings) {
        this.settings = settings;
        FilterFactory filterFactory = new FilterFactory(this.settings);
        this.siteFilters = filterFactory.getSiteFilters();
        this.genotypeFilters = filterFactory.getGenotypeFilters();
    }

    /**
     * Perform all the site filters
     *
     * @param vcfLine
     * @return
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public boolean performSiteFilters(VcfLine vcfLine) {
        // perform filters
        for (AbstractSiteFilter i : this.siteFilters) {
            if (i.filter(vcfLine, settings) == false) {
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
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public List<Boolean> performGenotypeFilters(VcfLine vcfLine) {
        // Create and prefull hashmap with true values 
        List<Boolean> result = new ArrayList<>();
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i = 0; i < genoNum; i++) {
            result.add(true);
        }
        // Perform filters
        for (AbstractGenotypeFilter i : this.genotypeFilters) {
            // Filter then Check if there are false values genotype result and change full result into false if so.
            List<Boolean> filterResult = i.filter(vcfLine, this.settings);
            int index = 0;
            if (filterResult.contains(false)) {
                for (Boolean fi : filterResult) {
                    if (fi == false) {
                        result.set(index, false);
                    }
                    index++;
                }
            }
        }
        return result;
    }
}
