/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.FilterFactory;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterHandler {
    private Settings settings;
    private ArrayList<AbstractSimpleFilter> simpleFilters;

    public FilterHandler(Settings settings) {
        this.settings = settings;
        FilterFactory filterFactory = new FilterFactory(this.settings);
        this.simpleFilters = filterFactory.getSimpleFilters();
        


    }

    public boolean performFilters(VcfLine vcfLine) {
        // perform filters
        
       for (AbstractSimpleFilter i : this.simpleFilters) {
            if (i.filter(vcfLine, settings) == false) {
                return false;
            }
        }
       return true;

    }

}
