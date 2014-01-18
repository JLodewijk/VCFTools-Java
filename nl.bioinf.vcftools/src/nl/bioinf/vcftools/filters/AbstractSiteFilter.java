package nl.bioinf.vcftools.filters;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Abstract class for non-context dependant (simple) filters.
 * 
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public abstract class AbstractSiteFilter {
    
    public abstract boolean filter(VcfLine vcfLine, Settings settings);
    
}
