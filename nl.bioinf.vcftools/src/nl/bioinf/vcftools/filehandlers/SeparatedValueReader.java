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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashvin ponnudurai <as.ponnudurai@st.hanze.nl>
 */
public class SeparatedValueReader {

    private String filePath;
    private String seperator;
    private String linesOfFile;

    /**
     * Constructor inducing file reading.
     */
    public SeparatedValueReader(String filePath, String seperator) {
        this.filePath = filePath;
        this.seperator = seperator;
        this.readFile();

    }

    /**
     * Reads file
     * @author ashvin ponnudurai
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void readFile() {
        StringBuilder fileLines = new StringBuilder();
        try {

            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while((line = br.readLine()) != null){
                if (!(line.startsWith("#"))) { fileLines.append(line).append("\\n"); }
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(SeparatedValueReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.linesOfFile = fileLines.toString();

    }

    /**
     * returns arrayList with separated file line elements.
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
