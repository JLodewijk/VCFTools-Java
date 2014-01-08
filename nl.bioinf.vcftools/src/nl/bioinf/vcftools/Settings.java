/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
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

    /* VcfTools settings */
    /* Basic Settings */
    private String inputFile;
    private String outputFile;
    private Boolean gzipped;

    /* Site Filters */
    private MultiMap chr;
    private MultiMap notChr;

    private ArrayList<String> snp;
    private String snpFile;
    private ArrayList<String> excludeSnp;
    private String excludeSnpFile;
    private MultiMap positions;
    private String positionsFile;
    private MultiMap excludePositions;
    private String excludePositionsFile;
    private Boolean keepIndels;
    private String bedFile;
    private String exludeBedFile;
    private MultiMap bed;
    private MultiMap exludeBed;
    private Boolean removeFilteredAll;
    private ArrayList<String> removeFiltered;
    private ArrayList<String> keepFiltered;
    private ArrayList<String> removeInfo;
    private ArrayList<String> keepInfo;
    private Double minQ;
    private Double minMeanDp;
    private Double maxMeanDp;
    private Double maf;
    private Double maxMaf;
    private Double nonRefAf;
    private Double maxNonRefAf;
    private Integer mac;
    private Integer maxMac;
    private Double nonRefAc;
    private Double maxNonRefAc;
    private Double hwe;
    private Double geno;
    private Integer maxMissingCount;
    private Integer minAlleles;
    private Integer maxAlleles;
    private Integer thin;
    private String mask;
    private String invertMask;
    private Integer maskMin;

    /* Individual filters */
    private ArrayList<String> keepIndv;
    private String keepIndvFile;
    private ArrayList<String> removeIndv;
    private String removeIndvFile;
    private Double minIndvMeanDp;
    private Double maxIndvMeanDp;
    private Double mind;
    private Boolean phased;
    private Integer maxIndv;
    
    /* Statistics */
    private Boolean count;
    private Boolean freq;
    private Boolean depth;

    /**
     * This constructor will set defaultConfig.xml as configuration file and load it.
     */
    public Settings() {
        this.configFile = "defaultConfig.xml";
        
        // Build empty datasets for variables that assume an instance in their add functions
        this.chr = new MultiValueMap();
        this.notChr = new MultiValueMap();
        this.snp = new ArrayList<>();
        this.excludeSnp = new ArrayList<>();
        this.positions = new MultiValueMap();
        this.excludePositions = new MultiValueMap();
        this.bed = new MultiValueMap();
        this.exludeBed = new MultiValueMap();
        this.removeFiltered = new ArrayList<>();
        this.keepFiltered = new ArrayList<>();
        this.removeInfo = new ArrayList<>();
        this.keepInfo = new ArrayList<>();
        this.keepIndv = new ArrayList<>();
        this.removeIndv = new ArrayList<>();
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
     * @author Sergio Bondietti <sergio@bondietti.nl>
     * @param filename settings XML filename.
     */
    public void load(String filename) {
        try {
            // open config object
            XMLConfiguration configRead = new XMLConfiguration(filename);   
            
            /* Site Filters */
//            this.chr = Misc.objListToStrArrayList(configRead.getList("siteFilters.chr"));
//            this.notChr = Misc.objListToStrArrayList(configRead.getList("siteFilters.notChr"));
            this.snp = Misc.objListToStrArrayList(configRead.getList("siteFilters.snp"));
            this.excludeSnp = Misc.objListToStrArrayList(configRead.getList("siteFilters.excludeSnp"));
            // this.positions = Misc.objListToIntegerArrayList(configRead.getList("siteFilters.positions")); // fix for multimap needed
            // this.excludePositions = Misc.objListToIntegerArrayList(configRead.getList("siteFilters.excludePositions"));  // multimap fix
//            this.keepOnlyIndels = configRead.getBoolean("siteFilters.keepOnlyIndels");
//            this.removeIndels = configRead.getBoolean("siteFilters.removeIndels");
            // todo: load bed data
            this.removeFilteredAll = configRead.getBoolean("siteFilters.removeFilteredAll");
            this.removeFiltered = Misc.objListToStrArrayList(configRead.getList("siteFilters.removeFiltered"));
            this.keepFiltered = Misc.objListToStrArrayList(configRead.getList("siteFilters.keepFiltered"));
            this.removeInfo = Misc.objListToStrArrayList(configRead.getList("siteFilters.removeInfo"));
            this.keepInfo = Misc.objListToStrArrayList(configRead.getList("siteFilters.keepInfo"));
            this.minQ = configRead.getDouble("siteFilters.minQ");
            this.minMeanDp = configRead.getDouble("siteFilters.minMeanDp");
            this.maxMeanDp = configRead.getDouble("siteFilters.maxMeanDp");
            this.maf = configRead.getDouble("siteFilters.maf");
            this.maxMaf = configRead.getDouble("siteFilters.maxMaf");
            this.nonRefAf = configRead.getDouble("siteFilters.nonRefAf");
            this.maxNonRefAf = configRead.getDouble("siteFilters.maxNonRefAf");
            this.mac = configRead.getInt("siteFilters.mac");
            this.maxMac = configRead.getInt("siteFilters.maxMac");
            this.nonRefAf = configRead.getDouble("siteFilters.nonRefAf");
            this.maxNonRefAc = configRead.getDouble("siteFilters.maxNonRefAc");
            this.hwe = configRead.getDouble("siteFilters.hwe");
            this.geno = configRead.getDouble("siteFilters.geno");
            this.maxMissingCount = configRead.getInt("siteFilters.maxMissingCount");
            this.minAlleles = configRead.getInt("siteFilters.minAlleles");
            this.maxAlleles = configRead.getInt("siteFilters.maxAlleles");
            this.thin = configRead.getInt("siteFilters.thin");
            this.mask = configRead.getString("siteFilters.mask");
            this.invertMask = configRead.getString("siteFilters.invertMask");
            this.maskMin = configRead.getInt("siteFilters.maskMin");
    
            /* Individual filters */       
            this.keepIndv = Misc.objListToStrArrayList(configRead.getList("individualFilters.keepIndv"));
            this.removeIndv = Misc.objListToStrArrayList(configRead.getList("individualFilters.removeIndv"));
            this.minIndvMeanDp = configRead.getDouble("individualFilters.minIndvMeanDp");
            this.maxIndvMeanDp = configRead.getDouble("individualFilters.maxIndvMeanDp");
            this.mind = configRead.getDouble("individualFilters.mind");
            this.phased = configRead.getBoolean("individualFilters.phased");
            this.maxIndv = configRead.getInt("individualFilters.maxIndv");
       
            /* Statistics */
            this.count = configRead.getBoolean("statistics.count");
            this.freq = configRead.getBoolean("statistics.freq");
            this.depth = configRead.getBoolean("statistics.depth");            
            
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
            /*
           
            TODO: only store values to xml, not the external files itself.
            
            */
            
            
            // open config object
            XMLConfiguration configCreate = new XMLConfiguration();
 
            // set filename and autoSave (we want to write all at once)
            configCreate.setFileName(filename);
            configCreate.setAutoSave(false);
            
            /* Site Filters */
//            configCreate.addProperty("siteFilters.chr", this.chr);
//            configCreate.addProperty("siteFilters.notChr", this.notChr);
//            configCreate.addProperty("siteFilters.fromBp", this.fromBp);
//            configCreate.addProperty("siteFilters.toBp", this.toBp);
            configCreate.addProperty("siteFilters.snp", this.snp);
            configCreate.addProperty("siteFilters.excludeSnp", this.excludeSnp);
            configCreate.addProperty("siteFilters.positions", this.positions);
            configCreate.addProperty("siteFilters.excludePositions", this.excludePositions);
//            configCreate.addProperty("siteFilters.keepOnlyIndels", this.keepOnlyIndels);
//            configCreate.addProperty("siteFilters.removeIndels", this.removeIndels);
            // todo: store bed data
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
            configCreate.addProperty("siteFilters.maxMissingCount", this.maxMissingCount);
            configCreate.addProperty("siteFilters.minAlleles", this.minAlleles);
            configCreate.addProperty("siteFilters.maxAlleles", this.maxAlleles);
            configCreate.addProperty("siteFilters.thin", this.thin);
            configCreate.addProperty("siteFilters.mask", this.mask);
            configCreate.addProperty("siteFilters.invertMask", this.invertMask);
            configCreate.addProperty("siteFilters.maskMin", this.invertMask);
            
            /* Individual filters */
            configCreate.addProperty("individualFilters.keepIndv", this.keepIndv);
            configCreate.addProperty("individualFilters.removeIndv", this.removeIndv);
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

    /**
     *
     * @return
     */
    
    public String getConfigFile() {
        return configFile;
    }

    /**
     *
     * @param configFile
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     *
     * @return
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     *
     * @param inputFile
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     *
     * @return
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     *
     * @param outputFile
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     *
     * @return
     */
    public Boolean isGzipped() {
        return gzipped;
    }

    /**
     *
     * @param gzipped
     */
    public void setGzipped(Boolean gzipped) {
        this.gzipped = gzipped;
    }

    /**
     *
     * @return
     */
    public MultiMap getChr() {
        return chr;
    }

    /**
     *
     * @param chr
     */
    public void setChr(MultiMap chr) {
        this.chr = chr;
    }

    /**
     *
     * @param chr
     */
    public void addChr(String chr) {
        this.chr.put(chr, null);
    }    

    /**
     *
     * @param chr
     * @param fromBp
     * @param toBp
     */
    public void addChr(String chr, int fromBp, int toBp) {
        this.chr.put(chr, Arrays.asList(fromBp, toBp));
    }

    /**
     *
     * @return
     */
    public MultiMap getNotChr() {
        return notChr;
    }

    /**
     *
     * @param notChr
     */
    public void setNotChr(MultiMap notChr) {
        this.notChr = notChr;
    }

    /**
     *
     * @param chr
     */
    public void addNotChr(String chr) {
        this.notChr.put(chr, null);
    }    

    /**
     *
     * @param chr
     * @param fromBp
     * @param toBp
     */
    public void addNotChr(String chr, int fromBp, int toBp) {
        this.notChr.put(chr, Arrays.asList(fromBp, toBp));
    } 

    /**
     *
     * @return
     */
    public ArrayList<String> getSnp() {
        return snp;
    }

    /**
     *
     * @param snp
     */
    public void setSnp(ArrayList<String> snp) {
        this.snp = snp;
    }

    /**
     *
     * @param snp
     */
    public void addSnp(ArrayList<String> snp) {
        this.snp.addAll(snp);
    }

    /**
     *
     * @param snp
     */
    public void addSnp(String snp) {
        this.snp.add(snp);
    }

    /**
     *
     * @return
     */
    public String getSnpFile() {
        return snpFile;
    }

    /**
     *
     * @param snpFile
     */
    public void setSnpFile(String snpFile) {
        this.snpFile = snpFile;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getExcludeSnp() {
        return excludeSnp;
    }

    /**
     *
     * @param excludeSnp
     */
    public void setExcludeSnp(ArrayList<String> excludeSnp) {
        this.excludeSnp = excludeSnp;
    }

    /**
     *
     * @param excludeSnp
     */
    public void addExcludeSnp(ArrayList<String> excludeSnp) {
        this.excludeSnp.addAll(excludeSnp);
    }

    /**
     *
     * @param excludeSnp
     */
    public void addExcludeSnp(String excludeSnp) {
        this.excludeSnp.add(excludeSnp);
    }

    /**
     *
     * @return
     */
    public String getExcludeSnpFile() {
        return excludeSnpFile;
    }

    /**
     *
     * @param excludeSnpFile
     */
    public void setExcludeSnpFile(String excludeSnpFile) {
        this.excludeSnpFile = excludeSnpFile;
    }

    /**
     *
     * @return
     */
    public MultiMap getPositions() {
        return positions;
    }

    /**
     *
     * @param positions
     */
    public void setPositions(MultiMap positions) {
        this.positions = positions;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addPositions(String key, Integer value) {
        this.positions.put(key, value);
    }
    
    /**
     * Returns if chromosome contains position
     * @param chr
     * @param position
     * @return 
     */
    public boolean containsPositions(String chr, int position) {
        ArrayList chrPos = (ArrayList) this.positions.get(chr);
        return chrPos.contains(position);
    }
    
    /**
     *
     * @return
     */
    public String getPositionsFile() {
        return positionsFile;
    }

    /**
     *
     * @param positionsFile
     */
    public void setPositionsFile(String positionsFile) {
        this.positionsFile = positionsFile;
    }

    /**
     *
     * @return
     */
    public MultiMap getExcludePositions() {
        return excludePositions;
    }

    /**
     *
     * @param excludePositions
     */
    public void setExcludePositions(MultiMap excludePositions) {
        this.excludePositions = excludePositions;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addExcludePositions(String key, Integer value) {
        this.excludePositions.put(key, value);
    }    

    /**
     * Returns if chromosome contains position
     * @param chr
     * @param position
     * @return 
     */
    public boolean containsExcludePositions(String chr, int position) {
        ArrayList chrPos = (ArrayList) this.excludePositions.get(chr);
        return chrPos.contains(position);
    }

    /**
     *
     * @return
     */
    public String getExcludePositionsFile() {
        return excludePositionsFile;
    }

    /**
     *
     * @param excludePositionsFile
     */
    public void setExcludePositionsFile(String excludePositionsFile) {
        this.excludePositionsFile = excludePositionsFile;
    }

    /**
     *
     * @return
     */
    public Boolean isKeepIndels() {
        return keepIndels;
    }

    /**
     *
     * @param keepIndels
     */
    public void setKeepIndels(Boolean keepIndels) {
        this.keepIndels = keepIndels;
    }

    /**
     *
     * @return
     */
    public String getBedFile() {
        return bedFile;
    }

    /**
     *
     * @param bedFile
     */
    public void setBedFile(String bedFile) {
        this.bedFile = bedFile;
    }

    /**
     *
     * @return
     */
    public String getExludeBedFile() {
        return exludeBedFile;
    }

    /**
     *
     * @param exludeBedFile
     */
    public void setExludeBedFile(String exludeBedFile) {
        this.exludeBedFile = exludeBedFile;
    }

    /**
     *
     * @return
     */
    public Boolean isRemoveFilteredAll() {
        return removeFilteredAll;
    }

    /**
     *
     * @param removeFilteredAll
     */
    public void setRemoveFilteredAll(Boolean removeFilteredAll) {
        this.removeFilteredAll = removeFilteredAll;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRemoveFiltered() {
        return removeFiltered;
    }

    /**
     *
     * @param removeFiltered
     */
    public void setRemoveFiltered(ArrayList<String> removeFiltered) {
        this.removeFiltered = removeFiltered;
    }

    /**
     *
     * @param removeFiltered
     */
    public void addRemoveFiltered(ArrayList<String> removeFiltered) {
        this.removeFiltered.addAll(removeFiltered);
    }

    /**
     *
     * @param removeFiltered
     */
    public void addRemoveFiltered(String removeFiltered) {
        this.removeFiltered.add(removeFiltered);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getKeepFiltered() {
        return keepFiltered;
    }

    /**
     *
     * @param keepFiltered
     */
    public void setKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    /**
     *
     * @param keepFiltered
     */
    public void addKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered.addAll(keepFiltered);
    }

    /**
     *
     * @param keepFiltered
     */
    public void addKeepFiltered(String keepFiltered) {
        this.keepFiltered.add(keepFiltered);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRemoveInfo() {
        return removeInfo;
    }

    /**
     *
     * @param removeInfo
     */
    public void setRemoveInfo(ArrayList<String> removeInfo) {
        this.removeInfo = removeInfo;
    }

    /**
     *
     * @param removeInfo
     */
    public void addRemoveInfo(ArrayList<String> removeInfo) {
        this.removeInfo.addAll(removeInfo);
    }

    /**
     *
     * @param removeInfo
     */
    public void addRemoveInfo(String removeInfo) {
        this.removeInfo.add(removeInfo);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getKeepInfo() {
        return keepInfo;
    }

    /**
     *
     * @param keepInfo
     */
    public void setKeepInfo(ArrayList<String> keepInfo) {
        this.keepInfo = keepInfo;
    }

    /**
     *
     * @param keepInfo
     */
    public void addKeepInfo(ArrayList<String> keepInfo) {
        this.keepInfo.addAll(keepInfo);
    }
    
    /**
     *
     * @param keepInfo
     */
    public void addKeepInfo(String keepInfo) {
        this.keepInfo.add(keepInfo);
    }

    /**
     *
     * @return
     */
    public Double getMinQ() {
        return minQ;
    }

    /**
     *
     * @param minQ
     */
    public void setMinQ(Double minQ) {
        this.minQ = minQ;
    }

    /**
     *
     * @return
     */
    public Double getMinMeanDp() {
        return minMeanDp;
    }

    /**
     *
     * @param minMeanDp
     */
    public void setMinMeanDp(Double minMeanDp) {
        this.minMeanDp = minMeanDp;
    }

    /**
     *
     * @return
     */
    public Double getMaxMeanDp() {
        return maxMeanDp;
    }

    /**
     *
     * @param maxMeanDp
     */
    public void setMaxMeanDp(Double maxMeanDp) {
        this.maxMeanDp = maxMeanDp;
    }

    /**
     *
     * @return
     */
    public Double getMaf() {
        return maf;
    }

    /**
     *
     * @param maf
     */
    public void setMaf(Double maf) {
        this.maf = maf;
    }

    /**
     *
     * @return
     */
    public Double getMaxMaf() {
        return maxMaf;
    }

    /**
     *
     * @param maxMaf
     */
    public void setMaxMaf(Double maxMaf) {
        this.maxMaf = maxMaf;
    }

    /**
     *
     * @return
     */
    public Double getNonRefAf() {
        return nonRefAf;
    }

    /**
     *
     * @param nonRefAf
     */
    public void setNonRefAf(Double nonRefAf) {
        this.nonRefAf = nonRefAf;
    }

    /**
     *
     * @return
     */
    public Double getMaxNonRefAf() {
        return maxNonRefAf;
    }

    /**
     *
     * @param maxNonRefAf
     */
    public void setMaxNonRefAf(Double maxNonRefAf) {
        this.maxNonRefAf = maxNonRefAf;
    }

    /**
     *
     * @return
     */
    public Integer getMac() {
        return mac;
    }

    /**
     *
     * @param mac
     */
    public void setMac(Integer mac) {
        this.mac = mac;
    }

    /**
     *
     * @return
     */
    public Integer getMaxMac() {
        return maxMac;
    }

    /**
     *
     * @param maxMac
     */
    public void setMaxMac(Integer maxMac) {
        this.maxMac = maxMac;
    }

    /**
     *
     * @return
     */
    public Double getNonRefAc() {
        return nonRefAc;
    }

    /**
     *
     * @param nonRefAc
     */
    public void setNonRefAc(Double nonRefAc) {
        this.nonRefAc = nonRefAc;
    }

    /**
     *
     * @return
     */
    public Double getMaxNonRefAc() {
        return maxNonRefAc;
    }

    /**
     *
     * @param maxNonRefAc
     */
    public void setMaxNonRefAc(Double maxNonRefAc) {
        this.maxNonRefAc = maxNonRefAc;
    }

    /**
     *
     * @return
     */
    public Double getHwe() {
        return hwe;
    }

    /**
     *
     * @param hwe
     */
    public void setHwe(Double hwe) {
        this.hwe = hwe;
    }

    /**
     *
     * @return
     */
    public Double getGeno() {
        return geno;
    }

    /**
     *
     * @param geno
     */
    public void setGeno(Double geno) {
        this.geno = geno;
    }

    /**
     *
     * @return
     */
    public Integer getMaxMissingCount() {
        return maxMissingCount;
    }

    /**
     *
     * @param maxMissingCount
     */
    public void setMaxMissingCount(Integer maxMissingCount) {
        this.maxMissingCount = maxMissingCount;
    }

    /**
     *
     * @return
     */
    public Integer getMinAlleles() {
        return minAlleles;
    }

    /**
     *
     * @param minAlleles
     */
    public void setMinAlleles(Integer minAlleles) {
        this.minAlleles = minAlleles;
    }

    /**
     *
     * @return
     */
    public Integer getMaxAlleles() {
        return maxAlleles;
    }

    /**
     *
     * @param maxAlleles
     */
    public void setMaxAlleles(Integer maxAlleles) {
        this.maxAlleles = maxAlleles;
    }

    /**
     *
     * @return
     */
    public Integer getThin() {
        return thin;
    }

    /**
     *
     * @param thin
     */
    public void setThin(Integer thin) {
        this.thin = thin;
    }

    /**
     *
     * @return
     */
    public String getMask() {
        return mask;
    }

    /**
     *
     * @param mask
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     *
     * @return
     */
    public String getInvertMask() {
        return invertMask;
    }

    /**
     *
     * @param invertMask
     */
    public void setInvertMask(String invertMask) {
        this.invertMask = invertMask;
    }

    /**
     *
     * @return
     */
    public Integer getMaskMin() {
        return maskMin;
    }

    /**
     *
     * @param maskMin
     */
    public void setMaskMin(Integer maskMin) {
        this.maskMin = maskMin;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getKeepIndv() {
        return keepIndv;
    }

    /**
     *
     * @param keepIndv
     */
    public void setKeepIndv(ArrayList<String> keepIndv) {
        this.keepIndv = keepIndv;
    }

    /**
     *
     * @param keepIndv
     */
    public void addKeepIndv(ArrayList<String> keepIndv) {
        this.keepIndv.addAll(keepIndv);
    }
    
    /**
     *
     * @param keepIndv
     */
    public void addKeepIndv(String keepIndv) {
        this.keepIndv.add(keepIndv);
    }

    /**
     *
     * @return
     */
    public String getKeepIndvFile() {
        return keepIndvFile;
    }

    /**
     *
     * @param keepIndvFile
     */
    public void setKeepIndvFile(String keepIndvFile) {
        this.keepIndvFile = keepIndvFile;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRemoveIndv() {
        return removeIndv;
    }

    /**
     *
     * @param removeIndv
     */
    public void setRemoveIndv(ArrayList<String> removeIndv) {
        this.removeIndv = removeIndv;
    }

    /**
     *
     * @param removeIndv
     */
    public void addRemoveIndv(ArrayList<String> removeIndv) {
        this.removeIndv.addAll(removeIndv);
    }
    
    /**
     *
     * @param removeIndv
     */
    public void addRemoveIndv(String removeIndv) {
        this.removeIndv.add(removeIndv);
    }    

    /**
     *
     * @return
     */
    public String getRemoveIndvFile() {
        return removeIndvFile;
    }

    /**
     *
     * @param removeIndvFile
     */
    public void setRemoveIndvFile(String removeIndvFile) {
        this.removeIndvFile = removeIndvFile;
    }

    /**
     *
     * @return
     */
    public Double getMinIndvMeanDp() {
        return minIndvMeanDp;
    }

    /**
     *
     * @param minIndvMeanDp
     */
    public void setMinIndvMeanDp(Double minIndvMeanDp) {
        this.minIndvMeanDp = minIndvMeanDp;
    }

    /**
     *
     * @return
     */
    public Double getMaxIndvMeanDp() {
        return maxIndvMeanDp;
    }

    /**
     *
     * @param maxIndvMeanDp
     */
    public void setMaxIndvMeanDp(Double maxIndvMeanDp) {
        this.maxIndvMeanDp = maxIndvMeanDp;
    }

    /**
     *
     * @return
     */
    public Double getMind() {
        return mind;
    }

    /**
     *
     * @param mind
     */
    public void setMind(Double mind) {
        this.mind = mind;
    }

    /**
     *
     * @return
     */
    public Boolean isPhased() {
        return phased;
    }

    /**
     *
     * @param phased
     */
    public void setPhased(Boolean phased) {
        this.phased = phased;
    }

    /**
     *
     * @return
     */
    public Integer getMaxIndv() {
        return maxIndv;
    }

    /**
     *
     * @param maxIndv
     */
    public void setMaxIndv(Integer maxIndv) {
        this.maxIndv = maxIndv;
    }

    /**
     *
     * @return
     */
    public Boolean isCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(Boolean count) {
        this.count = count;
    }

    /**
     *
     * @return
     */
    public Boolean isFreq() {
        return freq;
    }

    /**
     *
     * @param freq
     */
    public void setFreq(Boolean freq) {
        this.freq = freq;
    }

    /**
     *
     * @return
     */
    public Boolean isDepth() {
        return depth;
    }

    /**
     *
     * @param depth
     */
    public void setDepth(Boolean depth) {
        this.depth = depth;
    }


}
