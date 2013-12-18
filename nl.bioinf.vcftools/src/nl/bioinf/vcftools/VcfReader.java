/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.broadinstitute.variant.variantcontext.Allele;
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
    
    private Settings settings;
//    public static void main(String[] args) throws IOException {
//
//        VcfReader read = new VcfReader();
//        read.readVcfLine("/share/home/mhroelfes/Dropbox/Thema10/VCF/region.txt");
//    }

    public VcfReader(Settings settings) {
        this.settings = settings;
        try {
            readVcfLines(settings.getInputFile());
        } catch (IOException ex) {
            Logger.getLogger(VcfReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Reads VCF line for line while file has next line
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String readVcfLines(String file) throws IOException {
        Vcf vcf = new Vcf(file);
        SiteFilters site = new SiteFilters();
        

        //while vcf file has next iteration get next iteration
        while (vcf.hasNextIter()) {
              VcfLine iteration = vcf.getNextIter();
              FilterHandler filterHandler = new FilterHandler(this.settings, iteration);
//            VariantContext vc = vcf.getNextIterAsVariantContext();
//            site.MeanDepth(vc,84,86);
            
            //get next line
//            VariantContext nextLine = vcf.getNextIterAsVariantContext();
//            //System.out.println(nextLine.getAlternateAlleles());
//            VariantContext nextLine2 = vcf.getNextIterAsVariantContext();
//            //System.out.println(nextLine2.getAllele("C"));
//            VariantContext nextLine3 = vcf.getNextIterAsVariantContext();
//            Genotype genotypes = nextLine.getGenotype(0);
//            //System.out.println(genotypes.getAlleles());
//            //System.out.println(nextLine.getAttribute("AF").getClass().getName());
//            //alleleFreq;
//            //alleleFreq = nextLine.getAttributeAsDouble("AF", 0.0);
//            System.out.println(nextLine.getAttribute("Dels").getClass().getName());
//            Object valObj = nextLine.getAttribute("AF");
//            //System.out.println(valObj.getClass().getName());
//
//            if (valObj instanceof String) {
//                //handle single value
//                Double val = Double.valueOf((String) valObj);
//                //System.out.println("val="+val);
//            } else {
//                //ArrayList value
//                ArrayList<String> values = (ArrayList<String>) valObj;
//                List<Double> valuesDoubles = new ArrayList<Double>();
//                for (String str : values) {
//                    valuesDoubles.add(str != null ? Double.parseDouble(str) : null);
//                }
//                //System.out.println("value list=" + valuesDoubles);
//            }

//            try{
//                Double val = Double.valueOf((String)valObj);
//            }catch(ClassCastException e){
//                ArrayList<String> values = (ArrayList<String>)valObj;
//            }
            //System.out.println("@@@" + nextLine);
            //float al = Float.valueOf(alleleFreq);
            //System.out.println(al);
            //System.out.println(nextLine.getGenotypes().size());
        }

        return null;

    }
}
