/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

import java.util.ArrayList;

import java.util.List;
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *  Collects data of the individual for filter use
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class StatisticsGenerator {
    // settings
    private Settings settings;
    
    // individual based data
    private List<Integer> totalDp;
    private List<Double> meanDp;
    private List<Integer> calledTotal;
    private List<Double> calledRatio;
    
    // site based data (to write to external files)
    private List<Double> freq; 
    
    // total amount of sites
    private int siteCount;
    
    
    /**
     * Default constructor
     */
    public StatisticsGenerator(Settings settings) {
        this.settings = settings;
        this.totalDp = new ArrayList<>();
        this.meanDp = new ArrayList<>();
        this.calledTotal = new ArrayList<>();
        this.calledRatio = new ArrayList<>();
        this.siteCount = 0;
    }
             
    /**
     * Collect statistics from a VcfLine object
     * @param vcfLine 
     */
    public void collectStatistics(VcfLine vcfLine) {
        
        /*
        Individual / Genotype data collection
        */
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i=0;i<genoNum;i++) {
            VcfGenotype genotype = vcfLine.getGenotype(i);           
            // If first collection of data then prefil datasets of the individuals
            if (this.siteCount == 0) {
                this.totalDp.add(0);
                this.calledTotal.add(0);
            }
            
            // Store depths total
            this.totalDp.set(i, (this.totalDp.get(i) + genotype.getDp()));
            // When genotype is called add to the total
            if (genotype.isCalled() == true) {  this.calledTotal.set(i, this.calledTotal.get(i) + 1); }     
        }
        
        /*
        Site data collection
        */
        
        // this.freq.add(blablabla) etc
        
        
        
        // The original vcftools also calculates things when genotype is empty so total count can be used in most cases
        this.siteCount++;
    }
    
    /**
     * Calculate Statistics (usually performed after iterating trough all sites for individual based statistics)
     */
    public void calculateStatistics() {
        for (Integer dp : this.totalDp) {
            this.meanDp.add((double)dp / (double)this.siteCount);  
        }
        for (Integer callCount : this.calledTotal) {
            this.calledRatio.add((double)callCount / (double)this.siteCount);
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
     * Get the list of call ratios for individuals
     * @return 
     */
    public List<Double> getCalledRatio() {
        return calledRatio;
    }  

    /**
     * Get a list of frequencies to write to a file
     * @return 
     */
    public List<Double> getFreq() {
        return freq;
    }
    

    
    
    
    
}
