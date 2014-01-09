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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 *
 * @author Ashvin
 */
public class PositionFileReader {

    private MultiMap chrPositionMap;
    private String filePath;

    public PositionFileReader(String filePath) {
        this.chrPositionMap = new MultiValueMap();
        this.filePath = filePath;

        fileReader();
        

    }

    public void fileReader() {
        try {

            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = null;

//           System.out.println("Start reading file....");
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] chrStartPosStopPos = line.split("\t");

                    this.chrPositionMap.put(chrStartPosStopPos[0], Arrays.asList(Integer.parseInt(chrStartPosStopPos[1]), Integer.parseInt(chrStartPosStopPos[2])));

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PositionFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PositionFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MultiMap getChrPositionMap() {
        return chrPositionMap;
    }

}
