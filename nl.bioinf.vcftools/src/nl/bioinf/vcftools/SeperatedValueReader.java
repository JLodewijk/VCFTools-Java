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
public class SeperatedValueReader {

    private String filePath;
    private String seperator;
    private String linesOfFile;

    /**
     * Constructor inducining file reading.
     */
    SeperatedValueReader(String filePath, String seperator) {
        this.filePath = filePath;
        this.seperator = seperator;
        this.readFile();

    }

    /**
     * Reads file
     */
    public void readFile() {
        StringBuilder fileLines = new StringBuilder();
        try {

            FileReader fr = null;
            BufferedReader br = null;
            fr = new FileReader(this.filePath);
            br = new BufferedReader(fr);

            String line = null;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (!(line.startsWith("#"))) {

                    fileLines.append(line + "\\n");
                }

            }

        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(SeperatedValueReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.linesOfFile = fileLines.toString();

    }

    /**
     * returns arrayList with seperated file line elements.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getArrayList() {
        String[] splitedFileLines = this.linesOfFile.split(this.seperator);
        ArrayList<String> arrayListFilePathElements = new ArrayList<String>();

        for (String lineElement : splitedFileLines) {
            arrayListFilePathElements.add(lineElement);
        }

        return arrayListFilePathElements;
    }

}
