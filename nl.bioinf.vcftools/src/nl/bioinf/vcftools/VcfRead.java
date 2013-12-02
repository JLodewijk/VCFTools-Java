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
 *
 * @author mhroelfes
 */
public class VcfRead {

    private VCFCodec vcfCodec = new VCFCodec();
    private boolean requireIndex = false;
    private FeatureReader<VariantContext> reader;
    private Iterator<VariantContext> iter;

    public VcfRead(String file) throws IOException {
        reader = AbstractFeatureReader.getFeatureReader(
                file, vcfCodec, requireIndex);
        iter = reader.iterator();
    }

    public VariantContext getNextIter(String file) throws IOException {
        return iter.next();
    }

    public boolean hasNextIter(String file) throws IOException {
        return iter.hasNext();
    }
}
