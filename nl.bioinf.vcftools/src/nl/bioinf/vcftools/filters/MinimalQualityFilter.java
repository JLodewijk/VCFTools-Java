/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * 
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class MinimalQualityFilter extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        return vcfLine.getPhredScaledQual() >= settings.getMinQ();
    }
    
}
