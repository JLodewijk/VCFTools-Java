/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSimpleFilter;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 * This class can exclude a specific snp on their ID, returns false if line has to be removed.
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class ExcludeSnp extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return !settings.getExcludeSnp().contains(vcfLine.getId());
    }
    
}
