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
    
    /** Creates static object instance of class */
    private static Settings instance = new Settings();
    /** XML input file */
    private String configFile;
    /** commons configuration XMLConfiguration */
    private XMLConfiguration config;

    /* VcfTools settings */
    
    /* Basic Settings */
    
    private String inputFile;
    private boolean gzipped;
    private boolean outputFile;
    
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
    //private Bed bed;
    //private Bed exludeBed;
    private boolean removeFilteredAll;
    private ArrayList<String> removeFiltered;
    private String removeFilteredFile;
    private ArrayList<String> keepFiltered;
    private String keepFilteredFile;
    private ArrayList<String> removeInfo;
    private ArrayList<String> keepInfo;
    private float minQ;
    private float minMeanDP;
    private float maxMeanDP;
    private float maf;
    private float maxMaf;
    private float nonRefAf;
    private float maxNonRefAf;
    private int mac;
    private int maxMac;
    private float nonRefAc;
    private float maxNonRefAc;
    private float hwe;
    private float geno;
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
    private float minIndvMeanDp;
    private float maxIndvMeanDp;
    private float mind;
    private boolean phased;
    private int maxIndv;
    
    /* statistics */
    
    private boolean count;
    private boolean freq;
    private boolean depth;
    
    /**
     * This constructor will load defaultConfig.xml
     */
    public Settings() {
        //this.configFile = "defaultConfig.xml";
        //this.load();
    }

    /**
     * This constructor loads the given filename configuration
     *
     * @param filename Filename to set as configuration source
     */
    public Settings(String filename) {
        //this.configFile = filename;
        //this.load();
    }

    /**
     * Load settings files into memory
     */
    public void load() {
        try {
            this.config = new XMLConfiguration(this.configFile);
            this.config.setAutoSave(false);

            // add other load logic here
        } catch (ConfigurationException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Save settings from memory to file
     *
     * @param filename Filename to save to.
     * @throws IOException
     */
    public void save(String filename) throws IOException {
        try {
            this.config.save(filename);
        } catch (ConfigurationException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Settings getInstance() {
        return instance;
    }

    /*
    Getters and setters for the VCFTools settings
     */
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

    public boolean isOutputFile() {
        return outputFile;
    }

    public void setOutputFile(boolean outputFile) {
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

    public String getRemoveFilteredFile() {
        return removeFilteredFile;
    }

    public void setRemoveFilteredFile(String removeFilteredFile) {
        this.removeFilteredFile = removeFilteredFile;
    }

    public ArrayList<String> getKeepFiltered() {
        return keepFiltered;
    }

    public void setKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    public String getKeepFilteredFile() {
        return keepFilteredFile;
    }

    public void setKeepFilteredFile(String keepFilteredFile) {
        this.keepFilteredFile = keepFilteredFile;
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

    public float getMinQ() {
        return minQ;
    }

    public void setMinQ(float minQ) {
        this.minQ = minQ;
    }

    public float getMinMeanDP() {
        return minMeanDP;
    }

    public void setMinMeanDP(float minMeanDP) {
        this.minMeanDP = minMeanDP;
    }

    public float getMaxMeanDP() {
        return maxMeanDP;
    }

    public void setMaxMeanDP(float maxMeanDP) {
        this.maxMeanDP = maxMeanDP;
    }

    public float getMaf() {
        return maf;
    }

    public void setMaf(float maf) {
        this.maf = maf;
    }

    public float getMaxMaf() {
        return maxMaf;
    }

    public void setMaxMaf(float maxMaf) {
        this.maxMaf = maxMaf;
    }

    public float getNonRefAf() {
        return nonRefAf;
    }

    public void setNonRefAf(float nonRefAf) {
        this.nonRefAf = nonRefAf;
    }

    public float getMaxNonRefAf() {
        return maxNonRefAf;
    }

    public void setMaxNonRefAf(float maxNonRefAf) {
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

    public float getNonRefAc() {
        return nonRefAc;
    }

    public void setNonRefAc(float nonRefAc) {
        this.nonRefAc = nonRefAc;
    }

    public float getMaxNonRefAc() {
        return maxNonRefAc;
    }

    public void setMaxNonRefAc(float maxNonRefAc) {
        this.maxNonRefAc = maxNonRefAc;
    }

    public float getHwe() {
        return hwe;
    }

    public void setHwe(float hwe) {
        this.hwe = hwe;
    }

    public float getGeno() {
        return geno;
    }

    public void setGeno(float geno) {
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

    public float getMinIndvMeanDp() {
        return minIndvMeanDp;
    }

    public void setMinIndvMeanDp(float minIndvMeanDp) {
        this.minIndvMeanDp = minIndvMeanDp;
    }

    public float getMaxIndvMeanDp() {
        return maxIndvMeanDp;
    }

    public void setMaxIndvMeanDp(float maxIndvMeanDp) {
        this.maxIndvMeanDp = maxIndvMeanDp;
    }

    public float getMind() {
        return mind;
    }

    public void setMind(float mind) {
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
