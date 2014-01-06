package nl.bioinf.vcftools.filters;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.handlers.VcfLine;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public abstract class AbstractFilter {
    int count;

    /**
     * 
     * @param vcfLine
     * @param settings
     * @return
     */
    public abstract boolean filter(VcfLine vcfLine, Settings settings);
        
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
