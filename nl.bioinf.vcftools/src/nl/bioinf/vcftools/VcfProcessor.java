/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.List;
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
    private Boolean performIndividualFilters;
    private Boolean performStatistics;
    private StatisticsGenerator statisticsGenerator;

    /**
     * The default constructor always needs the settings.
     *
     * @param settings
     * @author Sergio Bondietti <sergio@bondietti.nl>
     * @throws java.io.IOException
     */
    public VcfProcessor(Settings settings) throws IOException {
        // Fill the needed variables
        this.settings = settings;     
        this.setPerformIndividualFilters();     
        this.setPerformStatistics();
        
        // Perform all the filters and precalculations
        if (this.performIndividualFilters == true) { performDependencyCalculations(); }
        performFilters();
    }

    /**
     * Perform all Dependency calculations
     *
     * @author Sergio Bondietti <sergio@bondietti.nl>
     * @throws java.io.IOException
     */
    public final void performDependencyCalculations() throws IOException {
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
    public final void performFilters() throws IOException {
        // build reader and writer
        VcfReader reader = new VcfReader(settings.getInputFile());
        VcfHeader header = reader.getHeader();
        VcfWriter writer = null;
        // When output file is set create file writer, else use System.output
        if (this.settings.getOutputFile() != null) {
            writer = new VcfWriter(this.settings.getOutputFile());
        } else {   
            writer = new VcfWriter();
        }             
        
        // Write header to output
        writer.writeHeader(header);

        // Build the StatisticsGenerator
        if (this.performStatistics == true) { statisticsGenerator = new StatisticsGenerator(this.settings); }
        
        // Build the FilterHandler
        FilterHandler filterHandler = new FilterHandler(this.settings);

        // Perform Individual filters
        List<Boolean> individualFilterResults = null;
        if (this.performIndividualFilters == true) { individualFilterResults = filterHandler.performIndividualFilters(header, filterDependencies); }
        
        // While reader file has next vcfLine get next vcfLine
        while (reader.hasNextIter()) {
            VcfLine vcfLine = reader.getNextIter();
            
            // Filter away individuals that are not needed anymore
            if ((this.performIndividualFilters == true) && (individualFilterResults.contains(false) == true)) { vcfLine.filterIndividuals(individualFilterResults); }

            // Perform all the site filters
            if (filterHandler.performSiteFilters(vcfLine) == true) {
                // Perform all the genotype filters and Addapt genotypes
                List<Boolean> genotypeFilterResults = filterHandler.performGenotypeFilters(vcfLine);
                if (genotypeFilterResults.contains(false) == true) { vcfLine.filterGenotypes(genotypeFilterResults); }

                // Collect statistics
                if (this.performStatistics == true) { statisticsGenerator.collectStatistics(vcfLine); }
                
                // Write line
                writer.writeVcfLine(vcfLine);
            }
        }
        
        // Calculate the statistics
        if (this.performStatistics == true) { statisticsGenerator.calculateStatistics(); }

        // close the reader and writer
        reader.close();
        writer.close();
    }
    
    /**
     * Set if individual filters have to be performed
     */
    private void setPerformIndividualFilters() {
        this.performIndividualFilters = false;
        
        // check once if individual filters have to be performed
        if ((this.settings.isPhased() != null) && (this.settings.isPhased() == true)) { this.performIndividualFilters = true; }
        else if ((this.settings.getMinIndvMeanDp() != null) && (this.settings.getMaxIndvMeanDp() != null)) { this.performIndividualFilters = true; }
        else if (!this.settings.getKeepIndv().isEmpty()) { this.performIndividualFilters = true; }
        else if (!this.settings.getRemoveIndv().isEmpty()) { this.performIndividualFilters = true; }
        else if (this.settings.getMind() != null) { this.performIndividualFilters = true; }
        else if (this.settings.getMaxIndv() != null) { this.performIndividualFilters = true;  }    
    } 
     /**
      * Set if statistics calculations have to be performed
      */
    private void setPerformStatistics() {
        // todo (check parameters of statistics to calculate)
        this.performStatistics = true;       
    }
}
