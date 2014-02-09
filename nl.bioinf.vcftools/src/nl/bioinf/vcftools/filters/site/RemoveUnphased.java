/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfGenotype;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class RemoveUnphased extends AbstractSiteFilter {

    /**
     * Removes sites that are phased, if phased filter is used.
     *
     * @param vcfLine
     * @param settings
     * @return filter result
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        int genoNum = vcfLine.getGenotypeNumber();
        for (int i = 0; i < genoNum; i++) {
            VcfGenotype genotype = vcfLine.getGenotype(i);
            // if there is a genotype that has alleles als is unphased then clear site
            if ((!genotype.getAlleles().isEmpty()) && (genotype.isPhased() == false)) {
                return false;
            }
        }
        return true;
    }
}
