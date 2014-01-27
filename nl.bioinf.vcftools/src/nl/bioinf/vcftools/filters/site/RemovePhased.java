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
public class RemovePhased extends AbstractSiteFilter{
    /**
     * Removes sites that are phased, if phased filter is used.
     *
     * @param vcfLine
     * @param settings
     * @return
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return false;
    }   
}
