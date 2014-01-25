/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfHeader;

/**
 * Abstract class for context dependent filters.
 * 
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public abstract class AbstractIndividualFilter {

    /**
     * Abstract class for individual filters
     * @param settings
     * @param vcfHeader
     * @param filterDependencies
     * @return
     */
    public abstract List<Boolean> filter(Settings settings, VcfHeader vcfHeader, FilterDependencies filterDependencies);
}