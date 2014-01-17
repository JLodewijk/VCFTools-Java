/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.io.File;
import java.util.EnumSet;
import net.sf.samtools.SAMSequenceDictionary;
import org.broadinstitute.variant.variantcontext.writer.Options;
import org.broadinstitute.variant.variantcontext.writer.VariantContextWriter;
import org.broadinstitute.variant.variantcontext.writer.VariantContextWriterFactory;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfWriter {
    private VariantContextWriter writer;
    private File file;
    
    /**
     * Default constructor
     */
    public VcfWriter() {
        // Use empty dictionary (we do not have the corespondending fasta file)
        SAMSequenceDictionary dict=new SAMSequenceDictionary();
        
        // file write example
        //this.file=new File("test.vcf"); 
        //this.writer = VariantContextWriterFactory.create(this.file, dict);
        
        // for now we only write to System.out
        this.writer = VariantContextWriterFactory.create(System.out, dict, EnumSet.of(Options.ALLOW_MISSING_FIELDS_IN_HEADER) );

    }
    
    /**
     * Write a VCF header
     * @param header 
     */
    public void writeHeader(VcfHeader header) {
        this.writer.writeHeader(header.getBroadinstituteVCFHeader());
    }
    
    /**
     * Write a VCF line
     * @param vcfLine 
     */
    public void writeVcfLine(VcfLine vcfLine) {
        this.writer.add(vcfLine.getBroadinstituteVariantContext());
    }
    
    /**
     * Close write stream
     */
    public void close() {
        this.writer.close();
    }
    
    
}
