/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import org.broadinstitute.variant.variantcontext.Genotype;
import org.broadinstitute.variant.variantcontext.GenotypesContext;
import org.broadinstitute.variant.variantcontext.VariantContext;

/**
 *
 * @author Marco Roelfes
 */
public class VcfReader {

    /**
     * @author Marco Roelfes
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        VcfReader read = new VcfReader();
        read.readVcfLine("/share/home/mhroelfes/Dropbox/Thema10/VCF/region.txt");
    }

    /**
     * Reads VCF line for line while file has next line
     *
     * @author Marco Roelfes
     * @param file
     * @return
     * @throws IOException
     */
    public String readVcfLine(String file) throws IOException {
        Vcf vcf = new Vcf(file);

        //while vcf file has next iteration get next iteration
        while (vcf.hasNextIter(file)){ 
            //get next line
            VariantContext nextLine = vcf.getNextIter(file);
            System.out.println(nextLine.getAlternateAlleles());
            VariantContext nextLine2 = vcf.getNextIter(file);
            System.out.println(nextLine2.getAllele("C"));
            VariantContext nextLine3 = vcf.getNextIter(file);
            Genotype genotypes = nextLine.getGenotype(0);
            System.out.println(genotypes.getAlleles());
            
            break;
        }
        
        return null;

    }
}
