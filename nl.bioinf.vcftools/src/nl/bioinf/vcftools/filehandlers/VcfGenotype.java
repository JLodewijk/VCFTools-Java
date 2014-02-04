/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.Genotype;
import org.broadinstitute.variant.variantcontext.GenotypeBuilder;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfGenotype {
    private Genotype gt;

    /**
     * Constructor using GATK Genotype.
     * 
     * @param genotype 
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public VcfGenotype(Genotype genotype) {
        this.gt = genotype;
    }
    
    
    /**
     * Get original GATK Genotype for internal use
     * @return 
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public Genotype getBroadinstituteGenotype() {
        return this.gt;
    } 
    
    
    /**
     * Get the oldAlleles.
     * 
     * @return Alleles 
     * @author Sergio Bondietti <sergio@bondietti.nl>
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
     * Clear all the alleles of this genotype
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void clearAlleles () {
        // create genotype builder using current GATK genotype object and make datasets
        GenotypeBuilder gtb = new GenotypeBuilder(this.gt);
        List<Allele> oldAlleles = this.gt.getAlleles();
        List<Allele> newAlleles = new ArrayList<>();
        // for each old allele element create an empty new one
        for (Allele i : oldAlleles) {
            newAlleles.add(Allele.NO_CALL);
        }
        // set the new alleles in genotypebuilder object and overwrite old genotype object with new one
        gtb.alleles(newAlleles);
        this.gt = gtb.make();
    }
    
    /**
     * Get allele depth field.
     * 
     * @return allele depth
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int[] getAd() {
        return this.gt.getAD();
    }
    
    /**
     * Get depth field.
     * 
     * @return depth
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getDp () {
        return this.gt.getDP();
    }
    
    /**
     * Get quality field.
     * 
     * @return quality
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getGq () {
        return this.gt.getGQ();
    }
    
    /**
     * Get pl field.
     * 
     * @return pl
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int[] getPl() {
        return this.gt.getPL();
    }
    
    /**
     * Get ploidy.
     * 
     * @return ploidy number
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getPloidy() {
        return this.gt.getPloidy();
    }
    
    /**
     * Check if phased.
     * 
     * @return true when phased, false when not.
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public boolean isPhased() {
        return this.gt.isPhased();
    }
     /**
     * Check if there is any missing data
     * 
     * @return true when there is missing false, true when not.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public boolean isCorrectGenotype(){
        return !this.gt.isNonInformative();
    }
    
    /**
     * Check if genotype is called
     * @return true when is called
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public boolean isCalled() {
        return this.gt.isCalled();
    }
    
    
    
}
