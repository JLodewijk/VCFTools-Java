/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.VariantContext;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfLine {

    private VariantContext vc;

    /**
     * Constructor using GATK VariantContext.
     * 
     * @param vc 
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    VcfLine(VariantContext vc) {
        this.vc = vc;
    }

    @Override
    public String toString() {
        return "VcfLine{" + "vc=" + vc + '}';
    }
    
    /**
     * Get the identifier of the SNP of the line.
     * 
     * @return Identifier
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getId() {
        return vc.getID();
    }
    
    /**
     * Get the chromosome of the SNP of the line.
     * 
     * @return Chromosome
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getChr() {
        return vc.getChr();
    }
    
    /**
     * Get the reference of the SNP of the line.
     * 
     * @return Allele
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getReferenceAllele() {
        return vc.getReference().toString();
    }
    
    /**
     * Get the alt alleles of the SNP of the line.
     * 
     * @return Alleles
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public List<String> getAltAlleles() {
        // convert list of objects to list of strings
        List<Allele> allelesObjects = this.vc.getAlternateAlleles(); 
        List<String> allelesStrings = new ArrayList<String>();
        for (Object object : allelesObjects) {
            allelesStrings.add(object != null ? object.toString() : null);
        }
        // Return list of strings
        return allelesStrings;
    }
    
    /**
     * Get the position of the SNP of the line.
     * 
     * @return Position
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getPosition() {
        return this.vc.getStart();
    }
    
    /**
     * Get the Quality of the SNP of the line.
     * 
     * @return Quality
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public double getQual() {
        return this.vc.getPhredScaledQual();
    }
    
     /**
     * Get the depth.
     * 
     * @return depth
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getDp() {
        return this.vc.getAttributeAsInt("DP", 0);
    }
    
     /**
     * Get the number of genotypes.
     * 
     * @return Number of genotypes
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getGenotypeNumber() {
        return vc.getGenotypes().size();
    }

    /**
     * Get if an indel is present.
     *
     * @return boolean
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public boolean getIndel() {
	return vc.isIndel();
    }

    /**
     * Get if it is not filtered.
     *
     * @return boolean
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public boolean getNotFiltered() {
	return vc.isNotFiltered();
    }

    /**
     * Get the specific filter status.
     *
     * @return Set<String> containing the filter status.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public Set<String> getSpecificFilter() {
	return vc.getFilters();
    }
    
    /**
     * Get the amount of allels.
     *
     * @return int
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public int getNAllels() {
        return vc.getNAlleles();
    }
    
    /**
     * Get the minor allele count.
     *
     * @return double
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public double getMinorAlleleCount() {
        return (double) vc.getAttribute("AC");
    }
    
    /**
     * Get an attribute as boolean of the SNP of the line.
     * 
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public boolean getAttributeAsBoolean(String attribute) {
        return this.vc.getAttributeAsBoolean(attribute, false);
    }
    
    /**
     * Get an attribute as double of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public double getAttributeAsDouble(String attribute) {
        return this.vc.getAttributeAsDouble(attribute, 0.0);
    }
    /**
     * Get an attribute as int of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return  Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getAttributeAsInt(String attribute) {
        return this.vc.getAttributeAsInt(attribute, 0);
    }
    
    /**
     * Get an attribute as string of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getAttributeAsString(String attribute) {
        return this.vc.getAttributeAsString(attribute, null);
    }
    
    /**
     * Get the genotype using the specified index number
     * @param index
     * @return 
     */
    public VcfGenotype getGenotype(int index) {
        return new VcfGenotype(this.vc.getGenotype(index));
    }
     

    
}
