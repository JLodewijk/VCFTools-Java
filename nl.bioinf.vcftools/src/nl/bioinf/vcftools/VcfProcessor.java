/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.bioinf.vcftools.filters.FilterDependencies;
import nl.bioinf.vcftools.filters.FilterHandler;
import nl.bioinf.vcftools.handlers.VcfReader;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * @author Marco Roelfes
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfProcessor {

    /**
     * @param args
     * @throws IOException
     */
    
    private Settings settings;

    /**
     * The default constructor always needs the settings.
     * @param settings 
     */
    public VcfProcessor(Settings settings) {
        this.settings = settings;
        try {
            performDependencyCalculations();
            performFilters();
        } catch (IOException ex) {
            Logger.getLogger(VcfProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Perform all Dependency calculations
     */
    public void performDependencyCalculations() throws IOException {
        VcfReader vcf = new VcfReader(settings.getInputFile());
        FilterDependencies filterDependencies = new FilterDependencies();
        
        // While vcf file has next iteration get next iteration
        while (vcf.hasNextIter()) {
            VcfLine iteration = vcf.getNextIter();
            // Collect the dependencies of the vcf line
            filterDependencies.collectDependencies(iteration);
        }
        // Calculate the dependencies (average etc)
        filterDependencies.calculateDependencies();
        
    }
    

    /**
     * Reads VCF line for line while file has next line and performs the filters
     *
     * @param file
     * @return
     * @throws IOException
     */
    public void performFilters() throws IOException {
        VcfReader vcf = new VcfReader(settings.getInputFile());
        FilterHandler filterHandler = new FilterHandler(this.settings);
        
        // While vcf file has next iteration get next iteration
        while (vcf.hasNextIter()) {     
              VcfLine iteration = vcf.getNextIter();
              
              // Perform all the filters and check if line has to stay or not
              if (filterHandler.performFilters(iteration) == false) {
                System.out.println("Removed:" + iteration.toString());
              }
              else {
                System.out.println("Kept:" + iteration.toString());
              }
        }
    }
}
