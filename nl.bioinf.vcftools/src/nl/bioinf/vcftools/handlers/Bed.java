/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.handlers;

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
public class Bed {

    private String filePath;
    private ArrayList<BedLine> arrayListWithBedLinesObjects;

    /**
     * Constructor starts file reading.
     *
     * @param filePath
     */
    Bed(String filePath) {
        this.filePath = filePath;
        this.readFile();

    }

    /**
     * Function which parse BED files
     * @author ashvin ponnudurai
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    
    public void readFile() {

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            
            this.arrayListWithBedLinesObjects = new ArrayList<BedLine>();
            String line = br.readLine();

            while (line != null) {
                BedLine bl = new BedLine(line);
                this.arrayListWithBedLinesObjects.add(bl);
                line = br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bed.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

/**
     * Get the arrayllist with all the bed line objects.
     * @return arrayListWithBedLinesObjects
     */
    public ArrayList<BedLine> getArrayListWithBedLinesObjects() {
        return arrayListWithBedLinesObjects;
    }

    /**
     * Get the reference to the file.
     *
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }
   
    
   
    
    
}
