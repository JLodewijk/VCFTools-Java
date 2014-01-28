/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import java.util.ArrayList;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 * 
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class IncludePositions extends AbstractSiteFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
            return settings.containsPositions(vcfLine.getChr(), vcfLine.getPosition());
    }

    
}
