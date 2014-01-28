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
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filehandlers.VcfHeader;
import nl.bioinf.vcftools.filters.FilterDependencies;
import nl.bioinf.vcftools.filters.FilterHandler;
import nl.bioinf.vcftools.filehandlers.VcfReader;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filehandlers.VcfWriter;

/**
 * Process the VcfFiles and perform the filters and statistics calculations
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
    private FilterDependencies filterDependencies;

    /**
     * The default constructor always needs the settings.
     *
     * @param settings
     * @author Sergio Bondietti <sergio@bondietti.nl>
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
     *
     * @author Sergio Bondietti <sergio@bondietti.nl>
     * @throws java.io.IOException
     */
    public void performDependencyCalculations() throws IOException {
        /*
         For now we are performing these dependency checks on the complete
         individual. In future versions this could be limited all the sites
         left after previously filtered sites so we get the behaviour of the
         original vcftools. In this scenario the same FilterDependencies class 
         can be used but then only feed it the VcfLines that are left after
         the first site filters.
        
         One thing to look out for when changing this scenario is when to 
         perform the (site) phased filter.
         */

        VcfReader reader = new VcfReader(settings.getInputFile());
        this.filterDependencies = new FilterDependencies();

        // While reader file has next vcfLine get next vcfLine
        while (reader.hasNextIter()) {
            VcfLine iteration = reader.getNextIter();
            // Collect the dependencies of the reader line
            this.filterDependencies.collectDependencies(iteration);
        }
        // Calculate the dependencies and close the reader
        this.filterDependencies.calculateDependencies();
        reader.close();
    }

    /**
     * Performs all the filters
     *
     * @throws IOException
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void performFilters() throws IOException {
        // build reader and writer
        VcfReader reader = new VcfReader(settings.getInputFile());
        VcfWriter writer = new VcfWriter();
        VcfHeader header = reader.getHeader();
        // Prebuild recurring variable addresses
        boolean siteFilterResult;
        VcfLine vcfLine;
        List<Boolean> genotypeFilterResult;
        
        // Write header to output
        writer.writeHeader(header);

        // Build the FilterHandler
        FilterHandler filterHandler = new FilterHandler(this.settings);

        // Perform Individual filters
        List<Boolean> individualFilterResults = filterHandler.performIndividualFilters(header, filterDependencies);
        
        // While reader file has next vcfLine get next vcfLine
        while (reader.hasNextIter()) {
            vcfLine = reader.getNextIter();
            
            // Filter away individuals that are not needed anymore
            vcfLine.filterIndividuals(individualFilterResults);

            // Perform all the site filters
            siteFilterResult = filterHandler.performSiteFilters(vcfLine);

            if (siteFilterResult == true) {
                // Perform all the genotype filters
                genotypeFilterResult = filterHandler.performGenotypeFilters(vcfLine);
                
                // Addapt genotypes
                vcfLine.filterGenotypes(genotypeFilterResult);

                // Write line
                writer.writeVcfLine(vcfLine);
            }
        }

        // close the reader and writer
        reader.close();
        writer.close();
    }
}
