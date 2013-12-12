/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.VariantContext;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfLine {

    private VariantContext vc;

    VcfLine(VariantContext vc) {
        this.vc = vc;
    }
    
    /**
     * Get the identifier of the SNP of the line.
     * @return Identifier
     */
    public String getId() {
        return vc.getID();
    }
    
    /**
     * Get the chromosome of the SNP of the line.
     * @return Chromosome
     */
    public String getChr() {
        return vc.getChr();
    }
    
    /**
     * Get the reference of the SNP of the line.
     * @return Allele
     */
    public String getReferenceAllele() {
        return vc.getReference().toString();
    }
    
    /**
     * Get the alt alleles of the SNP of the line.
     * @return Alleles
     */
    public List<String> getAltAlleles() {
        // convert list of objects to list of strings
        List<Allele> allelesObjects = this.vc.getAlternateAlleles(); 
        List<String> allelesStrings = new ArrayList<String>();
        for (Object object : allelesObjects) {
            allelesStrings.add(object != null ? object.toString() : null);
        }
        // return list of strings
        return allelesStrings;
    }
    
    /**
     * Get the position of the SNP of the line.
     * @return Position
     */
    public int getPosition() {
        return this.vc.getStart();
    }
    
    /**
     * Get the Quality of the SNP of the line.
     * @return Quality
     */
    public double getQual() {
        return this.vc.getPhredScaledQual();
    }
    
    /**
     * Get an attribute as boolean of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     */
    public boolean getAttributeAsBoolean(String attribute) {
        return this.vc.getAttributeAsBoolean(attribute, false);
    }
    
    /**
     * Get an attribute as double of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     */
    public double getAttributeAsDouble(String attribute) {
        return this.vc.getAttributeAsDouble(attribute, 0.0);
    }
    /**
     * Get an attribute as int of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return  Attribute value
     */
    public int getAttributeAsInt(String attribute) {
        return this.vc.getAttributeAsInt(attribute, 0);
    }
    
    /**
     * Get an attribute as string of the SNP of the line.
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     */
    public String getAttributeAsString(String attribute) {
        return this.vc.getAttributeAsString(attribute, null);
    }
    
}
