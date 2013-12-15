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
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfReader {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        VcfReader read = new VcfReader();
        read.readVcfLine("C://Users/Marco/Dropbox/Thema10/VCF/region.txt");
    }

    /**
     * Reads VCF line for line while file has next line
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String readVcfLine(String file) throws IOException {
        Vcf vcf = new Vcf(file);

        //while vcf file has next iteration get next iteration
        while (vcf.hasNextIter()){ 
            //get next line
            VariantContext nextLine = vcf.getNextIterAsVariantContext();
            System.out.println(nextLine.getAlternateAlleles());
            VariantContext nextLine2 = vcf.getNextIterAsVariantContext();
            System.out.println(nextLine2.getAllele("C"));
            VariantContext nextLine3 = vcf.getNextIterAsVariantContext();
            Genotype genotypes = nextLine.getGenotype(0);
            System.out.println(genotypes.getAlleles());
            System.out.println(nextLine.getAttribute("AF").getClass().getName());
            String alleleFreq;
            alleleFreq = (String) nextLine.getAttribute("AF");
            float al = Float.valueOf(alleleFreq);
            System.out.println(al);
            
        }
        
        return null;

    }
}
