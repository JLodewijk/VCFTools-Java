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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 *
 * @author Ashvin
 */
public class MaskFileReader {

    private String filePath;
    private MultiMap maskMap;
    private Boolean invertMask;

    /**
     * Constructor sets mask file to create a MultiMap with the mask file
     * content
     *
     * @param filePath mask file to do mask filtering
     * @param invertMask Boolean if mask file has to be inverted
     */
    public MaskFileReader(String filePath, Boolean invertMask) {
        this.filePath = filePath;
        this.maskMap = new MultiValueMap();
        this.invertMask = invertMask;
        fileReader();

    }

    /**
     * fileReader reads mask file and makes a MultiMap of the mask file content
     *
     * @exception FileNotFoundException, IOException
     */
    private void fileReader() {
        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            String chr = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith(">")) {
                    chr = line.substring(1, line.length());
                } else {
                    if (this.invertMask == true) {
                        /*iterate in reverse over sequence of digit string*/
                        for (int i = line.length() - 1; i <= 0; i--) {
                            this.maskMap.put(chr, Character.getNumericValue(line.charAt(i)));
                        }
                    } else {
                        /*iterate in over sequence of digit string*/
                        for (int i = 0; i < line.length(); i++) {
                            this.maskMap.put(chr, Character.getNumericValue(line.charAt(i)));
                        }
                    }
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaskFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MaskFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return mask MultiMap
     */
    public MultiMap getMaskMap() {
        return maskMap;
    }

}
