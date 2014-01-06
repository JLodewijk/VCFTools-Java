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
public class AlleleCount extends AbstractSimpleFilter {

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        Object valObj = vcfLine.getAttributeAsDouble("AC");
        //System.out.println(valObj.getClass().getName());
        if (valObj instanceof String) {
            //handle single value
            int val = Integer.parseInt((String) valObj);
            //if val is between threshold approve line, else reject line
            if (val < settings.getMaxAlleles() && val > settings.getMinAlleles()) {
                return true;
                
            } else {
                return false;
               
            }
            //System.out.println("val="+val);
        } else {
            boolean keep = true;
            //ArrayList value
            ArrayList<String> values = (ArrayList<String>) valObj;
            List<Integer> valuesInteger = new ArrayList<Integer>();
            //set String ArrayList to Double ArrayList            
            for (String str : values) {
                valuesInteger.add(str != null ? Integer.parseInt(str) : null);
            }
            //for every double in ArrayList check if between threshold
            for (double dValue : valuesInteger) {
                if (dValue < settings.getMaxAlleles() && dValue > settings.getMinAlleles()) {
                    keep =  true;
                } else {
                    keep=  false;
                    break;

                }
              
            }
            return keep;  
            //return if line has to be kept or not
           

        }
        
    }
    
}
