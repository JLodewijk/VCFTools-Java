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
 * @author mhroelfes
 */
public class KeepInfo extends AbstractSimpleFilter {

    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        for (String i : settings.getKeepInfo()) {
            if (vcfLine.getAttributeAsString(i) == null){
                return false;
            }
        }
        return true;
    


    }
//            if (line.getAttribute(info) != null) {
//                System.out.println("Keep line since it contains the info header " + info + " " + line.getAttribute(info));
//            } else {
//                System.out.println("Reject " + line.getAttributes() + " since it does not contain " + info);
//            }

    
}
