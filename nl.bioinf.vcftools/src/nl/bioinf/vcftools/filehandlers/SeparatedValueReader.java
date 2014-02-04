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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashvin ponnudurai <as.ponnudurai@st.hanze.nl>
 */
public class SeparatedValueReader {

    private String filename;
    private String seperator;
    private String linesOfFile;

    /**
     * Default constructor
     * @param filename
     * @param seperator 
     */
    public SeparatedValueReader(String filename, String seperator) {
        this.filename = filename;
        this.seperator = seperator;
        this.readFile();

    }

    /**
     * Read the file
     * @author ashvin ponnudurai
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    private void readFile() {
        StringBuilder fileLines = new StringBuilder();
        try {

            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while((line = br.readLine()) != null){
                // add each line to the StringBuilder
                if (!(line.startsWith("#"))) { fileLines.append(line).append(this.seperator);}
            }
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(SeparatedValueReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        // this.linesOfFile is one string, which contains all the lines of the file
        this.linesOfFile = fileLines.toString();
    }

    /**
     * returns List with separated file elements.
     *
     * @return List<String>
     */
    public List<String> getList() {
        String[] splitedFileLines = this.linesOfFile.split(this.seperator);
        ArrayList<String> arrayListFilePathElements = new ArrayList<String>();
        for (String lineElement : splitedFileLines) {arrayListFilePathElements.add(lineElement);}
        return arrayListFilePathElements;
    }

}
