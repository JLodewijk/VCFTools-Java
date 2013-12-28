/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

/**
 * Abstract class for context dependant filters.
 * 
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public abstract class AbstractContextDependantFilter extends AbstractFilter{

    /**
     * 
     * @param VCFLine
     * @param FilterDependancies
     * @return
     */
    public abstract boolean Filter(Object VCFLine, Object FilterDependancies);
}