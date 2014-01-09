/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools.filters.site;

import java.util.ArrayList;
import java.util.List;
import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSimpleFilter;
import nl.bioinf.vcftools.handlers.VcfLine;

/**
 *
 * @author mhroelfes
 */
public class AlleleFrequencies extends AbstractSimpleFilter{

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
            Object valObj = vcfLine.getAttributeAsDouble("AF");
            //System.out.println(valObj.getClass().getName());
            
            if (valObj instanceof String) {
                //handle single value
                Double val = Double.valueOf((String) valObj);
                //if val is between threshold approve line, else reject line
                if (val < settings.getMaxMaf() && val > settings.getMaf()) {
                    return true;
                } else {
                    return false;
                }
                //System.out.println("val="+val);
            } else {
                boolean keep = true;
                //ArrayList value
                ArrayList<String> values = (ArrayList<String>) valObj;
                List<Double> valuesDoubles = new ArrayList<Double>();
                //set String ArrayList to Double ArrayList            
                for (String str : values) {
                    valuesDoubles.add(str != null ? Double.parseDouble(str) : null);
                }
                //for every double in ArrayList check if between threshold
                for (double dValue : valuesDoubles) {
                    if (dValue < settings.getMaxMaf() && dValue > settings.getMaf()) {
                        keep = true;
                    } else {
                        keep = false;
                        break;

                    }
                }
                
                return keep;

              
            }
    }
    
}
