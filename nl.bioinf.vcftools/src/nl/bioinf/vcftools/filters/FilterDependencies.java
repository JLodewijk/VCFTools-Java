/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *  Collects data of the individual for filter use
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterDependencies {
    private List<Integer> totalDp;
    private List<Double> meanDp;
    private List<Boolean> Phased;
    private int siteCount;
    
    
    /**
     * Default constructor
     */
    public FilterDependencies() {
        this.totalDp = new ArrayList<>();
        this.meanDp = new ArrayList<>();
        this.Phased = new ArrayList<>();
        this.siteCount = 0;
    }
             
    /**
     * Collect dependencies from a VcfLine object
     * @param vcfLine 
     */
    public void collectDependencies(VcfLine vcfLine) {
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i=0;i<genoNum;i++) {
            VcfGenotype genotype = vcfLine.getGenotype(i);           
            // If first collection of data then prefil datasets
            if (this.siteCount == 0) {
                this.totalDp.set(i, 0);
                this.Phased.set(i, false);
            }        
            // Store depths total
            this.totalDp.set(i, (this.totalDp.get(i) + genotype.getDp()));
            // When we find a phased the whole individual is one totally unphased and can be kept
            if (genotype.isPhased() == true) { this.Phased.set(i, true); }    
        }
        // The original vcftools also calculates size when genotype is empty so one combined total counter will be okay
        this.siteCount++;
    }
    
    /**
     * Calculate dependencies after all collecting is finished
     */
    public void calculateDependencies() {
        int index = 0;
        for (Integer i : this.totalDp) {
            this.meanDp.set(index, ((double)i / (double)this.siteCount));
            index++;
        }
    }

    /**
     * Get a list of meanDP per individual
     * @return 
     */
    public List<Double> getMeanDp() {
        return meanDp;
    }
    
    /**
     * Get if there is phased data per individual, if its all unphased it returns false
     * @return 
     */
    public List<Boolean> getPhased() {
        return Phased;
    } 
    
}
