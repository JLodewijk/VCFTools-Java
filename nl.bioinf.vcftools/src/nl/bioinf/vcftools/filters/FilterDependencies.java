/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.HashMap;
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *  Collects data of the individual for filter use
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterDependencies {
    private HashMap<Integer, Integer> totalDp;
    private HashMap<Integer, Double> meanDp;
    private HashMap<Integer, Boolean> Phased;
    private int siteCount;
    
    
    /**
     * Default constructor
     */
    public FilterDependencies() {
        this.totalDp = new HashMap<>();
        this.meanDp = new HashMap<>();
        this.Phased = new HashMap<>();
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
                this.totalDp.put(i, 0);
                this.Phased.put(i, false);
            }        
            // Store depths total
            this.totalDp.put(i, (this.totalDp.get(i) + genotype.getDp()));
            // When we find a phased the whole individual is one totally unphased and can be kept
            if (genotype.isPhased() == true) { this.Phased.put(i, true); }    
        }
        // The original vcftools also calculates size when genotype is empty so one combined total counter will be okay
        this.siteCount++;
    }
    
    /**
     * Calculate dependencies after all collecting is finished
     */
    public void calculateDependencies() {
        for (Integer key : this.totalDp.keySet()) {
            this.meanDp.put(key, ((double)this.totalDp.get(key) / (double)this.siteCount));
        }
    }

    /**
     * Get a list of meanDP per individual
     * @return 
     */
    public HashMap<Integer, Double> getMeanDp() {
        return meanDp;
    }
    
    /**
     * Get if there is phased data per individual, if its all unphased it returns false
     * @return 
     */
    public HashMap<Integer, Boolean> getPhased() {
        return Phased;
    } 
    
}
