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
 *
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class MissingCount extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return vcfLine.getAttributeAsDouble("Dels") < settings.getMaxMissingCount();
    }
    

  
}
