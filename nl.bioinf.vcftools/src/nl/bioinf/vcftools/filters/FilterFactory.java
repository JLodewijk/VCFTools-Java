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
import nl.bioinf.vcftools.Settings;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterFactory {
    private Settings settings;
    private ArrayList<AbstractSimpleFilter> simpleFilters;
    
    public FilterFactory(Settings settings) {
        this.settings = settings;
        this.createSimpleFilters();
    }
    
    public void createSimpleFilters() {
        this.simpleFilters = new ArrayList<>();
        if (!this.settings.getChr().isEmpty()) { this.simpleFilters.add(new IncludeChromosome()); }
        if (!this.settings.getNotChr().isEmpty()) { this.simpleFilters.add(new ExcludeChromosome()); }
        if (!this.settings.getSnp().isEmpty()) { this.simpleFilters.add(new IncludeSnp()); }
        if (!this.settings.getPositions().isEmpty()) { this.simpleFilters.add(new IncludePositions()); }
        if (this.settings.getKeepInfo() != null) { this.simpleFilters.add(new KeepInfo()); }
        if (this.settings.isKeepIndels() != null) {
            if (this.settings.isKeepIndels() == true) { this.simpleFilters.add(new KeepIndels()); }
            if (this.settings.isKeepIndels() == false) { this.simpleFilters.add(new RemoveIndels()); }
        }
        if (!this.settings.getBed().isEmpty()) { /* bed incl chromosome implementation */ }
        if (!this.settings.getExludeBed().isEmpty()) { /* bed incl chromosome implementation */ }
        if (this.settings.isRemoveFilteredAll() != null) {
            if (this.settings.isRemoveFilteredAll() == true) { this.simpleFilters.add(new RemoveFiltered()); }
        }
        if (!this.settings.getRemoveFiltered().isEmpty()) { this.simpleFilters.add(new RemoveSpecificFilter()); }
        if (!this.settings.getKeepFiltered().isEmpty()) { this.simpleFilters.add(new KeepSpecificFilter()); }
        if (!this.settings.getRemoveInfo().isEmpty()) { this.simpleFilters.add(new RemoveInfo()); }
        if (!this.settings.getKeepInfo().isEmpty()) { this.simpleFilters.add(new KeepInfo()); }
        if (this.settings.getMinQ() != null) { this.simpleFilters.add(new MinimalQuality()); }
        if ((this.settings.getMinMeanDp() != null) && (this.settings.getMaxMeanDp() != null)) { this.simpleFilters.add(new MeanDepth()); }
        if ((this.settings.getMaf() != null) && (this.settings.getMaxMaf() != null)) { this.simpleFilters.add(new AlleleFrequencies()); }
        if ((this.settings.getNonRefAf() != null) && (this.settings.getMaxNonRefAf() != null)) { this.simpleFilters.add(new NonRefAlleleFrequencies()); }
        if ((this.settings.getMac() != null) && (this.settings.getMaxMac() != null)) {  }
        if ((this.settings.getNonRefAc() != null) && (this.settings.getMaxNonRefAc() != null)) { /* NonRefAlleleCount() */ }
        if (this.settings.getHwe() != null) { /* Hardy() */ }
        if (this.settings.getGeno() != null) { /* ?? */ }
        if (this.settings.getMaxMissingCount() != null) { this.simpleFilters.add(new MissingCount()); }
        if ((this.settings.getMinAlleles() != null) && (this.settings.getMaxAlleles() != null)) { this.simpleFilters.add(new MinorAlleleCount()); }
        if (this.settings.getThin() != null) { this.simpleFilters.add(new Thinning()); }
        // to do add mask
    }

    public ArrayList<AbstractSimpleFilter> getSimpleFilters() {
        return simpleFilters;
    }

    public void setSimpleFilters(ArrayList<AbstractSimpleFilter> simpleFilters) {
        this.simpleFilters = simpleFilters;
    }
    
    
    
}
