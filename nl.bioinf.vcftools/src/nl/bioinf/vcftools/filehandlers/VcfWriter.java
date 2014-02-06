/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
     * Use System.out as output
     */
    public VcfWriter() {
        // Use empty dictionary (we do not have the corespondending fasta file)
        SAMSequenceDictionary dict=new SAMSequenceDictionary();
        
        // Create a writer using System.out as output
        this.writer = VariantContextWriterFactory.create(System.out, dict, EnumSet.of(Options.ALLOW_MISSING_FIELDS_IN_HEADER) );
    }
    
    /**
     * Use file as output
     * @param file filename
     * @throws java.io.IOException 
     */
    public VcfWriter(String file) throws IOException {
        // Use empty dictionary (we do not have the corespondending fasta file)
        SAMSequenceDictionary dict=new SAMSequenceDictionary();       
        
        // Create a file handle and writer
        this.file = new File(file);
        this.file.createNewFile();
        this.writer = VariantContextWriterFactory.create(this.file, dict);     
    }
    
    /**
     * Use defined output stream
     * @param stream OutputStream object
     */
    public VcfWriter(OutputStream stream) {
        // Use empty dictionary (we do not have the corespondending fasta file)
        SAMSequenceDictionary dict=new SAMSequenceDictionary();
        
        // Create a writer using System.out as output
        this.writer = VariantContextWriterFactory.create(stream, dict, EnumSet.of(Options.ALLOW_MISSING_FIELDS_IN_HEADER) );
    }
    
    /**
     * Write a VCF header
     * @param header VcfHeader object
     */
    public void writeHeader(VcfHeader header) {
        this.writer.writeHeader(header.getBroadinstituteVCFHeader());
    }
    
    /**
     * Write a VCF line
     * @param vcfLine VcfLine object
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
