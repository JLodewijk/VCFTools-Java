/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.bioinf.vcftools.filehandlers.BedFileReader;
import nl.bioinf.vcftools.filehandlers.PositionFileReader;
import nl.bioinf.vcftools.filehandlers.SeparatedValueReader;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Settings class
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

    private List<String> snp;
    private List<String> excludeSnp;
    private MultiMap positions;
    private MultiMap excludePositions;
    private Boolean keepIndels;
    private Boolean removeFilteredAll;
    private List<String> removeFiltered;
    private List<String> keepFiltered;
    private List<String> removeInfo;
    private List<String> keepInfo;
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
    private MultiMap mask;
    private MultiMap invertMask;
    private Integer maskMin;

    /* Individual filters */
    private List<String> keepIndv;
    private List<String> removeIndv;
    private Double minIndvMeanDp;
    private Double maxIndvMeanDp;
    private Double mind;
    private Boolean phased;
    private Integer maxIndv;
    
    /* Genotype filters */
    private Boolean removeFilteredGenoAll;
    private List<String> removeFilteredGeno;
    private Double minGq;
    private Double minDp;
    private Double maxDp;
      
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
        this.removeFiltered = new ArrayList<>();
        this.keepFiltered = new ArrayList<>();
        this.removeInfo = new ArrayList<>();
        this.keepInfo = new ArrayList<>();
        this.mask = new MultiValueMap();
        this.invertMask = new MultiValueMap();
        this.keepIndv = new ArrayList<>();
        this.removeIndv = new ArrayList<>();
        this.removeFilteredGeno = new ArrayList<>();
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
//            this.mask = configRead.getString("siteFilters.mask");
//            this.invertMask = configRead.getString("siteFilters.invertMask");
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
     * Pre set the configuration file to use with a load()
     * @param configFile
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Get the input VCF file
     * @return filename
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * Set the input VCF file
     * @param inputFile filename
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Get the output VCF file
     * @return filename
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Set the output VCF file
     * @param outputFile filename
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Is the input VCF file gzipped
     * @return true when it is false when not
     */
    public Boolean isGzipped() {
        return gzipped;
    }

    /**
     * Set if the input VCF file gzipped
     * @param gzipped true when it is false when not
     */
    public void setGzipped(Boolean gzipped) {
        this.gzipped = gzipped;
    }

    /**
     * Get the collection of chromosomes to include
     * @return MultiMap of chromosomes
     */
    public MultiMap getChr() {
        return chr;
    }

    /**
     * Set the collection of chromosomes to include
     * @param chr MultiMap of chromosomes
     */
    public void setChr(MultiMap chr) {
        this.chr = chr;
    }

    /**
     * Add a chromosome to include
     * @param chr Chromosome
     */
    public void addChr(String chr) {
        this.chr.put(chr, null);
    }    

    /**
     * Add a chromosome to include
     * @param chr Chromosome
     * @param fromBp From base pair
     * @param toBp To base pair
     */
    public void addChr(String chr, int fromBp, int toBp) {
        this.chr.put(chr, Arrays.asList(fromBp, toBp));
    }

    /**
     * Get the collection of chromosomes to exclude
     * @return MultiMap of chromosomes
     */
    public MultiMap getNotChr() {
        return notChr;
    }

    /**
     * Set the collection of chromosomes to exclude
     * @param notChr MultiMap of chromosomes
     */
    public void setNotChr(MultiMap notChr) {
        this.notChr = notChr;
    }

    /**
     * Add a chromosome to exclude
     * @param chr Chromosome
     */
    public void addNotChr(String chr) {
        this.notChr.put(chr, null);
    }    

    /**
     * Add a chromosome to exclude
     * @param chr Chromosome
     * @param fromBp From base pair
     * @param toBp To base pair
     */
    public void addNotChr(String chr, int fromBp, int toBp) {
        this.notChr.put(chr, Arrays.asList(fromBp, toBp));
    } 

    /**
     * Get the list of snp's to include
     * @return list of snp's
     */
    public List<String> getSnp() {
        return snp;
    }

    /**
     * Set the list of snp's to include
     * @param snp list of snp's
     */
    public void setSnp(List<String> snp) {
        this.snp = snp;
    }

    /**
     * Add a list of snp's to include
     * @param snp list of snp's
     */
    public void addSnp(List<String> snp) {
        this.snp.addAll(snp);
    }

    /**
     * Add a snp to include
     * @param snp snp
     */
    public void addSnp(String snp) {
        this.snp.add(snp);
    }

    /**
     *  Load snp file data
     * @param snpFile filename
     */
    public void loadSnpFile(String snpFile) {
        // adding one by one so this functions allows for multiple files to load
        SeparatedValueReader reader = new SeparatedValueReader(snpFile,System.lineSeparator());
        List snps = reader.getList();
        for (Object snp : snps) {
            this.addSnp((String) snp);
        }
    }

    /**
     * Get the list of snp's to exclude
     * @return list of snp's
     */
    public List<String> getExcludeSnp() {
        return excludeSnp;
    }

    /**
     * Set the list of snp's to exclude
     * @param excludeSnp list of snp's
     */
    public void setExcludeSnp(List<String> excludeSnp) {
        this.excludeSnp = excludeSnp;
    }

    /**
     * Add a list of snp's to exclude
     * @param excludeSnp list of snp's
     */
    public void addExcludeSnp(List<String> excludeSnp) {
        this.excludeSnp.addAll(excludeSnp);
    }

    /**
     * Add a snp to exclude
     * @param excludeSnp snp
     */
    public void addExcludeSnp(String excludeSnp) {
        this.excludeSnp.add(excludeSnp);
    }

    /**
     * Load exclude snp file data
     * @param excludeSnpFile filename
     */
    public void loadExcludeSnpFile(String excludeSnpFile) {
        // adding one by one so this functions allows for multiple files to load
        SeparatedValueReader reader = new SeparatedValueReader(excludeSnpFile,System.lineSeparator());
        List snps = reader.getList();
        for (Object snp : snps) {
            this.addExcludeSnp((String) snp);
        }
    }

    /**
     * Get the collection of positions to include
     * @return MultiMap of chromosome and position combinations
     */
    public MultiMap getPositions() {
        return positions;
    }

    /**
     * Set a collection of positions to include
     * @param positions
     */
    public void setPositions(MultiMap positions) {
        this.positions = positions;
    }

    /**
     * Add a position to include
     * @param chr Chromosome
     * @param position Position
     */
    public void addPositions(String chr, Integer position) {
        this.positions.put(chr, position);
    }
    
    /**
     * Returns if chromosome contains position to include
     * @param chr
     * @param position
     * @return True when it contains the request
     */
    public boolean containsPositions(String chr, int position) {
        List chrPos = (List) this.positions.get(chr);
        return chrPos.contains(position);
    }    

    /**
     * Load a positions file to include
     * @param positionsFile filename
     */
    public void loadPositionsFile(String positionsFile) {
        // adding one by one so this functions allows for multiple files to load
//        PositionFileReader positionFileReader = new PositionFileReader(positionsFile);
//        MultiMap positions = positionFileReader.getChrPositionMap();
//        Iterator keyIterator = positions.mapIterator();
//        while (keyIterator.hasNext()) {
//            Object key = keyIterator.next();
//            for (Object value:(Collection) positions.get(key)) {
//                System.out.println(value);
//            }
//            
//        }
        
    }

    /**
     * Get the collection of positions to exclude
     * @return MultiMap of chromosome and position combinations
     */
    public MultiMap getExcludePositions() {
        return excludePositions;
    }

    /**
     * Set a collection of positions to exclude
     * @param excludePositions
     */
    public void setExcludePositions(MultiMap excludePositions) {
        this.excludePositions = excludePositions;
    }

    /**
     * Add a position to exclude
     * @param chr
     * @param position
     */
    public void addExcludePositions(String chr, Integer position) {
        this.excludePositions.put(chr, position);
    }    

    /**
     * Returns if chromosome contains position to exclude
     * @param chr
     * @param position
     * @return True when it contains the request
     */
    public boolean containsExcludePositions(String chr, int position) {
        List chrPos = (List) this.excludePositions.get(chr);
        return chrPos.contains(position);
    }

    /**
     * Load a positions file to exclude
     * @param excludePositionsFile
     */
    public void loadExcludePositionsFile(String excludePositionsFile) {
        //this.excludePositionsFile = excludePositionsFile;
    }

    /**
     *  Do we want to keep indels
     * @return True when we want to keep indels
     */
    public Boolean isKeepIndels() {
        return keepIndels;
    }

    /**
     * Set if we want to keep indels
     * @param keepIndels Set True when we want to keep indels
     */
    public void setKeepIndels(Boolean keepIndels) {
        this.keepIndels = keepIndels;
    } 

    /**
     *
     * @param bedFile
     */
    public void loadBedFile(String bedFile) {
        // adding one by one so this functions allows for multiple files to load
        BedFileReader bedFileReader = new BedFileReader(bedFile);
        MultiMap bedData = bedFileReader.getBedMap();
        for (Object key : bedData.keySet()) {
            for (Object value:(Collection) bedData.get(key)) {
                List<String> positions = (List) value;
                this.addChr((String) key, Integer.parseInt(positions.get(0)), Integer.parseInt(positions.get(1)));
            }   
        }
    }


    /**
     *
     * @param exludeBedFile
     */
    public void loadExludeBedFile(String exludeBedFile) {
        //this.exludeBedFile = exludeBedFile;
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
    public List<String> getRemoveFiltered() {
        return removeFiltered;
    }

    /**
     *
     * @param removeFiltered
     */
    public void setRemoveFiltered(List<String> removeFiltered) {
        this.removeFiltered = removeFiltered;
    }

    /**
     *
     * @param removeFiltered
     */
    public void addRemoveFiltered(List<String> removeFiltered) {
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
    public List<String> getKeepFiltered() {
        return keepFiltered;
    }

    /**
     *
     * @param keepFiltered
     */
    public void setKeepFiltered(List<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    /**
     *
     * @param keepFiltered
     */
    public void addKeepFiltered(List<String> keepFiltered) {
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
    public List<String> getRemoveInfo() {
        return removeInfo;
    }

    /**
     *
     * @param removeInfo
     */
    public void setRemoveInfo(List<String> removeInfo) {
        this.removeInfo = removeInfo;
    }

    /**
     *
     * @param removeInfo
     */
    public void addRemoveInfo(List<String> removeInfo) {
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
    public List<String> getKeepInfo() {
        return keepInfo;
    }

    /**
     *
     * @param keepInfo
     */
    public void setKeepInfo(List<String> keepInfo) {
        this.keepInfo = keepInfo;
    }

    /**
     *
     * @param keepInfo
     */
    public void addKeepInfo(List<String> keepInfo) {
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
     * @param maskFile
     */
    public void loadMaskFile(String maskFile) {
        //this.maskFile = maskFile;
    }

    /**
     *
     * @return
     */
    public MultiMap getMask() {
        return mask;
    }

    /**
     *
     * @param mask
     */
    public void setMask(MultiMap mask) {
        this.mask = mask;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addMask(String key, int value) {
        this.mask.put(key, value);
    }

    /**
     *
     * @param invertMaskFile
     */
    public void loadInvertMaskFile(String invertMaskFile) {
        //this.invertMaskFile = invertMaskFile;
    }

    /**
     *
     * @return
     */
    public MultiMap getInvertMask() {
        return invertMask;
    }

    /**
     *
     * @param invertMask
     */
    public void setInvertMask(MultiMap invertMask) {
        this.invertMask = invertMask;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addInvertMask(String key, int value) {
        this.invertMask.put(key, value);
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
    public List<String> getKeepIndv() {
        return keepIndv;
    }

    /**
     *
     * @param keepIndv
     */
    public void setKeepIndv(List<String> keepIndv) {
        this.keepIndv = keepIndv;
    }

    /**
     *
     * @param keepIndv
     */
    public void addKeepIndv(List<String> keepIndv) {
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
     * @param keepIndvFile
     */
    public void loadKeepIndvFile(String keepIndvFile) {
        // adding one by one so this functions allows for multiple files to load
        SeparatedValueReader reader = new SeparatedValueReader(keepIndvFile,System.lineSeparator());
        List indvs = reader.getList();
        for (Object indv : indvs) {
            this.addKeepIndv((String) indv);
        }
    }

    /**
     *
     * @return
     */
    public List<String> getRemoveIndv() {
        return removeIndv;
    }

    /**
     *
     * @param removeIndv
     */
    public void setRemoveIndv(List<String> removeIndv) {
        this.removeIndv = removeIndv;
    }

    /**
     *
     * @param removeIndv
     */
    public void addRemoveIndv(List<String> removeIndv) {
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
     * @param removeIndvFile
     */
    public void loadRemoveIndvFile(String removeIndvFile) {
        // adding one by one so this functions allows for multiple files to load
        SeparatedValueReader reader = new SeparatedValueReader(removeIndvFile,System.lineSeparator());
        List indvs = reader.getList();
        for (Object indv : indvs) {
            this.addRemoveIndv((String) indv);
        }
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

    /**
     *
     * @return
     */
    public Boolean isRemoveFilteredGenoAll() {
        return removeFilteredGenoAll;
    }

    /**
     *
     * @param removeFilteredGenoAll
     */
    public void setRemoveFilteredGenoAll(Boolean removeFilteredGenoAll) {
        this.removeFilteredGenoAll = removeFilteredGenoAll;
    }

    /**
     *
     * @return
     */
    public List<String> getRemoveFilteredGeno() {
        return removeFilteredGeno;
    }

    /**
     *
     * @param removeGenoFiltered
     */
    public void setRemoveFilteredGeno(List<String> removeGenoFiltered) {
        this.removeFilteredGeno = removeGenoFiltered;
    }
    
    /**
     *
     * @param removeGenoFiltered
     */
    public void addRemoveFilteredGeno(String removeGenoFiltered) {
        this.removeFilteredGeno.add(removeGenoFiltered);
    }

    /**
     *
     * @return
     */
    public Double getMinGq() {
        return minGq;
    }

    /**
     *
     * @param minGq
     */
    public void setMinGq(Double minGq) {
        this.minGq = minGq;
    }

    /**
     *
     * @return
     */
    public Double getMinDp() {
        return minDp;
    }

    /**
     *
     * @param minDp
     */
    public void setMinDp(Double minDp) {
        this.minDp = minDp;
    }

    /**
     *
     * @return
     */
    public Double getMaxDp() {
        return maxDp;
    }

    /**
     *
     * @param maxDp
     */
    public void setMaxDp(Double maxDp) {
        this.maxDp = maxDp;
    }
    

}
