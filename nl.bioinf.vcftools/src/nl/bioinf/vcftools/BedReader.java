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
    private ArrayList<String> arrayListChr;
    private ArrayList<Integer> arrayListStartPos;
    private ArrayList<Integer> arrayListStopPos;

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

        try {
            fr = new FileReader(this.filePath);
            br = new BufferedReader(fr);

            String line = null;

            while ((line = br.readLine()) != null) {
                BedLine bl = new BedLine(line);
                this.arrayListChr.add(bl.getChrName());
                this.arrayListStartPos.add(bl.getStartPos());
                this.arrayListStopPos.add(bl.getStopPos());

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BedReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BedReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get the reference to the file.
     *
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * Get the arraylist with all the chromosomes.
     * @return arrayListChr
     */
    public ArrayList<String> getArrayListChr() {
        return arrayListChr;
    }
    
    /**
     * Get the arraylist with all the start position of the chromosomes.
     * @return arrayListStartPos
     */
    public ArrayList<Integer> getArrayListStartPos() {
        return arrayListStartPos;
    }
    
    /**
     * Get the arrayllist with all the stop position of the chromosomes.
     * @return arrayListStopPos
     */
    public ArrayList<Integer> getArrayListStopPos() {
        return arrayListStopPos;
    }

}
