/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import nl.bioinf.vcftools.filters.site.KeepInfo;
import nl.bioinf.vcftools.filters.site.MinimalQuality;
import nl.bioinf.vcftools.filters.site.RemoveInfo;
import nl.bioinf.vcftools.filters.site.MinorAlleleCount;
import nl.bioinf.vcftools.filters.site.MeanDepth;
import nl.bioinf.vcftools.filters.site.RemoveSpecificFilter;
import nl.bioinf.vcftools.filters.site.AlleleFrequencies;
import nl.bioinf.vcftools.filters.site.KeepSpecificFilter;
import nl.bioinf.vcftools.filters.site.KeepIndels;
import nl.bioinf.vcftools.filters.site.RemoveIndels;
import nl.bioinf.vcftools.filters.site.ExcludeChromosome;
import nl.bioinf.vcftools.filters.site.Thinning;
import nl.bioinf.vcftools.filters.site.IncludePositions;
import nl.bioinf.vcftools.filters.site.IncludeChromosome;
import nl.bioinf.vcftools.filters.site.IncludeSnp;
import nl.bioinf.vcftools.filters.site.MissingCount;
import nl.bioinf.vcftools.filters.site.RemoveFiltered;
import nl.bioinf.vcftools.filters.site.NonRefAlleleFrequencies;
import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.genotype.Depth;
import nl.bioinf.vcftools.filters.genotype.Quality;
import nl.bioinf.vcftools.filters.individual.MaxIndividuals;
import nl.bioinf.vcftools.filters.individual.MeanIndvDp;
import nl.bioinf.vcftools.filters.individual.Phased;
import nl.bioinf.vcftools.filters.site.ExcludeSnp;
import nl.bioinf.vcftools.filters.site.Geno;
import nl.bioinf.vcftools.filters.site.Mask;
import nl.bioinf.vcftools.filters.site.RemoveUnphased;

/**
 *  This factory creates all the filters used in vcftools
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterFactory {
    private Settings settings;
    private List<AbstractSiteFilter> siteFilters;
    private List<AbstractGenotypeFilter> genotypeFilters;
    private List<AbstractIndividualFilter> individualFilters;
    
    /**
     * Default constructor
     * @param settings vcftools settings used to only build the filters that are needed
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public FilterFactory(Settings settings) {
        this.settings = settings;
        this.createSiteFilters();
        this.createGenotypeFilters();
        this.createIndividualFilters();
    }
    
    /**
     * Creates all the site filters
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    private void createSiteFilters() {
        this.siteFilters = new ArrayList<>();
        if ((!this.settings.getChr().isEmpty()) || (!this.settings.getBed().isEmpty())) { this.siteFilters.add(new IncludeChromosome()); }
        if ((!this.settings.getNotChr().isEmpty()) || (!this.settings.getExludeBed().isEmpty())) { this.siteFilters.add(new ExcludeChromosome()); }
        if (!this.settings.getSnp().isEmpty()) { this.siteFilters.add(new IncludeSnp()); }
        if (!this.settings.getExcludeSnp().isEmpty()) { this.siteFilters.add(new ExcludeSnp()); }
        if (!this.settings.getPositions().isEmpty()) { this.siteFilters.add(new IncludePositions()); }
        if (this.settings.getKeepInfo() != null) { this.siteFilters.add(new KeepInfo()); }
        if (this.settings.isKeepIndels() != null) {
            if (this.settings.isKeepIndels() == true) { this.siteFilters.add(new KeepIndels()); }
            if (this.settings.isKeepIndels() == false) { this.siteFilters.add(new RemoveIndels()); }
        }
        if ((this.settings.isRemoveFilteredAll() != null) && (this.settings.isRemoveFilteredAll() == true)) { this.siteFilters.add(new RemoveFiltered()); }
        if (!this.settings.getRemoveFiltered().isEmpty()) { this.siteFilters.add(new RemoveSpecificFilter()); }
        if (!this.settings.getKeepFiltered().isEmpty()) { this.siteFilters.add(new KeepSpecificFilter()); }
        if (!this.settings.getRemoveInfo().isEmpty()) { this.siteFilters.add(new RemoveInfo()); }
        if (!this.settings.getKeepInfo().isEmpty()) { this.siteFilters.add(new KeepInfo()); }
        if (this.settings.getMinQ() != null) { this.siteFilters.add(new MinimalQuality()); }
        if ((this.settings.getMinMeanDp() != null) && (this.settings.getMaxMeanDp() != null)) { this.siteFilters.add(new MeanDepth()); }
        if ((this.settings.getMaf() != null) && (this.settings.getMaxMaf() != null)) { this.siteFilters.add(new AlleleFrequencies()); }
        if ((this.settings.getNonRefAf() != null) && (this.settings.getMaxNonRefAf() != null)) { this.siteFilters.add(new NonRefAlleleFrequencies()); }
        if ((this.settings.getMac() != null) && (this.settings.getMaxMac() != null)) {  }
        if ((this.settings.getNonRefAc() != null) && (this.settings.getMaxNonRefAc() != null)) { /* NonRefAlleleCount() */ }
        if (this.settings.getHwe() != null) { /* Hardy() */ }
        if (this.settings.getGeno() != null && this.settings.getGeno() == 1)  { this.siteFilters.add(new Geno()); }
        if (this.settings.getMaxMissingCount() != null) { this.siteFilters.add(new MissingCount()); }
        if ((this.settings.getMinAlleles() != null) && (this.settings.getMaxAlleles() != null)) { this.siteFilters.add(new MinorAlleleCount()); }
        if (this.settings.getThin() != null) { this.siteFilters.add(new Thinning()); }
        if (!this.settings.getMask().isEmpty()) {this.siteFilters.add(new Mask());}
        if ((this.settings.isPhased() != null) && (this.settings.isPhased() == true)) { this.siteFilters.add(new RemoveUnphased()); }
    }
    
    /**
     * Creates all the genotype filters
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    private void createGenotypeFilters() {
        this.genotypeFilters = new ArrayList<>();
        if ((this.settings.getMinDp() != null) && (this.settings.getMaxDp() != null)) { this.genotypeFilters.add(new Depth()); }
        if (this.settings.getMinGq() != null) { this.genotypeFilters.add(new Quality()); }
    }
    
    /**
     * Creates all the individual filters
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    private void createIndividualFilters() {
        this.individualFilters = new ArrayList<>();
        
        if ((this.settings.isPhased() != null) && (this.settings.isPhased() == true)) { this.individualFilters.add(new Phased()); }
        if ((this.settings.getMinIndvMeanDp() != null) && (this.settings.getMaxIndvMeanDp() != null)) {this.individualFilters.add(new MeanIndvDp());}
        // insert extra filters HERE on this line and not below maxIndv because thats the final individual filter to be performed
        
        if (this.settings.getMaxIndv() != null) { this.individualFilters.add(new MaxIndividuals()); }
    }

    
    /**
     * Get the list of site filters
     * @return 
     */
    public List<AbstractSiteFilter> getSiteFilters() {
        return siteFilters;
    }

    /**
     * Get the list of genotype filters
     * @return 
     */
    public List<AbstractGenotypeFilter> getGenotypeFilters() {
        return genotypeFilters;
    } 
    
    /**
     * Get the list of individual filters
     * @return 
     */
    public List<AbstractIndividualFilter> getIndividualFilters() {
        return individualFilters;
    }
    
    
    
    
    
}
