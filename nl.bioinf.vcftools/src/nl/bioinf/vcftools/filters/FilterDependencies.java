/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.HashMap;
import nl.bioinf.vcftools.handlers.VcfGenotype;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class FilterDependencies {
    private HashMap<Integer, Integer> dp;
    private HashMap<Integer, Integer> count;
    
    
    /**
     * Default constructor
     */
    public FilterDependencies() {
        this.dp = new HashMap<>();
        this.count = new HashMap<>();
    }
        
        
    /**
     * Collect dependencies of vcfLine
     * @param vcfLine 
     */
    public void collectDependencies(VcfLine vcfLine) {
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i=0;i<genoNum;i++) {
            VcfGenotype genotype = vcfLine.getGenotype(i);
            
            if (this.dp.containsKey(i)) { this.dp.put(i, (this.dp.get(i) + genotype.getDp())); }
            else { this.dp.put(i, genotype.getDp()); }
            
            if (this.count.containsKey(i)) { this.count.put(i, (this.count.get(i) + 1)); }
            else { this.count.put(i, 1); }
            //System.out.println(vcfLine.getGenotype(i));
        }
    }
    
    /**
     * Calculate dependencies after all collecting is finished
     */
    public void calculateDependencies() {
        System.out.println(this.dp);
        System.out.println(this.count);
    }
    
}
