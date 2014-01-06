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
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class IncludeChromosome extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
	return settings.getChr().containsKey(vcfLine.getChr());
	
    }
    
}
