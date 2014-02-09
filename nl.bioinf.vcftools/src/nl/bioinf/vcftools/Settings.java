/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import nl.bioinf.vcftools.filehandlers.BedFileReader;
import nl.bioinf.vcftools.filehandlers.MaskFileReader;
import nl.bioinf.vcftools.filehandlers.PositionFileReader;
import nl.bioinf.vcftools.filehandlers.SeparatedValueReader;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 * Settings class
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class Settings {
    private static final Settings instance = new Settings();
    
    
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
     * Get a settings instance
     * @return instance
     */
    public static Settings getInstance() {
        return instance;
    }
    
    /**
     * This constructor will set defaultConfig.xml as configuration file and load it.
     */
    private Settings() {
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
        // loop trough items and add to snp list
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
        // loop trough items and add to excluseSnp list
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
        PositionFileReader positionFileReader = new PositionFileReader(positionsFile);
        MultiMap positions = positionFileReader.getChrPositionMap();
        // Loop trough key and values, and add to chr collection
        for (Object key : positions.keySet()) {
            for (Object value:(Collection) positions.get(key)) {
                this.addPositions((String) key, (Integer) value);
            }   
        }     
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
        // adding one by one so this functions allows for multiple files to load
        PositionFileReader positionFileReader = new PositionFileReader(excludePositionsFile);
        MultiMap positions = positionFileReader.getChrPositionMap();
        // Loop trough key and values, and add to chr collection
        for (Object key : positions.keySet()) {
            for (Object value:(Collection) positions.get(key)) {
                this.addExcludePositions((String) key, (Integer) value);
            }   
        } 
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
     * Load a bed file
     * @param bedFile
     */
    public void loadBedFile(String bedFile) {
        // adding one by one so this functions allows for multiple files to load
        BedFileReader bedFileReader = new BedFileReader(bedFile);
        MultiMap bedData = bedFileReader.getBedMap();
        // Loop trough key and values, and add to chr collection
        for (Object key : bedData.keySet()) {
            for (Object value:(Collection) bedData.get(key)) {
                List<Integer> positions = (List) value;
                this.addChr((String) key, positions.get(0), positions.get(1));
            }   
        }
    }


    /**
     * Load a bed file to exclude
     * @param exludeBedFile
     */
    public void loadExludeBedFile(String exludeBedFile) {
        // adding one by one so this functions allows for multiple files to load
        BedFileReader bedFileReader = new BedFileReader(exludeBedFile);
        MultiMap bedData = bedFileReader.getBedMap();
        // Loop trough key and values, and add to notChr collection
        for (Object key : bedData.keySet()) {
            for (Object value:(Collection) bedData.get(key)) {
                List<Integer> positions = (List) value;
                this.addNotChr((String) key, positions.get(0), positions.get(1));
            }   
        }
    }

    /**
     * Is remove all filtered sites enabled
     * @return setting
     */
    public Boolean isRemoveFilteredAll() {
        return removeFilteredAll;
    }

    /**
     * Set to remove all filtered sites
     * @param removeFilteredAll
     */
    public void setRemoveFilteredAll(Boolean removeFilteredAll) {
        this.removeFilteredAll = removeFilteredAll;
    }

    /**
     * Get list with specific filters to remove sites with
     * @return list with specific filters to remove sites with
     */
    public List<String> getRemoveFiltered() {
        return removeFiltered;
    }

    /**
     * Set list with specific filters to remove sites with
     * @param removeFiltered
     */
    public void setRemoveFiltered(List<String> removeFiltered) {
        this.removeFiltered = removeFiltered;
    }

    /**
     * Add list with specific filters to remove sites with
     * @param removeFiltered
     */
    public void addRemoveFiltered(List<String> removeFiltered) {
        this.removeFiltered.addAll(removeFiltered);
    }

    /**
     * Add specific filter to remove sites with
     * @param removeFiltered
     */
    public void addRemoveFiltered(String removeFiltered) {
        this.removeFiltered.add(removeFiltered);
    }

    /**
     * Get list with specific filters to keep sites with
     * @return list with specific filters to keep sites with
     */
    public List<String> getKeepFiltered() {
        return keepFiltered;
    }

    /**
     * Set list with specific filters to keep sites with
     * @param keepFiltered
     */
    public void setKeepFiltered(List<String> keepFiltered) {
        this.keepFiltered = keepFiltered;
    }

    /**
     * Add list with specific filters to keep sites with
     * @param keepFiltered
     */
    public void addKeepFiltered(List<String> keepFiltered) {
        this.keepFiltered.addAll(keepFiltered);
    }

    /**
     * Add specific filter to keep sites with
     * @param keepFiltered
     */
    public void addKeepFiltered(String keepFiltered) {
        this.keepFiltered.add(keepFiltered);
    }

    /**
     * Get list with specific info to remove sites with
     * @return list with specific info to remove sites with
     */
    public List<String> getRemoveInfo() {
        return removeInfo;
    }

    /**
     * Set list with specific info to remove sites with
     * @param removeInfo
     */
    public void setRemoveInfo(List<String> removeInfo) {
        this.removeInfo = removeInfo;
    }

    /**
     * Add list with specific info to remove sites with
     * @param removeInfo
     */
    public void addRemoveInfo(List<String> removeInfo) {
        this.removeInfo.addAll(removeInfo);
    }

    /**
     * Add specific info to remove sites with
     * @param removeInfo
     */
    public void addRemoveInfo(String removeInfo) {
        this.removeInfo.add(removeInfo);
    }

    /**
     * Get list with specific info to keep sites with
     * @return list with specific info to keep sites with
     */
    public List<String> getKeepInfo() {
        return keepInfo;
    }

    /**
     * Set list with specific info to keep sites with
     * @param keepInfo
     */
    public void setKeepInfo(List<String> keepInfo) {
        this.keepInfo = keepInfo;
    }

    /**
     * Add list with specific info to keep sites with
     * @param keepInfo
     */
    public void addKeepInfo(List<String> keepInfo) {
        this.keepInfo.addAll(keepInfo);
    }
    
    /**
     * Add specific info to keep sites with
     * @param keepInfo
     */
    public void addKeepInfo(String keepInfo) {
        this.keepInfo.add(keepInfo);
    }

    /**
     * Get minimum quality
     * @return setting
     */
    public Double getMinQ() {
        return minQ;
    }

    /**
     * Set minimum quality
     * @param minQ
     */
    public void setMinQ(Double minQ) {
        this.minQ = minQ;
    }

    /**
     * Get minimum mean depth
     * @return setting
     */
    public Double getMinMeanDp() {
        return minMeanDp;
    }

    /**
     * Set minimum mean depth
     * @param minMeanDp
     */
    public void setMinMeanDp(Double minMeanDp) {
        this.minMeanDp = minMeanDp;
    }

    /**
     * Get maximum mean depth
     * @return setting
     */
    public Double getMaxMeanDp() {
        return maxMeanDp;
    }

    /**
     * Set maximum mean depth
     * @param maxMeanDp
     */
    public void setMaxMeanDp(Double maxMeanDp) {
        this.maxMeanDp = maxMeanDp;
    }

    /**
     * Get minimum Minor Allele Frequency
     * @return setting
     */
    public Double getMaf() {
        return maf;
    }

    /**
     * Get minimum Minor Allele Frequency
     * @param maf
     */
    public void setMaf(Double maf) {
        this.maf = maf;
    }

    /**
     * Get maximum Minor Allele Frequency
     * @return setting
     */
    public Double getMaxMaf() {
        return maxMaf;
    }

    /**
     * Set maximum Minor Allele Frequency
     * @param maxMaf
     */
    public void setMaxMaf(Double maxMaf) {
        this.maxMaf = maxMaf;
    }

    /**
     * Get minimum Non-Reference Allele Frequencies
     * @return setting
     */
    public Double getNonRefAf() {
        return nonRefAf;
    }

    /**
     * Set minimum Non-Reference Allele Frequencies
     * @param nonRefAf
     */
    public void setNonRefAf(Double nonRefAf) {
        this.nonRefAf = nonRefAf;
    }

    /**
     * Get maximum Non-Reference Allele Frequencies
     * @return setting
     */
    public Double getMaxNonRefAf() {
        return maxNonRefAf;
    }

    /**
     * Set maximum Non-Reference Allele Frequencies
     * @param maxNonRefAf
     */
    public void setMaxNonRefAf(Double maxNonRefAf) {
        this.maxNonRefAf = maxNonRefAf;
    }

    /**
     * Get minimum Minor Allele Count
     * @return setting
     */
    public Integer getMac() {
        return mac;
    }

    /**
     * Set minimum Minor Allele Count
     * @param mac
     */
    public void setMac(Integer mac) {
        this.mac = mac;
    }

    /**
     * Get maximum Minor Allele Count
     * @return setting
     */
    public Integer getMaxMac() {
        return maxMac;
    }

    /**
     * Set maximum Minor Allele Count
     * @param maxMac
     */
    public void setMaxMac(Integer maxMac) {
        this.maxMac = maxMac;
    }

    /**
     * Get minimum Non-Reference Allele Counts
     * @return setting
     */
    public Double getNonRefAc() {
        return nonRefAc;
    }

    /**
     * Set minimum Non-Reference Allele Counts
     * @param nonRefAc
     */
    public void setNonRefAc(Double nonRefAc) {
        this.nonRefAc = nonRefAc;
    }

    /**
     * Get maximum Non-Reference Allele Counts
     * @return setting
     */
    public Double getMaxNonRefAc() {
        return maxNonRefAc;
    }

    /**
     * Set maximum Non-Reference Allele Counts
     * @param maxNonRefAc
     */
    public void setMaxNonRefAc(Double maxNonRefAc) {
        this.maxNonRefAc = maxNonRefAc;
    }

    /**
     * Set Hardy-Weinberg Equilibrium
     * @return setting
     */
    public Double getHwe() {
        return hwe;
    }

    /**
     * Get Hardy-Weinberg Equilibrium
     * @param hwe
     */
    public void setHwe(Double hwe) {
        this.hwe = hwe;
    }

    /**
     * Set proportion of missing data
     * @return setting
     */
    public Double getGeno() {
        return geno;
    }

    /**
     * Get proportion of missing data
     * @param geno
     */
    public void setGeno(Double geno) {
        this.geno = geno;
    }

    /**
     * Set maximum missing chromosomes 
     * @return setting
     */
    public Integer getMaxMissingCount() {
        return maxMissingCount;
    }

    /**
     * Get maximum missing chromosomes
     * @param maxMissingCount
     */
    public void setMaxMissingCount(Integer maxMissingCount) {
        this.maxMissingCount = maxMissingCount;
    }

    /**
     * Get minimum number of alleles
     * @return setting
     */
    public Integer getMinAlleles() {
        return minAlleles;
    }

    /**
     * Set minimum number of alleles
     * @param minAlleles
     */
    public void setMinAlleles(Integer minAlleles) {
        this.minAlleles = minAlleles;
    }

    /**
     * Get maximum number of alleles
     * @return setting
     */
    public Integer getMaxAlleles() {
        return maxAlleles;
    }

    /**
     * Set maximum number of alleles
     * @param maxAlleles
     */
    public void setMaxAlleles(Integer maxAlleles) {
        this.maxAlleles = maxAlleles;
    }

    /**
     * Get thinning number
     * @return setting
     */
    public Integer getThin() {
        return thin;
    }

    /**
     * Set thinning number
     * @param thin
     */
    public void setThin(Integer thin) {
        this.thin = thin;
    }

    /**
     * Load mask file
     * @param maskFile
     */
    public void loadMaskFile(String maskFile) {
        // adding one by one so this functions allows for multiple files to load
        MaskFileReader maskFileReader = new MaskFileReader(maskFile, false);
        MultiMap mask = maskFileReader.getMaskMap();
        // Loop trough key and values, and add to chr collection
        for (Object key : mask.keySet()) {
            for (Object value:(Collection) mask.get(key)) {
                this.addMask((String) key, (Integer) value);
            }   
        }
    }

    /**
     * Get mask
     * @return setting
     */
    public MultiMap getMask() {
        return mask;
    }

    /**
     * Set mask
     * @param mask
     */
    public void setMask(MultiMap mask) {
        this.mask = mask;
    }

    /**
     * Add mask item
     * @param key
     * @param value
     */
    public void addMask(String key, int value) {
        this.mask.put(key, value);
    }

    /**
     * Load inverted mask file
     * @param invertMaskFile
     */
    public void loadInvertMaskFile(String invertMaskFile) {
        // adding one by one so this functions allows for multiple files to load
        MaskFileReader maskFileReader = new MaskFileReader(invertMaskFile, true);
        MultiMap mask = maskFileReader.getMaskMap();
        // Loop trough key and values, and add to chr collection
        for (Object key : mask.keySet()) {
            for (Object value:(Collection) mask.get(key)) {
                this.addInvertMask((String) key, (Integer) value);
            }   
        }
    }

    /**
     * Get inverted mask
     * @return inverted mask
     */
    public MultiMap getInvertMask() {
        return invertMask;
    }

    /**
     * Set inverted mask
     * @param invertMask
     */
    public void setInvertMask(MultiMap invertMask) {
        this.invertMask = invertMask;
    }

    /**
     * Add inverted mask item
     * @param key
     * @param value
     */
    public void addInvertMask(String key, int value) {
        this.invertMask.put(key, value);
    }    
    
    /**
     * Get minimum mask number
     * @return setting
     */
    public Integer getMaskMin() {
        return maskMin;
    }

    /**
     * Set minimum mask number
     * @param maskMin
     */
    public void setMaskMin(Integer maskMin) {
        this.maskMin = maskMin;
    }

    /**
     * Get list of individuals to keep
     * @return list of individuals to keep
     */
    public List<String> getKeepIndv() {
        return keepIndv;
    }

    /**
     * Set list of individuals to keep
     * @param keepIndv
     */
    public void setKeepIndv(List<String> keepIndv) {
        this.keepIndv = keepIndv;
    }

    /**
     * Add list of individuals to keep
     * @param keepIndv
     */
    public void addKeepIndv(List<String> keepIndv) {
        this.keepIndv.addAll(keepIndv);
    }
    
    /**
     * Add individual to keep
     * @param keepIndv
     */
    public void addKeepIndv(String keepIndv) {
        this.keepIndv.add(keepIndv);
    }

    /**
     * Load file with individuals to keep
     * @param keepIndvFile
     */
    public void loadKeepIndvFile(String keepIndvFile) {
        // adding one by one so this functions allows for multiple files to load
        SeparatedValueReader reader = new SeparatedValueReader(keepIndvFile,System.lineSeparator());
        List indvs = reader.getList();
        // loop trough items and add to keepIndv list
        for (Object indv : indvs) {
            this.addKeepIndv((String) indv);
        }
    }

    /**
     * Get list of individuals to remove
     * @return list of individuals to remove
     */
    public List<String> getRemoveIndv() {
        return removeIndv;
    }

    /**
     * Set list of individuals to remove
     * @param removeIndv
     */
    public void setRemoveIndv(List<String> removeIndv) {
        this.removeIndv = removeIndv;
    }

    /**
     * Add list of individuals to remove
     * @param removeIndv
     */
    public void addRemoveIndv(List<String> removeIndv) {
        this.removeIndv.addAll(removeIndv);
    }
    
    /**
     * Add individual to remove
     * @param removeIndv
     */
    public void addRemoveIndv(String removeIndv) {
        this.removeIndv.add(removeIndv);
    }    

    /**
     * Load file with individuals to remove
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
     * Get minimum individual mean depth
     * @return setting
     */
    public Double getMinIndvMeanDp() {
        return minIndvMeanDp;
    }

    /** 
     * Set minimum individual mean depth
     * @param minIndvMeanDp
     */
    public void setMinIndvMeanDp(Double minIndvMeanDp) {
        this.minIndvMeanDp = minIndvMeanDp;
    }

    /**
     * Get maximum individual mean depth
     * @return setting
     */
    public Double getMaxIndvMeanDp() {
        return maxIndvMeanDp;
    }

    /**
     * Set maximum individual mean depth
     * @param maxIndvMeanDp
     */
    public void setMaxIndvMeanDp(Double maxIndvMeanDp) {
        this.maxIndvMeanDp = maxIndvMeanDp;
    }

    /**
     * Get minimum call rate threshold
     * @return setting
     */
    public Double getMind() {
        return mind;
    }

    /**
     * Set minimum call rate threshold
     * @param mind
     */
    public void setMind(Double mind) {
        this.mind = mind;
    }

    /**
     * Get this filter: First excludes all individuals having all genotypes unphased, and subsequently excludes all sites with unphased genotypes. The remaining data therefore consists of phased data only.
     * @return setting
     */
    public Boolean isPhased() {
        return phased;
    }

    /**
     * Set this filter: First excludes all individuals having all genotypes unphased, and subsequently excludes all sites with unphased genotypes. The remaining data therefore consists of phased data only.
     * @param phased
     */
    public void setPhased(Boolean phased) {
        this.phased = phased;
    }

    /**
     * Get this filter: Randomly thins individuals so that only the specified number are retained.
     * @return setting
     */
    public Integer getMaxIndv() {
        return maxIndv;
    }

    /**
     * Set this filter: Set this filter: Randomly thins individuals so that only the specified number are retained.
     * @param maxIndv
     */
    public void setMaxIndv(Integer maxIndv) {
        this.maxIndv = maxIndv;
    }

    /**
     * Get statistics count
     * @return setting
     */
    public Boolean isCount() {
        return count;
    }

    /**
     * set statistics count
     * @param count
     */
    public void setCount(Boolean count) {
        this.count = count;
    }

    /**
     * Get statistics freq
     * @return setting
     */
    public Boolean isFreq() {
        return freq;
    }

    /**
     * Set statistics freq
     * @param freq
     */
    public void setFreq(Boolean freq) {
        this.freq = freq;
    }

    /**
     * Get statistics depth
     * @return setting
     */
    public Boolean isDepth() {
        return depth;
    }

    /**
     * Set statistics depth
     * @param depth
     */
    public void setDepth(Boolean depth) {
        this.depth = depth;
    }

    /**
     * Get to remove filtered genotypes
     * @return setting
     */
    public Boolean isRemoveFilteredGenoAll() {
        return removeFilteredGenoAll;
    }

    /**
     * Set to remove filtered genotypes
     * @param removeFilteredGenoAll
     */
    public void setRemoveFilteredGenoAll(Boolean removeFilteredGenoAll) {
        this.removeFilteredGenoAll = removeFilteredGenoAll;
    }

    /**
     * Get list of filters to remove genotypes with
     * @return list of filters to remove genotypes with
     */
    public List<String> getRemoveFilteredGeno() {
        return removeFilteredGeno;
    }

    /** 
     * Set list of filters to remove genotypes with
     * @param removeGenoFiltered
     */
    public void setRemoveFilteredGeno(List<String> removeGenoFiltered) {
        this.removeFilteredGeno = removeGenoFiltered;
    }
    
    /**
     * Add filter to remove genotypes with
     * @param removeGenoFiltered
     */
    public void addRemoveFilteredGeno(String removeGenoFiltered) {
        this.removeFilteredGeno.add(removeGenoFiltered);
    }

    /**
     * Get minimum genotype quality
     * @return setting
     */
    public Double getMinGq() {
        return minGq;
    }

    /**
     * Set minimum genotype quality
     * @param minGq
     */
    public void setMinGq(Double minGq) {
        this.minGq = minGq;
    }

    /**
     * Get minimum genotype depth
     * @return setting
     */
    public Double getMinDp() {
        return minDp;
    }

    /**
     * Set minimum genotype depth
     * @param minDp
     */
    public void setMinDp(Double minDp) {
        this.minDp = minDp;
    }

    /**
     * Get maximum genotype depth
     * @return setting
     */
    public Double getMaxDp() {
        return maxDp;
    }

    /**
     * Set maximum genotype depth
     * @param maxDp
     */
    public void setMaxDp(Double maxDp) {
        this.maxDp = maxDp;
    }
    

}
