/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.handlers.VcfLine;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 *
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class IncludePositions extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
            return settings.containsPositions(vcfLine.getChr(), vcfLine.getPosition());
    }

    
}
