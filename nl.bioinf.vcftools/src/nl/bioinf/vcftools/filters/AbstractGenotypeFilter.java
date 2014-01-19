/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public abstract class AbstractGenotypeFilter {
    
    /**
     * Abstract class for genotype filters
     * @param vcfLine
     * @param settings
     * @return 
     */
    public abstract List<Boolean> filter(VcfLine vcfLine, Settings settings);
    
}
