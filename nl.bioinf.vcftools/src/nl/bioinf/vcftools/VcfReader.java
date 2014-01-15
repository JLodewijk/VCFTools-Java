/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.bioinf.vcftools.filters.FilterHandler;
import nl.bioinf.vcftools.handlers.Vcf;
import nl.bioinf.vcftools.handlers.VcfLine;

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
//        read.performFilters("/share/home/mhroelfes/Dropbox/Thema10/VCF/region.txt");
//    }

    public VcfReader(Settings settings) {
        this.settings = settings;
        try {
            performFilters();
        } catch (IOException ex) {
            Logger.getLogger(VcfReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     */
    public void performDependencyCalculations() {
        
    }
    

    /**
     * Reads VCF line for line while file has next line and performs the filters
     *
     * @param file
     * @return
     * @throws IOException
     */
    public void performFilters() throws IOException {
        Vcf vcf = new Vcf(settings.getInputFile());
        
        FilterHandler filterHandler = new FilterHandler(this.settings);
        //while vcf file has next iteration get next iteration
        while (vcf.hasNextIter()) {
              VcfLine iteration = vcf.getNextIter();
              
              if (filterHandler.performFilters(iteration) == false) {
                System.out.println("Removed:" + iteration.toString());
              }
              else {
                System.out.println("Kept:" + iteration.toString());
              }
        }


    }
}
