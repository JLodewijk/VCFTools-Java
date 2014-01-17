/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.handlers;

import java.io.IOException;
import java.util.Iterator;
import org.broad.tribble.AbstractFeatureReader;
import org.broad.tribble.FeatureReader;
import org.broadinstitute.variant.variantcontext.VariantContext;
import org.broadinstitute.variant.vcf.VCFCodec;

/**
 * @author mhroelfes
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfReader {
    
    private VCFCodec vcfCodec = new VCFCodec();
    private boolean requireIndex = false;
    private FeatureReader<VariantContext> reader;
    private Iterator<VariantContext> iter;

    /**
     * Contstructor using VCF filename as input.
     * @param file vcf input file
     * @throws IOException
     */
    public VcfReader(String file) throws IOException {
        reader = AbstractFeatureReader.getFeatureReader(
                file, vcfCodec, requireIndex);
        iter = reader.iterator();
    }


    /**
     * Return next iteration.
     * @return next iteration
     */
    public VcfLine getNextIter() {
        return new VcfLine(iter.next());
    }

     /**
     * Return next iteration as VariantContext (to test things with).
     * @return next iteration
     */
    public VariantContext getNextIterAsVariantContext() {
        return iter.next();
    }
    
    /**
     * Checks if next iteration exists.
     * @return true if has next iteration
     */
    public boolean hasNextIter() {
        return iter.hasNext();
    }
}


