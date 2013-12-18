/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.vcftools;

import java.util.ArrayList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.Allele;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class Misc {
    
    /**
     * Makes a list of strings from a list of objects.
     * 
     * @param objectList
     * @return list of strings
     */
    public static ArrayList<String> objListToStrArrayList(List<Object> objectList) {
        ArrayList<String> stringList = new ArrayList<>();
        for (Object object : objectList) {
            stringList.add(object != null ? object.toString() : null);
        }
        return stringList;
    }
    
    public static ArrayList<Integer> objListToIntegerArrayList(List<Object> objectList) {
        ArrayList<Integer> stringList = new ArrayList<>();
        for (Object object : objectList) {
            stringList.add(object != null ? Integer.parseInt(object.toString()) : null);
            
        }
        return stringList;
    }    
    
}
