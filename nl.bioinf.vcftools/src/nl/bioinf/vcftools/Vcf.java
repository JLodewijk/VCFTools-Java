/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

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
public class Vcf {
    
    private VCFCodec vcfCodec = new VCFCodec();
    private boolean requireIndex = false;
    private FeatureReader<VariantContext> reader;
    private Iterator<VariantContext> iter;

    /**
     * defines reader and iterator
     * @param file vcf input file
     * @throws IOException
     */
    public Vcf(String file) throws IOException {
        reader = AbstractFeatureReader.getFeatureReader(
                file, vcfCodec, requireIndex);
        iter = reader.iterator();
    }


    /**
     * return every iteration
     * @param file
     * @return
     * @throws IOException
     */
//    public VcfLine getNextIter(String file) throws IOException {
//        return new VcfLine(iter.next());
//    }

    public VariantContext getNextIter(String file) throws IOException {
        return iter.next();
    }
    
    /**
     * checks if next iteration exists
     * @param file
     * @return
     * @throws IOException
     */
    public boolean hasNextIter(String file) throws IOException {
        return iter.hasNext();
    }
}


