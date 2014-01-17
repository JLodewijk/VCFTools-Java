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
 * Abstract class for context dependent filters.
 * 
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public abstract class AbstractIndividualFilter extends AbstractFilter{

    /**
     * 
     * @param vcfLine
     * @param settings
     * @param filterDependencies
     * @return
     */
    public abstract List<Boolean> filter(VcfLine vcfLine, Settings settings, FilterDependencies filterDependencies);
}