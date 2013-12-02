/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import org.broad.tribble.AbstractFeatureReader;
import org.broad.tribble.FeatureReader;
import org.broadinstitute.variant.vcf.*;
import org.broadinstitute.variant.variantcontext.VariantContext;
import java.util.Iterator;

/**
 *
 * Genotype: vc.getGenotypes() In dit geval is het leeg? Type: vc.getType() In
 * dit geval zijn het SNP POS: vc.getEnd() en vc.getStart() Reference:
 * vc.getReference() Chromosome: vc.getChr() Alt: vc.getAlternateAlleles()
 * Aantal allelen: vc.getNAlleles() ID: vc.getID() Monomorphic:
 * vc.isMonomorphicInSamples() Polymorphic: vc.isPolymorphicInSamples()
 *
 * VCF file mag geen spaties bevatten, het mag alleen maar tabs bevatten. Anders werkt het niet.
 * Ook moet je rekening houden met dat het maar 1 tab herkend. Op twee tabs werkt het ook niet meer
 * 
 * @author Jeroen
 */
public class VCFRead {

    public static void main(String args[]) throws Exception {
        /**
         * latest VCF specification
         */
        final VCFCodec vcfCodec = new VCFCodec();

        boolean requireIndex = false;

        String filename = args[0];
        /* get A VCF Reader */
        System.out.println(filename);
        FeatureReader<VariantContext> reader = AbstractFeatureReader.getFeatureReader(
                filename, vcfCodec, requireIndex);
        /* read the header */
        VCFHeader header = (VCFHeader) reader.getHeader();
        System.out.println(header);
        /**
         * loop over each Variation.
         */
        Iterator<VariantContext> it = reader.iterator();
        while (it.hasNext()) {
            /* get next variation and save it */
            VariantContext vc = it.next();
            //vc.getEnd()
            System.out.println( vc.getGenotypes());

        }
        reader.close();
    }
}
