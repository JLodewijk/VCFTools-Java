/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Genotype;
import org.broadinstitute.variant.variantcontext.VariantContextBuilder;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfLineBuilder {
    private VariantContextBuilder vcb;
 
    /**
     * Constructor using VcfLine as base
     * @param vcfline 
     */
    public VcfLineBuilder(VcfLine vcfline) {
        this.vcb = new VariantContextBuilder(vcfline.getBroadinstituteVariantContext());
    }
    
    /**
     * Set the genotypes of this VcfLine
     * @param genotypes
     */
    public void setGenotypes(List<VcfGenotype> genotypes) {
        List<Genotype> biGenotypes = new ArrayList<>();
        for (VcfGenotype i : genotypes) {
            biGenotypes.add(i.getBroadinstituteGenotype());
        }
        this.vcb.genotypes(biGenotypes);
    }
    
    /**
     * Create the new VcfLine
     * @return 
     */
    public VcfLine make() {
        return new VcfLine(vcb.make());
    }

    @Override
    public String toString() {
        return "VcfLineBuilder{" + "vcb=" + vcb + '}';
    }
    
    
    
}
