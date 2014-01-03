package nl.bioinf.vcftools;

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
    public abstract boolean Filter(VcfLine vcfLine, Settings settings);
    @Override
    public abstract String toString();
}
