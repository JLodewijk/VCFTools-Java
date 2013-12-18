/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

/**
 *
 * @author aponnudurai
 */
public class FilterHandler {

    private VcfLine vcfLine; 
    private Settings settings;
    
    public FilterHandler(Settings settings, VcfLine vcfLine) {
        this.vcfLine = vcfLine;
        this.settings = settings;
        
    }
    
    public boolean performFilters() {
        // perform filters
        SiteFilters sf = new SiteFilters();
        if(settings.getChr() != null){
//        sf.Chromosome(null, settings.getChr(), true);
        }
        if(settings.getNotChr() != null){
//        sf.Chromosome(null, settings.getNotChr(), false);
        }
        
        if(settings.getFromBp() != 0 && settings.getToBp() != 0){
        sf.Bp(null, settings.getToBp(), settings.getFromBp());
        }
        
        if(Double.compare(settings.getMinQ(), Double.NaN) == 0){
        
        
        }
        
        
        
        
        
        
        return true;
    }
    
    
    
}
