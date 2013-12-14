/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;
import nl.bioinf.vcftools.Settings;
import org.apache.commons.cli.HelpFormatter;

/**
 *
 * @author aponnudurai
 */
public class Cli {

    private Settings settings;
    private HelpFormatter helpFormatter;
    private Options option;
    private CommandLine cmd;
    private String[] args;

    Cli(String[] args) throws ParseException {

        this.args = args;
        this.option = defineOptions();
        this.helpFormatter = new HelpFormatter();
        this.settings = new Settings();

        System.out.println("hoi");
        CommandLineParser parser = new BasicParser();
        this.cmd = parser.parse(this.option, args);
        System.out.println("boob");

        checkOptions();

        procesOptions();

    }

    public Options defineOptions() {
        Options opt = new Options();
        opt.addOption("h", "help", false, "Help function");

        /* Basic Settings */
        opt.addOption("vcf", true, "this option defines the VCF file to be processed");
        opt.addOption("gvcf", true, "this option defines the compressed VCF file to be processed");
        opt.addOption("bcf", true, "this option defines the BCF file to be processed");
        opt.addOption("out", true, "this option defines the output filename prefix for all files generated by vcftools");

        /* Site Filters */
        opt.addOption("chr", true, "chromosome identifiers can be used more than once to include or exclude multiple chromosomes. Seperate the identifiers with ',' if mulitiple identifiers are given");
        opt.addOption("fromBp", true, "this option defines the physical start position of site will be processed. A integer is expected");
        opt.addOption("toBp", true, "this option defines the physical stop position of site will be processed. A integer is expected");
        opt.addOption("snp", true, "this option defines a snp which will be processed. A string is excepted");
        opt.addOption("snpFile", true, "include a list of SNPs given in a file, with one ID per line");
        opt.addOption("excludeSnp", true, "exclude SNPs which are given by the user. Seperate the snps with a ',' if mulitple snp are given");
        opt.addOption("excludeSnpFile", true, "exclude a list of SNPs given in a file. The file should contain a list of SNP IDs, with one ID per line");
        opt.addOption("positions", true, "include a set of sites. Seperate with ',' if multiple sites are wanted to be given");
        opt.addOption("positionsFile", true, "include a set of sites on the basis of a list of positions in a file");
        opt.addOption("excludePositions", true, "exclude a set of sites on the basis of a list of positions in a file");
        opt.addOption("excludePositionsFile", true, "exclude a set of sites on the basis of a list of positions in a file");
        opt.addOption("keepOnlyIndels", true, "include or exclude sites that contain an indel");
        opt.addOption("removeIndels", true, "exclude or exclude sites that contain an indel");
        opt.addOption("bed", true, "include a set of sites on the basis of a BED files");
        opt.addOption("exludeBed", true, "exclude a set of sites on the basis of a BED files");
        opt.addOption("removeFilteredAll", false, "this option removes all sites with a FILTER flag");
        opt.addOption("removeFiltered", true, "exclude sites with a specific filter flag");
        opt.addOption("removeFilteredFile", true, "exclude sites with a specific filter flag which is given in a file");
        opt.addOption("keepFiltered", true, "this option can be used to select sites on the basis of specific filter flags"); //check required if arg = FLAG and no INFO field value
        opt.addOption("keepFilteredFile", true, "this option can be used to select sites on the basis of specific filter flags which are in a file");
        opt.addOption("removeInfo", true, "this option can be used to exclude sites with a specific INFO flag");
        opt.addOption("keepInfo", true, "this option can be used to select sites on the basis of specific INFO flags, keepInfo is applied befor removeInfo if both are given");
        opt.addOption("minQ", true, "include only sites with Quality above this threshold. A float is expected");
        opt.addOption("minMeanDP", true, "include sites with mean Depth within the thresholds defined by the user. A float is expected");
        opt.addOption("maxMeanDP", true, "include sites with mean Depth within the thresholds defined by the user. A float is expected");
        opt.addOption("maf", true, "include only sites with Minor Allele Frequency within the specified range. A float is expected");
        opt.addOption("maxMaf", true, "include only sites with Minor Allele Frequency within a range which is specified by the user. A float is expected");
        opt.addOption("nonRefAf", true, "include only sites with all Non-Reference Allele Frequencies within the specified range. A float is expected");
        opt.addOption("maxNonRefAf", true, "include only sites with all Non-Reference Allele Frequencies within the specified range with a maximum specified by the user as a float");
        opt.addOption("mac", true, "include only sites with Minor Allele Count within the specified range. A float is expected");
        opt.addOption("maxMac", true, "include only sites with Minor Allele Count within the specified range with a maximum which is given by the user as a float");
        opt.addOption("nonRefAc", true, "include only sites with all Non-Reference Allele Counts within the specified range. A float is expected");
        opt.addOption("maxNonRefAc", true, "include only sites with all Non-Reference Allele Counts within the specified range with a maximum which is given by the user as a float");
        opt.addOption("hwe", true, "assesses sites for Hardy-Weinberg Equilibrium using an exact test sites with a p-value below the threshold defined by this option are taken to be out of HWE, and therefore excluded. A float is expected");
        opt.addOption("geno", true, "exclude sites on the basis of the proportion of missing data (defined to be between 0 and 1, where 1 indicates no missing data allowed). A float is expected");
        opt.addOption("maxMissingCount", true, "exclude sites which has more than this number of missing chromosomes. An integer is expected");
        opt.addOption("minAlleles", true, "include only sites with a number of alleles within the specified range. For example, to include only bi-allelic sites, one could use --minAlleles 2. A interger is expected");
        opt.addOption("maxAlleles", true, "include only sites with a number of alleles within the specified range. For example, to include only bi-allelic sites, one could use --maxAlleles 2. A interger is expected");
        opt.addOption("thin", true, "thin sites so that no two sites are within the specified distance. An integer is expected");
        opt.addOption("mask", true, "include sites on the basis of a FASTA-like file");
        opt.addOption("invertMask", true, "this option can be used to specify a mask file that will be inverted before being applied");
        opt.addOption("maskMin", true, "set the threshold value which determines if sites are filtered or not. An integer is expected");

        /* Individual filters */
        opt.addOption("keepIndv", true, "specify an individual to be kept in the analysis. This option can accept multiple arguments to specify multiple individuals. Each individual should be seperated with a ','. A string is expected");
        opt.addOption("keepIndvFile", true, "provide a file containing a list of individuals to include in subsequent analysis. Each individual ID (as defined in the VCF headerline) should be included on a separate line");
        opt.addOption("removeIndv", true, "specify an individual to be removed from the analysis. A string is expected. If --indv also used, --indv will be applied first.");
        opt.addOption("removeIndvFile", true, "provide a file containing a list of individuals to exclude in subsequent analysis. Each individual ID (as defined in the VCF headerline) should be included on a separate line If --keep also used, --keep will be applied first");
//        opt.addOption(" minIndvMeanDp", true, "calculate the mean coverage on a per-individual basis. Only individuals with coverage of a minimal thresshold specified by these options are included in subsequent analyses. A float is expected");
        opt.addOption("maxIndvMeanDP", true, "calculate the mean coverage on a per-individual basis. Only individuals with coverage of a maximal thresshold specified by these options are included in subsequent analyses. A float is expected");
        opt.addOption("mind", true, "specify the minimum call rate threshold for each individual. A float is expected");
        opt.addOption("phased", true, "first excludes all individuals having all genotypes unphased, and subsequently excludes all sites with unphased genotypes. The remaining data therefore consists of phased data only");
        opt.addOption("maxIndv", true, "randomly thins individuals so that only the specified number are retained. An integer is expected");

        /* Statistics */
        opt.addOption("count", false, "this option results a file with a raw count of allele per site of a given VCF file with the suffix .frq.count");
        opt.addOption("freq", false, "outputs the allele frequency in a file with the suffix .frq");
        opt.addOption("depth", false, "generates a file containing the mean depth per individual. This file has the suffix .idepth");

        return opt;
    }

    public void checkOptions() {
        System.out.println("checking options....");

        if (this.args.length < 0) {
            usage();
        } else if (this.cmd.hasOption("fromBp") && !(this.cmd.hasOption("chr"))) {
            System.err.println("The options -fromBp and -toBp can only be used in conjunction with -chr");
            System.exit(0);

        } else if (this.cmd.hasOption("fromBp") && !(this.cmd.hasOption("toBp"))) {
            System.err.println("When the option -fromBp is given the -toBp is also required");
            usage();
        } else if (this.cmd.hasOption("removeFilteredAll") && (this.cmd.hasOption("removeFiltered") || this.cmd.hasOption("keepFiltered"))) {
            System.err.println("When option -removeFilteredAll is given the options -removeFilterd or -keepFilterd are not allowed");
            System.exit(0);
        } else if (cmd.hasOption("keepInfo") && cmd.hasOption("removeInfo")) {
            System.out.println("For the options: -keepInfo and -removeInfo"
                    + " the -keepInfo option will be executed first.");
        } else if (cmd.hasOption("keepFilteredFile") && cmd.hasOption("removeFilteredFile")) {
            System.out.println("For the options: keepFilteredFile and removeFilteredFile"
                    + " the -keepFilteredFile option will be executed first.");
        } else if (cmd.hasOption("keepFiltered") && cmd.hasOption("removeFiltered")) {
            System.out.println("For the options: -keepFiltered and -removeFiltered"
                    + " the -keepFiltered option will be executed first.");
        } else if (cmd.hasOption("chr")) {
            String pattern = "\\w";
            Pattern patternCompiled = Pattern.compile(pattern);
            Matcher m = patternCompiled.matcher(cmd.getOptionValue("chr"));
            if (m.find()) {
                System.err.println("To identify chromosome, only the identifying number is required." + "\nGiven is = " + cmd.getOptionValue("chr"));
                System.exit(0);
            }
        } else if (cmd.hasOption("minMeanDP") && !(cmd.hasOption("maxMeanDP")) || cmd.hasOption("maxMeanDP") && !(cmd.hasOption("minMeanDP"))) {
            System.err.println("It is obliged to use the options -minMeanDP and -maxMeanDP together");
            System.exit(0);
        } else if (cmd.hasOption("maf") && !(cmd.hasOption("maxMaf")) || cmd.hasOption("maxMaf") && !(cmd.hasOption("maf"))) {
            System.err.println("It is obliged to use the options -maf and -maxMaf together");
        } else if (cmd.hasOption("nonRefAf") && !(cmd.hasOption("maxNonRefAf")) || cmd.hasOption("maxNonRefAf") && !(cmd.hasOption("nonRefAf"))) {
            System.err.println("It is obliged to use the options -nonRefAf and -maxNonRefAf together");
            System.exit(0);
        } else if (cmd.hasOption("mac") && !(cmd.hasOption("maxMac")) || cmd.hasOption("maxMac") && !(cmd.hasOption("mac"))) {
            System.err.println("It is obliged to use the options -mac and -maxMac together");
            System.exit(0);
        } else if (cmd.hasOption("nonRefAc") && !(cmd.hasOption("maxNonRefAc")) || cmd.hasOption("maxNonRefAc") && !(cmd.hasOption("nonRefAc"))) {
            System.err.println("It is obliged to use the options -nonRefAc and -maxNonRefAc together");
            System.exit(0);
        } else if (cmd.hasOption("geno")) {
            String geno = cmd.getOptionValue("geno");
            if (!(geno.equals("1")) || !(geno.equals("0"))) {
                System.err.println("The option -geno only allows 1 or 0. Where 1 indicates no missing data allowed");
                System.exit(0);
            }

        } else if (cmd.hasOption("minAlleles") && !(cmd.hasOption("maxAlleles")) || cmd.hasOption("maxAlleles") && !(cmd.hasOption("minAlleles"))) {
            System.err.println("It is obliged to use the options -minAlleles and -maxAlleles together");
            System.exit(0);
        } //        
        //        
        //        
        //        else if(cmd.hasOption("keepInfo")){
        //        
        //        }
        else if (args.length < 1) {
//                usages(opt);
        }

    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void usage() {
        helpFormatter.printHelp("For analysing the VCF file several options can be used."
                + "This tool supports version higher than 4.0 . This tool is also"
                + "compatible for snp analysis in poliploid cells", this.option);
        System.exit(0);
    }

    public void procesOptions() {

//         Settings settings = new Settings();
        if (this.cmd.hasOption("h")) {
//        this.usages();
        }
        if (this.cmd.hasOption("vcf")) {

            settings.setInputFile(this.cmd.getOptionValue("vcf"));
        }

        settings.setInputFile(this.cmd.getOptionValue("vcf"));

        if (this.cmd.hasOption("gvcf")) {
            settings.setGzipped(true);
        } else if (this.cmd.hasOption("out")) {
            settings.setOutputFile(true);
        } else if (this.cmd.hasOption("chr")) {
            String chrIdentifiers = this.cmd.getOptionValue("chr");
            ArrayList<String> arrayListChr = new ArrayList<String>();

            String[] splitedchrIdentifiers = chrIdentifiers.split(",");
            for (String identifier : splitedchrIdentifiers) {
                arrayListChr.add(identifier);
            }

            settings.setChr(arrayListChr);
        } else if (this.cmd.hasOption("notChr")) {
            String chrIdentifiers = this.cmd.getOptionValue("notChr");
            ArrayList<String> arrayListNotChr = new ArrayList<String>();

            String[] splitedchrIdentifiers = chrIdentifiers.split(",");
            for (String identifier : splitedchrIdentifiers) {
                arrayListNotChr.add(identifier);
                settings.setNotChr(arrayListNotChr);
            }
        } else if (this.cmd.hasOption("fromBp")) {
            int fromBp = Integer.parseInt(this.cmd.getOptionValue("fromBp"));
            settings.setFromBp(fromBp);
        } else if (this.cmd.hasOption("toBp")) {
            int toBp = Integer.parseInt(this.cmd.getOptionValue("toBp"));
            settings.setToBp(toBp);
        } else if (this.cmd.hasOption("snp")) {
            String snpIdentifiers = this.cmd.getOptionValue("snp");
            ArrayList<String> arrayListSnp = new ArrayList<String>();

            String[] splitedSnpIdentifiers = snpIdentifiers.split(",");
            for (String identifier : splitedSnpIdentifiers) {
                arrayListSnp.add(identifier);

            }
            settings.setSnp(arrayListSnp);
        } else if (this.cmd.hasOption("snpFile")) {
            settings.setSnpFile(this.cmd.getOptionValue("snpFile"));
        } else if (this.cmd.hasOption("excludeSnp")) {
            String excludeSnpIdentifiers = this.cmd.getOptionValue("excludeSnp");
            ArrayList<String> arrayListExcludeSnp = new ArrayList<String>();

            String[] splitedExcludedIdentifiers = excludeSnpIdentifiers.split(",");
            for (String identifiers : splitedExcludedIdentifiers) {
                arrayListExcludeSnp.add(identifiers);
            }
            settings.setExcludeSnp(arrayListExcludeSnp);
        } else if (this.cmd.hasOption("excludeSnpFile")) {
            settings.setExcludeSnpFile(this.cmd.getOptionValue("excludeSnpFile"));
        } else if (this.cmd.hasOption("positions")) {
            String positions = this.cmd.getOptionValue("positions");
            String[] splitedPositions = positions.split(",");
            ArrayList<String> arrayListPosition = new ArrayList<String>();

            for (String pos : splitedPositions) {
                arrayListPosition.add(pos);
            }
            settings.setPositions(arrayListPosition);
        } else if (this.cmd.hasOption("positionsFile")) {
            settings.setPositionsFile(this.cmd.getOptionValue("positionsFile"));
        } else if (this.cmd.hasOption("excludePositions")) {
            String excludePositions = this.cmd.getOptionValue("excludePositions");
            String[] splitedExcludePositions = excludePositions.split(",");

            ArrayList<Integer> arrayListExcludePositions = new ArrayList<Integer>();

            for (String exPos : splitedExcludePositions) {
                int excludePosition = Integer.parseInt(exPos);
                arrayListExcludePositions.add(excludePosition);
            }
            settings.setExcludePositions(arrayListExcludePositions);
        } else if (this.cmd.hasOption("excludePositionsFile")) {
            settings.setExcludePositionsFile(this.cmd.getOptionValue("excludePositionsFile"));
        } else if (this.cmd.hasOption("keepOnlyIndels")) {
            settings.setKeepOnlyIndels(true);
        } else if (this.cmd.hasOption("removeIndels")) {
            settings.setRemoveIndels(true);
        } else if (this.cmd.hasOption("bed")) {
//      Bed bed = new Bed();
//      settings.setBed(this.cmd.getOptionValue("bed"));
        } else if (this.cmd.hasOption("exludeBed")) {
//      Bed bed = new Bed()
//        settings.setExludeBed(this.cmd.getOptionValue("exludeBed"));
        } else if (this.cmd.hasOption("removeFilteredAll")) {
            settings.setRemoveFilteredAll(true);
        } else if (this.cmd.hasOption("removeFiltered")) {
            String removedFiltered = this.cmd.getOptionValue("removeFiltered");
            String[] splitedRemovedFiltered = removedFiltered.split(",");
            ArrayList<String> arrayListRemovedFiltered = new ArrayList<String>();

            for (String removedItem : splitedRemovedFiltered) {
                arrayListRemovedFiltered.add(removedItem);
            }
            settings.setRemoveFiltered(arrayListRemovedFiltered);
        } else if (this.cmd.hasOption("removeFilteredFile")) {
            settings.setRemoveFilteredFile(null);
        } else if (this.cmd.hasOption("keepFiltered")) {
            String keepFiltered = this.cmd.getOptionValue("keepFiltered");
            String[] splitedKeepFiltered = keepFiltered.split(",");
            ArrayList<String> arrayListKeepFiltered = new ArrayList<String>();

            for (String keepFilteredItem : splitedKeepFiltered) {
                arrayListKeepFiltered.add(keepFiltered);
            }
            settings.setKeepFiltered(arrayListKeepFiltered);
        } else if (this.cmd.hasOption("keepFilteredFile")) {
            settings.setKeepFilteredFile(this.cmd.getOptionValue("keepFilteredFile"));
        } else if (this.cmd.hasOption("removeInfo")) {
            String removeInfo = this.cmd.getOptionValue("removeInfo");
            String[] splitedRemovedInfo = removeInfo.split(",");
            ArrayList<String> arrayListRemovedInfo = new ArrayList<String>();

            for (String removedInfoItem : splitedRemovedInfo) {
                arrayListRemovedInfo.add(removedInfoItem);
            }
            settings.setRemoveInfo(arrayListRemovedInfo);
        } else if (this.cmd.hasOption("keepInfo")) {
            String keepInfo = this.cmd.getOptionValue("keepInfo");
            String[] splitedKeptInfo = keepInfo.split(",");
            ArrayList<String> arrayListKeptInfo = new ArrayList<String>();

            for (String keptInfoItem : splitedKeptInfo) {
                arrayListKeptInfo.add(keptInfoItem);
            }
            settings.setKeepInfo(arrayListKeptInfo);
        } else if (this.cmd.hasOption("minQ")) {
            Float minQValue = Float.parseFloat(this.cmd.getOptionValue("minQ"));
            settings.setMinQ(minQValue);
        } else if (this.cmd.hasOption("minMeanDP")) {
            Float minMeanDP = Float.parseFloat(this.cmd.getOptionValue("minMeanDP"));
            settings.setMinMeanDP(minMeanDP);
        } else if (this.cmd.hasOption("minMeanDP")) {
            Float minMeanDP = Float.parseFloat(this.cmd.getOptionValue("minMeanDP"));
            settings.setMinMeanDP(minMeanDP);
        } else if (this.cmd.hasOption("maxMeanDP")) {
            Float maxMeanDP = Float.parseFloat(this.cmd.getOptionValue("maxMeanDP"));
            settings.setMaxMeanDP(maxMeanDP);
        } else if (this.cmd.hasOption("maf")) {
            Float maf = Float.parseFloat(this.cmd.getOptionValue("maf"));
            settings.setMaf(maf);
        } else if (this.cmd.hasOption("maxMaf")) {
            Float maxMaf = Float.parseFloat(this.cmd.getOptionValue("maxMaf"));
            settings.setMaxMaf(maxMaf);
        } else if (this.cmd.hasOption("nonRefAf")) {
            Float nonRefAf = Float.parseFloat(this.cmd.getOptionValue("nonRefAf"));
            settings.setNonRefAf(nonRefAf);
        } else if (this.cmd.hasOption("maxNonRefAf")) {
            Float maxNonRefAf = Float.parseFloat(this.cmd.getOptionValue("maxNonRefAf"));
            settings.setMaxNonRefAf(maxNonRefAf);
        } else if (this.cmd.hasOption("mac")) {
            int mac = Integer.parseInt(this.cmd.getOptionValue("mac"));
            settings.setMac(mac);
        } else if (this.cmd.hasOption("maxMac")) {
            int maxMac = Integer.parseInt(this.cmd.getOptionValue("maxMac"));
            settings.setMaxMac(maxMac);
        } else if (this.cmd.hasOption("nonRefAc")) {
            float nonRefAc = Float.parseFloat(this.cmd.getOptionValue("nonRefAc"));
            settings.setNonRefAc(nonRefAc);
        } else if (this.cmd.hasOption("maxNonRefAc")) {
            float maxNonRefAc = Float.parseFloat(this.cmd.getOptionValue("maxNonRefAc"));
            settings.setMaxNonRefAc(maxNonRefAc);
        } else if (this.cmd.hasOption("hwe")) {
            float hwe = Float.parseFloat(this.cmd.getOptionValue("hwe"));
            settings.setHwe(hwe);
        } else if (this.cmd.hasOption("geno")) {
            float geno = Float.parseFloat(this.cmd.getOptionValue("geno"));
            settings.setGeno(geno);
        } else if (this.cmd.hasOption("maxMissingCount")) {
            int maxMissingCount = Integer.parseInt(this.cmd.getOptionValue("maxMissingCount"));
//      settings.setMaxMissingCount(maxMissingCount);
        } else if (this.cmd.hasOption("minAlleles")) {
            int minAlleles = Integer.parseInt(this.cmd.getOptionValue("minAlleles"));
            settings.setMinAlleles(minAlleles);
        } else if (this.cmd.hasOption("maxAlleles")) {
            int maxAlleles = Integer.parseInt(this.cmd.getOptionValue("maxAlleles"));
            settings.setMaxAlleles(maxAlleles);
        } else if (this.cmd.hasOption("thin")) {
            int thin = Integer.parseInt(this.cmd.getOptionValue("thin"));
            settings.setThin(thin);
        } else if (this.cmd.hasOption("mask")) {
            settings.setMask(this.cmd.getOptionValue("mask"));
        } else if (this.cmd.hasOption("invertMask")) {
            settings.setInvertMask(this.cmd.getOptionValue("invertMask"));
        } else if (this.cmd.hasOption("maskMin")) {
            int maskMin = Integer.parseInt(this.cmd.getOptionValue("maskMin"));
            settings.setMaskMin(maskMin);
        } else if (this.cmd.hasOption("keepIndv")) {
            String keepIndv = this.cmd.getOptionValue("keepIndv");
            String[] splitedKeptIndv = this.cmd.getOptionValues("keepIndv");

            ArrayList<String> arrayListKeepIndv = new ArrayList<String>();
            for (String keepIndvItem : splitedKeptIndv) {
                arrayListKeepIndv.add(keepIndvItem);
            }
            settings.setKeepIndv(arrayListKeepIndv);
        } else if (this.cmd.hasOption("keepIndvFile")) {
            settings.setKeepIndvFile(this.cmd.getOptionValue("keepIndvFile"));
        } else if (this.cmd.hasOption("removeIndv")) {
            String removeIndv = this.cmd.getOptionValue("removeIndv");
            String[] splitedRemovedIndv = removeIndv.split(",");

            ArrayList<String> arrayListRemovedIndv = new ArrayList<String>();
            for (String removedIndvItem : splitedRemovedIndv) {
                arrayListRemovedIndv.add(removedIndvItem);
            }
            settings.setRemoveIndv(arrayListRemovedIndv);
        } else if (this.cmd.hasOption("removeIndvFile")) {
            settings.setRemoveIndvFile(this.cmd.getOptionValue("removeIndvFile"));
        } else if (this.cmd.hasOption("minIndvMeanDp")) {
            float minIndvMeanDp = Float.parseFloat(this.cmd.getOptionValue("minIndvMeanDp"));
            settings.setMinIndvMeanDp(minIndvMeanDp);
        } else if (this.cmd.hasOption("maxIndvMeanDp")) {
            float maxIndvMeanDp = Float.parseFloat(this.cmd.getOptionValue("maxIndvMeanDp"));
            settings.setMaxIndvMeanDp(maxIndvMeanDp);
        } else if (this.cmd.hasOption("mind")) {
            float mind = Float.parseFloat(this.cmd.getOptionValue("mind"));
            settings.setMind(mind);
        } else if (this.cmd.hasOption("phased")) {
            settings.setPhased(true);
        } else if (this.cmd.hasOption("maxIndv")) {
            int maxIndv = Integer.parseInt(this.cmd.getOptionValue("maxIndv"));
            settings.setMaxIndv(maxIndv);
        } else if (this.cmd.hasOption("count")) {
            settings.setCount(true);
        } else if (this.cmd.hasOption("freq")) {
            settings.setFreq(true);
        } else if (this.cmd.hasOption("depth")) {
            settings.setDepth(true);
        } else {
//            helpFormatter.printHelp("Please use the following options ", opt);
        }

    }

}
