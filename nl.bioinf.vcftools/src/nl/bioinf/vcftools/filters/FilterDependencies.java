/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.ArrayList;

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
    private List<Boolean> phased;
    private List<Boolean> individualsLeft;
    private List<Integer> calledTotal;
    private List<Double> calledRatio;
    private int siteCount;
    
    
    /**
     * Default constructor
     */
    public FilterDependencies() {
        this.totalDp = new ArrayList<>();
        this.meanDp = new ArrayList<>();
        this.phased = new ArrayList<>();
        this.individualsLeft = new ArrayList<>();
        this.calledTotal = new ArrayList<>();
        this.calledRatio = new ArrayList<>();
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
                this.totalDp.add(0);
                this.calledTotal.add(0);
                this.phased.add(false);
            }        
            // Store depths total
            this.totalDp.set(i, (this.totalDp.get(i) + genotype.getDp()));
            // When we find a phased the whole individual is not totally unphased and can be kept
            if (genotype.isPhased() == true) { this.phased.set(i, true); }  
            // When genotype is called add to the total
            if (genotype.isCalled() == true) {  this.calledTotal.set(i, this.calledTotal.get(i) + 1); }     
        }
        // The original vcftools also calculates size when genotype is empty so one combined total counter will be okay
        this.siteCount++;
    }
    
    /**
     * Calculate dependencies after all collecting is finished
     */
    public void calculateDependencies() {
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
     * Get if there is phased data per individual, if its all unphased it returns false
     * @return 
     */
    public List<Boolean> getPhased() {
        return phased;
    } 

    /**
     * Get the list of call ratios for individuals
     * @return 
     */
    public List<Double> getCalledRatio() {
        return calledRatio;
    }  
    
    /**
     * Get the list of individuals left after previous filter steps
     * @return 
     */
    public List<Boolean> getIndividualsLeft() {
        return individualsLeft;
    }

    /**
     * Set the list of individuals left after previous filter steps
     * @param individualsLeft 
     */
    public void setIndividualsLeft(List<Boolean> individualsLeft) {
        this.individualsLeft = individualsLeft;
    }
    
    
    
}
