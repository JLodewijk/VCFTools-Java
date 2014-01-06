/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * @author mhroelfes
 */
public class NonRefAlleleFrequencies extends AbstractSimpleFilter {

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {

        Object valObj = vcfLine.getAttributeAsDouble("MLEAF");
        boolean reject = false;
        //If MLEAF contains only one value then perform this segment. 
        if (valObj instanceof String) {
            //Handles a single val
            Double val = Double.valueOf((String) valObj);
            //if val is between threshold (minRefAlleleFreq and maxRefAlleleFreq) approve line, else reject line.
            if (val < settings.getMaxNonRefAf() && val > settings.getNonRefAf()) {
                return true;
            } else {
                return false;
            }
        } //Sometimes a MLEAF contains multipule MLEAF values instead of one
        else {
            boolean keep = true;
            //ArrayList value.

            ArrayList<String> values = (ArrayList<String>) valObj;
            List<Double> valuesDoubles = new ArrayList<Double>();
            //set String ArrayList to Double ArrayList.      
            for (String str : values) {
                valuesDoubles.add(str != null ? Double.parseDouble(str) : null);
            }
            //for every double in ArrayList check if between threshold. If it falls between the thresholds then reject = false
            for (double dValue : valuesDoubles) {
                if (dValue < settings.getMaxNonRefAf() && dValue > settings.getNonRefAf()) {
                    keep = true;
                } else {
                    //Does not fall between the thresholds, so break out of loop. Since the SNP is rejected anyway
                    keep = false;
                    break;

                }
            }

            //check if to reject or approve SNP
            return keep;
        }

    }

}
