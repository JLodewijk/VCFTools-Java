/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class Settings {

    /* Variables for basic functioning of the Settings class */
    /* XML input file */
    private String configFile;
    /* commons configuration XMLConfiguration */
    private XMLConfiguration configRead;
    private XMLConfiguration configCreate;

    /* VcfTools settings */
    /* Basic Settings */
    private String inputFile;
    private String outputFile;
    private boolean gzipped;

    /* Site Filters */
    private ArrayList<String> chr;
    private ArrayList<String> notChr;
    private int fromBp;
    private int toBp;
    private ArrayList<String> snp;
    private String snpFile;
    private ArrayList<String> excludeSnp;
    private String excludeSnpFile;
    private ArrayList positions;
    private String positionsFile;
    private ArrayList<Integer> excludePositions;
    private String excludePositionsFile;
    private boolean keepOnlyIndels;
    private boolean removeIndels;
    private String bedFile;
    //private Bed bed;
    //private Bed exludeBed;
    private boolean removeFilteredAll;
    private ArrayList<String> removeFiltered;
    private ArrayList<String> keepFiltered;
    private ArrayList<String> removeInfo;
    private ArrayList<String> keepInfo;
    private double minQ;
    private double minMeanDp;
    private double maxMeanDp;
    private double maf;
    private double maxMaf;
    private double nonRefAf;
    private double maxNonRefAf;
    private int mac;
    private int maxMac;
    private double nonRefAc;
    private double maxNonRefAc;
    private double hwe;
    private double geno;
    private int maxMissingCound;
    private int minAlleles;
    private int maxAlleles;
    private int thin;
    private String mask;
    private String invertMask;
    private int maskMin;

    /* Individual filters */
    private ArrayList<String> keepIndv;
    private String keepIndvFile;
    private ArrayList<String> removeIndv;
    private String removeIndvFile;
    private double minIndvMeanDp;
    private double maxIndvMeanDp;
    private double mind;
    private boolean phased;
    private int maxIndv;

    /* Statistics */
    private boolean count;
    private boolean freq;
    private boolean depth;

    /**
     * This constructor will set defaultConfig.xml as configuration file and load it.
     */
    public Settings() {
        //this.configFile = "defaultConfig.xml";
        //this.load();
    }

    /**
     * This constructor set the given filename as configuration and load it.
     *
     * @param filename Filename to set as configuration source
     */
    public Settings(String filename) {
        //this.configFile = filename;
        //this.load();
    }

    /**
     * Load in constructor defined settings file into memory.
     */
    public void load() {
        this.load(this.configFile);
    }

    /**
     * Load given settings file into memory.
     *
     * @param filename settings XML filename.
     */
    public void load(String filename) {
        try {
            this.configRead = new XMLConfiguration(filename);
            

            // add other load logic here
        } catch (ConfigurationException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Save settings from memory to file.
     * 
     * @author Sergio Bondietti <sergio@bondietti.nl>
     * @param filename Filename to save to.
     * @throws IOException
     */
    public void save(String filename) throws IOException {
        try {
            // open config object
            XMLConfiguration configCreate = new XMLConfiguration();
 
            // set filename and autoSave (we want to write all at once)
            configCreate.setFileName(filename);
            configCreate.setAutoSave(false);
            
            // set properties
            /* Basic Settings */
            configCreate.addProperty("basic.inputFile", this.inputFile);
            configCreate.addProperty("basic.outputFile", this.outputFile);
            configCreate.addProperty("basic.gzippedFile", this.gzipped);
            /* Site Filters */
            configCreate.addProperty("siteFilters.chr", this.chr);
            configCreate.addProperty("siteFilters.notChr", this.notChr);
            configCreate.addProperty("siteFilters.fromBp", this.fromBp);
            configCreate.addProperty("siteFilters.toBp", this.toBp);
            configCreate.addProperty("siteFilters.snp", this.snp);
            configCreate.addProperty("siteFilters.snpFile", this.snpFile);
            configCreate.addProperty("siteFilters.excludeSnp", this.excludeSnp);
            configCreate.addProperty("siteFilters.excludeSnpFile", this.excludeSnpFile);
            configCreate.addProperty("siteFilters.positions", this.positions);
            configCreate.addProperty("siteFilters.positionsFile", this.positionsFile);
            configCreate.addProperty("siteFilters.excludePositions", this.excludePositions);
            configCreate.addProperty("siteFilters.excludePositionsFile", this.excludePositionsFile);
            configCreate.addProperty("siteFilters.keepOnlyIndels", this.keepOnlyIndels);
            configCreate.addProperty("siteFilters.removeIndels", this.removeIndels);
            configCreate.addProperty("siteFilters.bedFile", this.bedFile);
            // todo: implement a way to store needed bed data to xml
            configCreate.addProperty("siteFilters.removeFilteredAll", this.removeFilteredAll);
            configCreate.addProperty("siteFilters.removeFiltered", this.removeFiltered);
            configCreate.addProperty("siteFilters.keepFiltered", this.keepFiltered);
            configCreate.addProperty("siteFilters.removeInfo", this.removeInfo);
            configCreate.addProperty("siteFilters.keepInfo", this.keepInfo);
            configCreate.addProperty("siteFilters.minQ", this.minQ);
            configCreate.addProperty("siteFilters.minMeanDp", this.minMeanDp);
            configCreate.addProperty("siteFilters.maxMeanDp", this.maxMeanDp);
            configCreate.addProperty("siteFilters.maf", this.maf);
            configCreate.addProperty("siteFilters.maxMaf", this.maf);
            configCreate.addProperty("siteFilters.nonRefAf", this.nonRefAf);
            configCreate.addProperty("siteFilters.maxNonRefAf", this.maxNonRefAf);
            configCreate.addProperty("siteFilters.mac", this.mac);
            configCreate.addProperty("siteFilters.maxMac", this.maxMac);
            configCreate.addProperty("siteFilters.maxNonRefAc", this.maxNonRefAc);
            configCreate.addProperty("siteFilters.hwe", this.hwe);
            configCreate.addProperty("siteFilters.geno", this.geno);
            configCreate.addProperty("siteFilters.maxMissingCount", this.maxMissingCound);
            configCreate.addProperty("siteFilters.minAlleles", this.minAlleles);
            configCreate.addProperty("siteFilters.maxAlleles", this.maxAlleles);
            configCreate.addProperty("siteFilters.thin", this.thin);
            configCreate.addProperty("siteFilters.mask", this.mask);
            configCreate.addProperty("siteFilters.invertMask", this.invertMask);
            configCreate.addProperty("siteFilters.maskMin", this.invertMask);
            /* Individual filters */
            configCreate.addProperty("individualFilters.keepIndv", this.keepIndv);
            configCreate.addProperty("individualFilters.keepIndvFile", this.keepIndvFile);
            configCreate.addProperty("individualFilters.removeIndv", this.removeIndv);
            configCreate.addProperty("individualFilters.removeIndvFile", this.removeIndvFile);
            configCreate.addProperty("individualFilters.minIndvMeanDp", this.minIndvMeanDp);
            configCreate.addProperty("individualFilters.maxIndvMeanDp", this.maxIndvMeanDp);
            configCreate.addProperty("individualFilters.mind", this.mind);
            configCreate.addProperty("individualFilters.phased", this.phased);
            configCreate.addProperty("individualFilters.maxIndv", this.maxIndv);
            /* Statistics */
            configCreate.addProperty("statistics.count", this.count);
            configCreate.addProperty("statistics.freq", this.freq);
            configCreate.addProperty("statistics.depth", this.depth);      
            
            // save config
            configCreate.save();
            
        } catch (ConfigurationException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Getters and setters for the VCFTools settings */
    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public boolean isGzipped() {
        return gzipped;
    }

    public void setGzipped(boolean gzipped) {
        this.gzipped = gzipped;
    }

    public String isOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public ArrayList<String> getChr() {
        return chr;
    }

    public void setChr(ArrayList<String> chr) {
        this.chr = chr;
    }

    public ArrayList<String> getNotChr() {
        return notChr;
    }

    public void setNotChr(ArrayList<String> notChr) {
        this.notChr = notChr;
    }

    public int getFromBp() {
        return fromBp;
    }

    public void setFromBp(int fromBp) {
        this.fromBp = fromBp;
    }

    public int getToBp() {
        return toBp;
    }

    public void setToBp(int toBp) {
        this.toBp = toBp;
    }

    public ArrayList<String> getSnp() {
        return snp;
    }

    public void setSnp(ArrayList<String> snp) {
        this.snp = snp;
    }

    public String getSnpFile() {
        return snpFile;
    }

    public void setSnpFile(String snpFile) {
        this.snpFile = snpFile;
    }

    public ArrayList<String> getExcludeSnp() {
        return excludeSnp;
    }

    public void setExcludeSnp(ArrayList<String> excludeSnp) {
        this.excludeSnp = excludeSnp;
    }

    public String getExcludeSnpFile() {
        return excludeSnpFile;
    }

    public void setExcludeSnpFile(String excludeSnpFile) {
        this.excludeSnpFile = excludeSnpFile;
    }

    public ArrayList getPositions() {
        return positions;
    }

    public void setPositions(ArrayList positions) {
        this.positions = positions;
    }

    public String getPositionsFile() {
        return positionsFile;
    }

    public void setPositionsFile(String positionsFile) {
        this.positionsFile = positionsFile;
    }

    public ArrayList<Integer> getExcludePositions() {
        return excludePositions;
    }

    public void setExcludePositions(ArrayList<Integer> excludePositions) {
        this.excludePositions = excludePositions;
    }

    public String getExcludePositionsFile() {
        return excludePositionsFile;
    }

    public void setExcludePositionsFile(String excludePositionsFile) {
        this.excludePositionsFile = excludePositionsFile;
    }

    public boolean isKeepOnlyIndels() {
        return keepOnlyIndels;
    }

    public void setKeepOnlyIndels(boolean keepOnlyIndels) {
        this.keepOnlyIndels = keepOnlyIndels;
    }

    public boolean isRemoveIndels() {
        return removeIndels;
    }

    public void setRemoveIndels(boolean removeIndels) {
        this.removeIndels = removeIndels;
    }

    public boolean isRemoveFilteredAll() {
        return removeFilteredAll;
    }

    public void setRemoveFilteredAll(boolean removeFilteredAll) {
        this.removeFilteredAll = removeFilteredAll;
    }

    public ArrayList<String> getRemoveFiltered() {
        return removeFiltered;
    }

    public void setRemoveFiltered(ArrayList<String> removeFiltered) {
        this.removeFiltered = removeFiltered;
    }

    public ArrayList<String> getKeepFiltered() {
        return keepFiltered;
    }

    public void setKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    public ArrayList<String> getRemoveInfo() {
        return removeInfo;
    }

    public void setRemoveInfo(ArrayList<String> removeInfo) {
        this.removeInfo = removeInfo;
    }

    public ArrayList<String> getKeepInfo() {
        return keepInfo;
    }

    public void setKeepInfo(ArrayList<String> keepInfo) {
        this.keepInfo = keepInfo;
    }

    public double getMinQ() {
        return minQ;
    }

    public void setMinQ(double minQ) {
        this.minQ = minQ;
    }

    public double getMinMeanDP() {
        return minMeanDp;
    }

    public void setMinMeanDP(double minMeanDP) {
        this.minMeanDp = minMeanDP;
    }

    public double getMaxMeanDP() {
        return maxMeanDp;
    }

    public void setMaxMeanDP(double maxMeanDP) {
        this.maxMeanDp = maxMeanDP;
    }

    public double getMaf() {
        return maf;
    }

    public void setMaf(double maf) {
        this.maf = maf;
    }

    public double getMaxMaf() {
        return maxMaf;
    }

    public void setMaxMaf(double maxMaf) {
        this.maxMaf = maxMaf;
    }

    public double getNonRefAf() {
        return nonRefAf;
    }

    public void setNonRefAf(double nonRefAf) {
        this.nonRefAf = nonRefAf;
    }

    public double getMaxNonRefAf() {
        return maxNonRefAf;
    }

    public void setMaxNonRefAf(double maxNonRefAf) {
        this.maxNonRefAf = maxNonRefAf;
    }

    public int getMac() {
        return mac;
    }

    public void setMac(int mac) {
        this.mac = mac;
    }

    public int getMaxMac() {
        return maxMac;
    }

    public void setMaxMac(int maxMac) {
        this.maxMac = maxMac;
    }

    public double getNonRefAc() {
        return nonRefAc;
    }

    public void setNonRefAc(double nonRefAc) {
        this.nonRefAc = nonRefAc;
    }

    public double getMaxNonRefAc() {
        return maxNonRefAc;
    }

    public void setMaxNonRefAc(double maxNonRefAc) {
        this.maxNonRefAc = maxNonRefAc;
    }

    public double getHwe() {
        return hwe;
    }

    public void setHwe(double hwe) {
        this.hwe = hwe;
    }

    public double getGeno() {
        return geno;
    }

    public void setGeno(double geno) {
        this.geno = geno;
    }

    public int getMaxMissingCound() {
        return maxMissingCound;
    }

    public void setMaxMissingCound(int maxMissingCound) {
        this.maxMissingCound = maxMissingCound;
    }

    public int getMinAlleles() {
        return minAlleles;
    }

    public void setMinAlleles(int minAlleles) {
        this.minAlleles = minAlleles;
    }

    public int getMaxAlleles() {
        return maxAlleles;
    }

    public void setMaxAlleles(int maxAlleles) {
        this.maxAlleles = maxAlleles;
    }

    public int getThin() {
        return thin;
    }

    public void setThin(int thin) {
        this.thin = thin;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getInvertMask() {
        return invertMask;
    }

    public void setInvertMask(String invertMask) {
        this.invertMask = invertMask;
    }

    public int getMaskMin() {
        return maskMin;
    }

    public void setMaskMin(int maskMin) {
        this.maskMin = maskMin;
    }

    public ArrayList<String> getKeepIndv() {
        return keepIndv;
    }

    public void setKeepIndv(ArrayList<String> keepIndv) {
        this.keepIndv = keepIndv;
    }

    public String getKeepIndvFile() {
        return keepIndvFile;
    }

    public void setKeepIndvFile(String keepIndvFile) {
        this.keepIndvFile = keepIndvFile;
    }

    public ArrayList<String> getRemoveIndv() {
        return removeIndv;
    }

    public void setRemoveIndv(ArrayList<String> removeIndv) {
        this.removeIndv = removeIndv;
    }

    public String getRemoveIndvFile() {
        return removeIndvFile;
    }

    public void setRemoveIndvFile(String removeIndvFile) {
        this.removeIndvFile = removeIndvFile;
    }

    public double getMinIndvMeanDp() {
        return minIndvMeanDp;
    }

    public void setMinIndvMeanDp(double minIndvMeanDp) {
        this.minIndvMeanDp = minIndvMeanDp;
    }

    public double getMaxIndvMeanDp() {
        return maxIndvMeanDp;
    }

    public void setMaxIndvMeanDp(double maxIndvMeanDp) {
        this.maxIndvMeanDp = maxIndvMeanDp;
    }

    public double getMind() {
        return mind;
    }

    public void setMind(double mind) {
        this.mind = mind;
    }

    public boolean isPhased() {
        return phased;
    }

    public void setPhased(boolean phased) {
        this.phased = phased;
    }

    public int getMaxIndv() {
        return maxIndv;
    }

    public void setMaxIndv(int maxIndv) {
        this.maxIndv = maxIndv;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean isFreq() {
        return freq;
    }

    public void setFreq(boolean freq) {
        this.freq = freq;
    }

    public boolean isDepth() {
        return depth;
    }

    public void setDepth(boolean depth) {
        this.depth = depth;
    }

}
