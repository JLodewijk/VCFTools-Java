/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.util.List;
import org.broadinstitute.variant.vcf.VCFHeader;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfHeader {
    private VCFHeader header;

    /**
     * Constructor using GATK VCFHeader to initiate
     * @param header 
     */
    public VcfHeader(VCFHeader header) {
        this.header = header;
    }

    /**
     * Get original GATK VCFHeader for internal use
     * @return 
     */
    public VCFHeader getBroadinstituteVCFHeader() {
        return this.header;
    }

    /**
     * Get the genotype samples
     * @return 
     */
    public List<String> getGenotypeSamples() {
        return this.header.getGenotypeSamples();
    }
    
    /**
     * Get the number of genotype samples
     * @return 
     */
    public int getGenotypeSampleNumber() {
        return this.header.getNGenotypeSamples();
    }
    
    @Override
    public String toString() {
        return "VcfHeader{" + "header=" + header + '}';
    }
    
    
}
