/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class Geno extends AbstractSiteFilter {

    /**
     * Exclude sites on the basis of the proportion of missing data (defined to be between 0 and 1, where 1 indicates no missing data allowed).
     * 
     * @param vcfLine
     * @param settings
     * @return
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
