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
        if (this.settings.getMinQ() != null) { this.simpleFilters.add(new MinimalQualityFilter()); }

    }

    public ArrayList<AbstractSimpleFilter> getSimpleFilters() {
        return simpleFilters;
    }

    public void setSimpleFilters(ArrayList<AbstractSimpleFilter> simpleFilters) {
        this.simpleFilters = simpleFilters;
    }
    
    
    
}
