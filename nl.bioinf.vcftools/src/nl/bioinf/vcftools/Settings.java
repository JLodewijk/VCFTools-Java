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
    private ArrayList<String> chr;
    private ArrayList<String> notChr;
    private Integer fromBp;
    private Integer toBp;
    private ArrayList<String> snp;
    private String snpFile;
    private ArrayList<String> excludeSnp;
    private String excludeSnpFile;
    private MultiMap positions;
    private String positionsFile;
    private MultiMap excludePositions;
    private String excludePositionsFile;
    private Boolean keepOnlyIndels;
    private Boolean removeIndels;
    private String bedFile;
    private String exludeBedFile;
    //private Bed bed;
    //private Bed exludeBed;
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
        this.chr = new ArrayList<>();
        this.notChr = new ArrayList<>();
        this.snp = new ArrayList<>();
        this.excludeSnp = new ArrayList<>();
        this.positions = new MultiValueMap();
        this.excludePositions = new MultiValueMap();
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
            this.chr = Misc.objListToStrArrayList(configRead.getList("siteFilters.chr"));
            this.notChr = Misc.objListToStrArrayList(configRead.getList("siteFilters.notChr"));
            this.fromBp = configRead.getInt("siteFilters.fromBp");
            this.toBp = configRead.getInt("siteFilters.toBp");
            this.snp = Misc.objListToStrArrayList(configRead.getList("siteFilters.snp"));
            this.excludeSnp = Misc.objListToStrArrayList(configRead.getList("siteFilters.excludeSnp"));
            // this.positions = Misc.objListToIntegerArrayList(configRead.getList("siteFilters.positions")); // fix for multimap needed
            // this.excludePositions = Misc.objListToIntegerArrayList(configRead.getList("siteFilters.excludePositions"));  // multimap fix
            this.keepOnlyIndels = configRead.getBoolean("siteFilters.keepOnlyIndels");
            this.removeIndels = configRead.getBoolean("siteFilters.removeIndels");
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
            configCreate.addProperty("siteFilters.chr", this.chr);
            configCreate.addProperty("siteFilters.notChr", this.notChr);
            configCreate.addProperty("siteFilters.fromBp", this.fromBp);
            configCreate.addProperty("siteFilters.toBp", this.toBp);
            configCreate.addProperty("siteFilters.snp", this.snp);
            configCreate.addProperty("siteFilters.excludeSnp", this.excludeSnp);
            configCreate.addProperty("siteFilters.positions", this.positions);
            configCreate.addProperty("siteFilters.excludePositions", this.excludePositions);
            configCreate.addProperty("siteFilters.keepOnlyIndels", this.keepOnlyIndels);
            configCreate.addProperty("siteFilters.removeIndels", this.removeIndels);
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
    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public Boolean isGzipped() {
        return gzipped;
    }

    public void setGzipped(Boolean gzipped) {
        this.gzipped = gzipped;
    }

    public ArrayList<String> getChr() {
        return chr;
    }

    public void setChr(ArrayList<String> chr) {
        this.chr = chr;
    }

    public void addChr(ArrayList<String> chr) {
        this.chr.addAll(chr);
    }   
    
    public void addChr(String chr) {
        this.chr.add(chr);
    }
    
    public ArrayList<String> getNotChr() {
        return notChr;
    }

    public void setNotChr(ArrayList<String> notChr) {
        this.notChr = notChr;
    }

    public void addNotChr(ArrayList<String> notChr) {
        this.notChr.addAll(notChr);
    }    
    
    public void addNotChr(String notChr) {
        this.notChr.add(notChr);
    }
    
    public Integer getFromBp() {
        return fromBp;
    }

    public void setFromBp(Integer fromBp) {
        this.fromBp = fromBp;
    }

    public Integer getToBp() {
        return toBp;
    }

    public void setToBp(Integer toBp) {
        this.toBp = toBp;
    }

    public ArrayList<String> getSnp() {
        return snp;
    }

    public void setSnp(ArrayList<String> snp) {
        this.snp = snp;
    }

    public void addSnp(ArrayList<String> snp) {
        this.snp.addAll(snp);
    }    
    
    public void addSnp(String snp) {
        this.snp.add(snp);
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

    public void addExcludeSnp(ArrayList<String> excludeSnp) {
        this.excludeSnp.addAll(excludeSnp);
    }   
    
    public void addExcludeSnp(String excludeSnp) {
        this.excludeSnp.add(excludeSnp);
    } 
    
    public String getExcludeSnpFile() {
        return excludeSnpFile;
    }

    public void setExcludeSnpFile(String excludeSnpFile) {
        this.excludeSnpFile = excludeSnpFile;
    }

    public MultiMap getPositions() {
        return positions;
    }

    public void setPositions(MultiMap positions) {
        this.positions = positions;
    }

    public void addPositions(String key, Integer value) {
        this.positions.put(key, value);
    }
    
    public String getPositionsFile() {
        return positionsFile;
    }

    public void setPositionsFile(String positionsFile) {
        this.positionsFile = positionsFile;
    }

    public MultiMap getExcludePositions() {
        return excludePositions;
    }

    public void setExcludePositions(MultiMap excludePositions) {
        this.excludePositions = excludePositions;
    }

    public void addExcludePositions(String key, Integer value) {
        this.excludePositions.put(key, value);
    }    
    
    public String getExcludePositionsFile() {
        return excludePositionsFile;
    }

    public void setExcludePositionsFile(String excludePositionsFile) {
        this.excludePositionsFile = excludePositionsFile;
    }

    public Boolean isKeepOnlyIndels() {
        return keepOnlyIndels;
    }

    public void setKeepOnlyIndels(Boolean keepOnlyIndels) {
        this.keepOnlyIndels = keepOnlyIndels;
    }

    public Boolean isRemoveIndels() {
        return removeIndels;
    }

    public void setRemoveIndels(Boolean removeIndels) {
        this.removeIndels = removeIndels;
    }

    public String getBedFile() {
        return bedFile;
    }

    public void setBedFile(String bedFile) {
        this.bedFile = bedFile;
    }

    public String getExludeBedFile() {
        return exludeBedFile;
    }

    public void setExludeBedFile(String exludeBedFile) {
        this.exludeBedFile = exludeBedFile;
    }

    public Boolean isRemoveFilteredAll() {
        return removeFilteredAll;
    }

    public void setRemoveFilteredAll(Boolean removeFilteredAll) {
        this.removeFilteredAll = removeFilteredAll;
    }

    public ArrayList<String> getRemoveFiltered() {
        return removeFiltered;
    }

    public void setRemoveFiltered(ArrayList<String> removeFiltered) {
        this.removeFiltered = removeFiltered;
    }

    public void addRemoveFiltered(ArrayList<String> removeFiltered) {
        this.removeFiltered.addAll(removeFiltered);
    }    
    
    public void addRemoveFiltered(String removeFiltered) {
        this.removeFiltered.add(removeFiltered);
    }    
    
    public ArrayList<String> getKeepFiltered() {
        return keepFiltered;
    }

    public void setKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    public void addKeepFiltered(ArrayList<String> keepFiltered) {
        this.keepFiltered.addAll(keepFiltered);
    }    
    
    public void addKeepFiltered(String keepFiltered) {
        this.keepFiltered.add(keepFiltered);
    }    
    
    public ArrayList<String> getRemoveInfo() {
        return removeInfo;
    }

    public void setRemoveInfo(ArrayList<String> removeInfo) {
        this.removeInfo = removeInfo;
    }

    public void addRemoveInfo(ArrayList<String> removeInfo) {
        this.removeInfo.addAll(removeInfo);
    }    
    
    public void addRemoveInfo(String removeInfo) {
        this.removeInfo.add(removeInfo);
    }    
    
    public ArrayList<String> getKeepInfo() {
        return keepInfo;
    }

    public void setKeepInfo(ArrayList<String> keepInfo) {
        this.keepInfo = keepInfo;
    }

    public void addKeepInfo(ArrayList<String> keepInfo) {
        this.keepInfo.addAll(keepInfo);
    }
    
    public void addKeepInfo(String keepInfo) {
        this.keepInfo.add(keepInfo);
    }   
    
    public Double getMinQ() {
        return minQ;
    }

    public void setMinQ(Double minQ) {
        this.minQ = minQ;
    }

    public Double getMinMeanDp() {
        return minMeanDp;
    }

    public void setMinMeanDp(Double minMeanDp) {
        this.minMeanDp = minMeanDp;
    }

    public Double getMaxMeanDp() {
        return maxMeanDp;
    }

    public void setMaxMeanDp(Double maxMeanDp) {
        this.maxMeanDp = maxMeanDp;
    }

    public Double getMaf() {
        return maf;
    }

    public void setMaf(Double maf) {
        this.maf = maf;
    }

    public Double getMaxMaf() {
        return maxMaf;
    }

    public void setMaxMaf(Double maxMaf) {
        this.maxMaf = maxMaf;
    }

    public Double getNonRefAf() {
        return nonRefAf;
    }

    public void setNonRefAf(Double nonRefAf) {
        this.nonRefAf = nonRefAf;
    }

    public Double getMaxNonRefAf() {
        return maxNonRefAf;
    }

    public void setMaxNonRefAf(Double maxNonRefAf) {
        this.maxNonRefAf = maxNonRefAf;
    }

    public Integer getMac() {
        return mac;
    }

    public void setMac(Integer mac) {
        this.mac = mac;
    }

    public Integer getMaxMac() {
        return maxMac;
    }

    public void setMaxMac(Integer maxMac) {
        this.maxMac = maxMac;
    }

    public Double getNonRefAc() {
        return nonRefAc;
    }

    public void setNonRefAc(Double nonRefAc) {
        this.nonRefAc = nonRefAc;
    }

    public Double getMaxNonRefAc() {
        return maxNonRefAc;
    }

    public void setMaxNonRefAc(Double maxNonRefAc) {
        this.maxNonRefAc = maxNonRefAc;
    }

    public Double getHwe() {
        return hwe;
    }

    public void setHwe(Double hwe) {
        this.hwe = hwe;
    }

    public Double getGeno() {
        return geno;
    }

    public void setGeno(Double geno) {
        this.geno = geno;
    }

    public Integer getMaxMissingCount() {
        return maxMissingCount;
    }

    public void setMaxMissingCount(Integer maxMissingCount) {
        this.maxMissingCount = maxMissingCount;
    }

    public Integer getMinAlleles() {
        return minAlleles;
    }

    public void setMinAlleles(Integer minAlleles) {
        this.minAlleles = minAlleles;
    }

    public Integer getMaxAlleles() {
        return maxAlleles;
    }

    public void setMaxAlleles(Integer maxAlleles) {
        this.maxAlleles = maxAlleles;
    }

    public Integer getThin() {
        return thin;
    }

    public void setThin(Integer thin) {
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

    public Integer getMaskMin() {
        return maskMin;
    }

    public void setMaskMin(Integer maskMin) {
        this.maskMin = maskMin;
    }

    public ArrayList<String> getKeepIndv() {
        return keepIndv;
    }

    public void setKeepIndv(ArrayList<String> keepIndv) {
        this.keepIndv = keepIndv;
    }

    public void addKeepIndv(ArrayList<String> keepIndv) {
        this.keepIndv.addAll(keepIndv);
    }
    
    public void addKeepIndv(String keepIndv) {
        this.keepIndv.add(keepIndv);
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

    public void addRemoveIndv(ArrayList<String> removeIndv) {
        this.removeIndv.addAll(removeIndv);
    }
    
    public void addRemoveIndv(String removeIndv) {
        this.removeIndv.add(removeIndv);
    }    

    
    public String getRemoveIndvFile() {
        return removeIndvFile;
    }

    public void setRemoveIndvFile(String removeIndvFile) {
        this.removeIndvFile = removeIndvFile;
    }

    public Double getMinIndvMeanDp() {
        return minIndvMeanDp;
    }

    public void setMinIndvMeanDp(Double minIndvMeanDp) {
        this.minIndvMeanDp = minIndvMeanDp;
    }

    public Double getMaxIndvMeanDp() {
        return maxIndvMeanDp;
    }

    public void setMaxIndvMeanDp(Double maxIndvMeanDp) {
        this.maxIndvMeanDp = maxIndvMeanDp;
    }

    public Double getMind() {
        return mind;
    }

    public void setMind(Double mind) {
        this.mind = mind;
    }

    public Boolean isPhased() {
        return phased;
    }

    public void setPhased(Boolean phased) {
        this.phased = phased;
    }

    public Integer getMaxIndv() {
        return maxIndv;
    }

    public void setMaxIndv(Integer maxIndv) {
        this.maxIndv = maxIndv;
    }

    public Boolean isCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public Boolean isFreq() {
        return freq;
    }

    public void setFreq(Boolean freq) {
        this.freq = freq;
    }

    public Boolean isDepth() {
        return depth;
    }

    public void setDepth(Boolean depth) {
        this.depth = depth;
    }


}
