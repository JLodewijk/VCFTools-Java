/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.GenotypeBuilder;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfGenotypeBuilder {
    private GenotypeBuilder gtb;
    private VcfGenotype gt;


    /**
     * Constructor using VcfGenotype as base
     * @param genotype 
     */
    public VcfGenotypeBuilder(VcfGenotype genotype) {
        this.gt = genotype;
        this.gtb = new GenotypeBuilder(gt.getBroadinstituteGenotype());
    }
    
    public void clearAlleles () {
        List<String> oldAlleles = this.gt.getAlleles();
        List<Allele> newAlleles = new ArrayList<>();
        for (String i : oldAlleles) {
            newAlleles.add(Allele.NO_CALL);
        }
        this.gtb.alleles(newAlleles);
    }
    
    public VcfGenotype make() {
        return new VcfGenotype(this.gtb.make());
    }
    
    
    
    
    
}
