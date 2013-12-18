/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

/**
 *
 * @author ashvin
 */
public class BedLine {

    private String chrName;
    private int startPos;
    private int stopPos;
    private String bedLineToSplit;
    
    /**
     * Constructor processing options and arguments.
     *
     * @param args arguments given on the commandline
     */
    BedLine(String bedLine) {
        this.bedLineToSplit = bedLine;
        this.bedLineElementDefiner();
    }
    
    /**
     * Constructor processing options and arguments.
     *
     * @param args arguments given on the commandline
     */
    public void bedLineElementDefiner() {
        String[] splitedBedLine = this.bedLineToSplit.split("\t");
        int startPosition = Integer.parseInt(splitedBedLine[1]);
        int stopPosition = Integer.parseInt(splitedBedLine[2]);
        
        setChrName(splitedBedLine[0]);
        setStartPos(startPosition);
        setStopPos(stopPosition);

    }

    public String getChrName() {
        return chrName;
    }

    public void setChrName(String chrName) {
        this.chrName = chrName;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getStopPos() {
        return stopPos;
    }

    public void setStopPos(int stopPos) {
        this.stopPos = stopPos;
    }

}
