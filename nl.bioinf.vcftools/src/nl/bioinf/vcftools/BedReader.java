/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashvin ponnudurai
 */
public class BedReader {
    private String filePath;

    /**
     * Constructor starts file reading.
     *
     * @param filePath 
     */
    BedReader(String filePath) {
        this.filePath = filePath;
        this.fileReader();
    }

    /**
     * Function which parse BED files
     *
     */
    
    public void fileReader() {

        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<BedLine> arrayListWithBedLine = new ArrayList<BedLine>();

        try {
            fr = new FileReader(this.filePath);
            br = new BufferedReader(fr);

            String line = null;

            while ((line = br.readLine()) != null) {
                BedLine bl = new BedLine(line);
                arrayListWithBedLine.add(bl);           
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BedReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BedReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
