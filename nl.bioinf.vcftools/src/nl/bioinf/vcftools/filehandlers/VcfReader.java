/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filehandlers;

import java.io.IOException;
import java.util.Iterator;
import org.broad.tribble.AbstractFeatureReader;
import org.broad.tribble.FeatureReader;
import org.broadinstitute.variant.variantcontext.VariantContext;
import org.broadinstitute.variant.vcf.VCFCodec;
import org.broadinstitute.variant.vcf.VCFHeader;

/**
 * @author mhroelfes
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfReader {
    
    private VCFCodec vcfCodec = new VCFCodec();
    private FeatureReader<VariantContext> reader;
    private Iterator<VariantContext> iter;
    private VcfHeader header;

    /**
     * Contstructor using VCF filename as input.
     * @param file vcf input file
     * @throws IOException
     */
    public VcfReader(String file) throws IOException {
        this.reader = AbstractFeatureReader.getFeatureReader(file, vcfCodec, false);
        this.header = new VcfHeader((VCFHeader) this.reader.getHeader());
        this.iter = this.reader.iterator();
    }


    /**
     * Return next iteration.
     * @return next iteration
     */
    public VcfLine getNextIter() {
        return new VcfLine(this.iter.next());
    }

     /**
     * Return next iteration as VariantContext (to test things with).
     * @return next iteration
     */
    public VariantContext getNextIterAsVariantContext() {
        return this.iter.next();
    }
    
    /**
     * Checks if next iteration exists.
     * @return true if has next iteration
     */
    public boolean hasNextIter() {
        return this.iter.hasNext();
    }

    /**
     * Get the header of the VCF file
     * @return header
     */
    public VcfHeader getHeader() {
        return this.header;
    }
    
    /**
     * Close read stream
     * @throws IOException 
     */
    public void close() throws IOException {
        this.reader.close();
    }
    
    
}


