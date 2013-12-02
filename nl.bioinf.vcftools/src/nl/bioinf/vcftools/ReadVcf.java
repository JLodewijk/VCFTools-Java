/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;

/**
 *
 * @author Marco Roelfes
 */
public class ReadVcf {

    /**
     * @author Marco Roelfes
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ReadVcf read = new ReadVcf();
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
        while (vcf.hasNextIter(file)) {
            System.out.println(vcf.getNextIter(file));
        }
        
        return null;

    }
}
