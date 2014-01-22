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

        // While reader file has next iteration get next iteration
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

        // Write header to output
        writer.writeHeader(reader.getHeader());

        // Build the FilterHandler
        FilterHandler filterHandler = new FilterHandler(this.settings);

        // While reader file has next iteration get next iteration
        while (reader.hasNextIter()) {
            VcfLine iteration = reader.getNextIter();

            // Perform all the site filters
            boolean siteFilterResult = filterHandler.performSiteFilters(iteration);

            // Perform all the genotype filters
            List<Boolean> genotypeFilterResult = filterHandler.performGenotypeFilters(iteration);

            // TODO: Perform the needed VcfLine editing here
            // Write the line away if SiteFilter allowed it
            if (siteFilterResult == true) {
                // Addapt genotypes
                VcfLine vcfLine = this.processGenotypeChanges(iteration, genotypeFilterResult);
                // Write line
                writer.writeVcfLine(vcfLine);
            }
        }

        // close the reader and writer
        reader.close();
        writer.close();
    }

    /**
     * Gets vcfLine and genotypefilter results and returns genotypefilter processed vcfline
     * @param vcfLine
     * @param genotypeFilterResult
     * @return 
     */
    private VcfLine processGenotypeChanges(VcfLine vcfLine, List<Boolean> genotypeFilterResult) {
        // When result is all true return original vcfLine
        if (!genotypeFilterResult.contains(false)) {
            return vcfLine;
        } 
        // When there are results contains false elements, store mutated genotypes
        else {
            // Create new list of Genotypes
            List<VcfGenotype> genoTypes = new ArrayList<>();
            int index = 0;
            // loop trough results
            for (Boolean i : genotypeFilterResult) {
                // When result is false remove alleles from original genotype
                VcfGenotype genotype = vcfLine.getGenotype(index);
                if (i == false) {
                    genotype.clearAlleles();   
                } 
                // Store genotype in new list   
                genoTypes.add(genotype); 
                index++;
            }
            // Build new vcfLine with changed genotypes and return it
            vcfLine.setGenotypes(genoTypes);
            return vcfLine;
        }
    }
}
