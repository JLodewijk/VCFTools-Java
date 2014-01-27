/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filehandlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 *
 * @author ashvin ponnudurai <as.ponnudurai@st.hanze.nl>
 */
public class BedFileReader {

    private String filePath;
    private MultiMap bedMap;

    /**
     * Constructor starts file reading.
     *
     * @param filePath
     */
    public BedFileReader(String filePath) {
        this.filePath = filePath;
        this.bedMap = new MultiValueMap();
        this.readFile();

    }

    /**
     * Function which parse BED files and creates a multiMap with the chromosome,
     * start and stop position
     *
     * @author ashvin ponnudurai <as.ponnudurai@st.hanze.nl>
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void readFile() {

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            String chr = null;
            Boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                // to skip firstline of the file, Boolean firstLine will be set to falsee 
                if (firstLine) {
                    firstLine = false;
                    continue;
                } else {
                    String[] splitedLine = line.split("\t");
                    this.bedMap.put(splitedLine[0], Arrays.asList(splitedLine[1], splitedLine[2]));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BedFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BedFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the multiMap with all the chromosome and start and stop position.
     *
     * @return bedMap
     */
    public MultiMap getBedMap() {
        return bedMap;
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
