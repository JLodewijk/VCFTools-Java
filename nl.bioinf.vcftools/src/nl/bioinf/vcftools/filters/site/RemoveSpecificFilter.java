/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class RemoveSpecificFilter extends AbstractSiteFilter {

    /**
     * Removes specific filter statuses if it matches the condition given by the user.
     * 
     * @param vcfLine
     * @param settings
     * @return boolean
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        for (String filterSatus : vcfLine.getSpecificFilter()) {
            return settings.getKeepFiltered().contains(filterSatus);
        }
        return true;
    }

}
