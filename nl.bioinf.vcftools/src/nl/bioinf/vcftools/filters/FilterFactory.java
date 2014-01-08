/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

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
        if (this.settings.isKeepIndels() == true) { this.simpleFilters.add(new KeepIndels()); }
        if (this.settings.isKeepIndels() == false) { this.simpleFilters.add(new RemoveIndels()); }
        if (!this.settings.getBed().isEmpty()) { /* bed incl chromosome implementation */ }
        if (!this.settings.getExludeBed().isEmpty()) { /* bed incl chromosome implementation */ }
        if (this.settings.isRemoveFilteredAll() == true) { this.simpleFilters.add(new RemoveFiltered()); }
        if (!this.settings.getRemoveFiltered().isEmpty()) { this.simpleFilters.add(new RemoveSpecificFilter()); }
        if (!this.settings.getKeepFiltered().isEmpty()) { this.simpleFilters.add(new KeepSpecificFilter()); }
        if (!this.settings.getRemoveInfo().isEmpty()) { this.simpleFilters.add(new RemoveInfo()); }
        if (!this.settings.getKeepInfo().isEmpty()) { this.simpleFilters.add(new KeepInfo()); }
        if (this.settings.getMinQ() != null) { this.simpleFilters.add(new MinimalQuality()); }
        if ((this.settings.getMinMeanDp() != null) && (this.settings.getMaxMeanDp() != null)) { this.simpleFilters.add(new MeanDepth()); }
        // filter
        
        

    }

    public ArrayList<AbstractSimpleFilter> getSimpleFilters() {
        return simpleFilters;
    }

    public void setSimpleFilters(ArrayList<AbstractSimpleFilter> simpleFilters) {
        this.simpleFilters = simpleFilters;
    }
    
    
    
}
