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
import nl.bioinf.vcftools.filehandlers.VcfReader;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filehandlers.VcfWriter;

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
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void performDependencyCalculations() throws IOException {
        VcfReader reader = new VcfReader(settings.getInputFile());
        FilterDependencies filterDependencies = new FilterDependencies();

        // While reader file has next iteration get next iteration
        while (reader.hasNextIter()) {
            VcfLine iteration = reader.getNextIter();
            // Collect the dependencies of the reader line
            filterDependencies.collectDependencies(iteration);
        }
        // Calculate the dependencies (average etc)
        filterDependencies.calculateDependencies();
        reader.close();
    }

    /**
     * Reads VCF line by line while file has next line and performs the filters
     *
     * @throws IOException
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void performFilters() throws IOException {
        VcfReader reader = new VcfReader(settings.getInputFile());
        VcfWriter writer = new VcfWriter();
        writer.writeHeader(reader.getHeader());

        FilterHandler filterHandler = new FilterHandler(this.settings);

        // While reader file has next iteration get next iteration
        while (reader.hasNextIter()) {
            VcfLine iteration = reader.getNextIter();

            // Perform all the filters and check if line has to stay or not
            if (filterHandler.performSiteFilters(iteration) == true) {
                writer.writeVcfLine(iteration);
            }
        }
        reader.close();
        writer.close();
    }
}
