/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.Genotype;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfGenotype {
    private Genotype gt;

    /**
     * Constructor using GATK Genotype.
     * @param genotype 
     */
    public VcfGenotype(Genotype genotype) {
        this.gt = genotype;
    }
    
    /**
     * Get the alleles.
     * @return Alleles 
     */
    public List<String> getAlleles() {
        // convert list of objects to list of strings
        List<Allele> allelesObjects = this.gt.getAlleles(); 
        List<String> allelesStrings = new ArrayList<String>();
        for (Object object : allelesObjects) {
            allelesStrings.add(object != null ? object.toString() : null);
        }
        // return list of strings
        return allelesStrings;
    }
    
    /**
     * Get allele depth field.
     * @return allele depth
     */
    public int[] getAd() {
        return this.gt.getAD();
    }
    
    /**
     * Get depth field.
     * @return depth
     */
    public int getDp () {
        return this.gt.getDP();
    }
    
    /**
     * Get quality field.
     * @return quality
     */
    public int getGq () {
        return this.gt.getGQ();
    }
    
    /**
     * Get pl field.
     * @return pl
     */
    public int[] getPl() {
        return this.gt.getPL();
    }
    
    /**
     * Get ploidy.
     * @return ploidy number
     */
    public int getPloidy() {
        return this.gt.getPloidy();
    }
    
    /**
     * Check if phased.
     * @return true when phased, false when not.
     */
    public boolean isPhased() {
        return this.gt.isPhased();
    }
    
    
    
}