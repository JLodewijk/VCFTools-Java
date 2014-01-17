/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import org.broadinstitute.variant.variantcontext.VariantContextBuilder;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfLineBuilder {
    private VariantContextBuilder vcb;

    /**
     * Default Constructor making empty builder object
     */
    public VcfLineBuilder() {
        this.vcb = new VariantContextBuilder();
    }
  
    /**
     * Constructor using VcfLine as base
     * @param vcfline 
     */
    public VcfLineBuilder(VcfLine vcfline) {
        this.vcb = new VariantContextBuilder(vcfline.getBroadinstituteVariantContext());
    }
    
    /**
     * Set the genotypes of this VcfLine
     */
    public void setGenotypes() {
        // to do insert/replace genotypes
    }
    
    /**
     * Create the new VcfLine
     * @return 
     */
    public VcfLine make() {
        return new VcfLine(vcb.make());
    }
    
    
    
}
