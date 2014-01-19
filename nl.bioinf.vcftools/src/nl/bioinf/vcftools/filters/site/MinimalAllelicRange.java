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
public class MinimalAllelicRange extends AbstractSiteFilter {

    /**
     * Include only sites with allelic range higher than the minimal range.
     *
     * @param vcfLine
     * @param settings
     * @return boolean
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return vcfLine.getNAllels() > settings.getMinAlleles();
    }

}
