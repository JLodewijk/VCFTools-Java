/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import static java.util.Arrays.asList;
import java.util.List;
import org.broadinstitute.variant.variantcontext.VariantContext;

/**
 *
 * This Java class contains numerous test function for Site filtering. If you
 * want to run this file, than you need to call in a different program
 * (ReadVcf.java) this program. You can do this by: SiteFilters site = new
 * SiteFilters(); and site.*insert function name*( vcf.getNextIter(file),*insert
 * other parameters*);
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 * @url: http://vcftools.sourceforge.net/options.html#site_filter
 */
public class SiteFilters {

    /**
     * Approve or reject chromosomes based on their CHROM, this can also be used
     * for multiple chromosomes.
     *
     * @param line VCF snip line that will be analysed.
     * @param chromosome User defined chromosome name, is capable of supporting
     * multiple chromosomes.
     * @param args Acts as a flag to activate either the IncludeChromosome
     * (true) or the ExcludeChromosome (false).
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void Chromosome(VariantContext line, String chromosome, boolean args) {
	//Multiple chromosomes support. Example: "22,23" becomes [22, 23] as a array list.
	List<String> chromosomes = asList(chromosome.split(","));
	//If option IncludeChromosome is given then args is true.
	if (args == true) {
	    if (chromosomes.contains(line.getChr())) {
		System.out.println("Line is approved based on: " + line.getChr() + " since it's in " + chromosomes);
	    } else {
		System.out.println("Line is rejected based on: " + line.getChr() + " since it's not in " + chromosomes);
	    }
	} //Else if option ExcludeChromosome is given then args is false.
	else {
	    if (chromosomes.contains(line.getChr())) {
		System.out.println("Line is rejected based on: " + line.getChr() + " since it's in " + chromosomes);
	    } else {
		System.out.println("Line is approved based on: " + line.getChr() + " since it's not in " + chromosomes);
	    }
	}
    }

    /**
     * Only sites who have position within that range will be passed. Sites
     * outside of this range will be rejected. Also this can only be used in
     * conjuction with –chr.
     *
     * @param line VCF snip line that will be analysed.
     * @param ToBp User defined base pair number from, is used for the options
     * ToBp.
     * @param FromBp User defined base pair number to, is used for the options
     * FromBp.
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void Bp(VariantContext line, int ToBp, int FromBp) {
	if (ToBp > line.getEnd() | FromBp < line.getStart()) {
	    System.out.println("Line is rejected since: " + line.getEnd() + " falls outside the range of " + ToBp + " and " + FromBp);
	} else {
	    System.out.println("Line is passed since: " + line.getStart() + " inside the range of " + ToBp + " and " + FromBp);
	}
    }

    /**
     * Include only sites with Quality above this threshold.
     *
     * @param line VCF snip line that will be analysed.
     * @param minQ User defined minimal quality number, determines how low the
     * quality score can be before it is being filtered.
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void MinimalQuality(VariantContext line, double minQ) {
	/*
	 * To compare two doubles you need
	 * Double.compare(line.getPhredScaledQual(),minQ), after both doubles
	 * are compared then it return a int. It can be -1 if
	 * line.getPhredScaledQual() is less then minQ. Or 1 if
	 * line.getPhredScaledQual() is greater then minQ . Finally it can be 0
	 * if there are equal to each other.
	 */
	int MinimalQuality = Double.compare((double) line.getPhredScaledQual(), minQ);
	if (MinimalQuality > 0) {
	    System.out.println(line.getPhredScaledQual() + " is greater then " + minQ);
	} else if (MinimalQuality < 0) {
	    System.out.println(line.getPhredScaledQual() + " is lesser then " + minQ);
	} else {
	    System.out.println(line.getPhredScaledQual() + " are equal " + minQ);
	}

    }

    /**
     * Include or exclude sites that contain an indel.
     *
     * @param line VCF snip line that will be analysed.
     * @param args Acts as a flag to activate either the keep-only-indels (true)
     * or the remove-indels (false).
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void Indel(VariantContext line, boolean args) {
	//--keep-only-indels
	if (args == true) {
	    if (line.isIndel() == true) {
		System.out.println("Keep " + line + " since it has a Indel. Only the lines that have a indel are processed further.");
	    } else {
		System.out.println("Reject " + line + " since it has no a Indel. Only the lines that have a indel are processed further.");
	    }
	} //--remove-indels
	else {
	    if (line.isIndel() == false) {
		System.out.println("Reject " + line + " since it has a Indel. Only the lines that have no indels are processed further.");
	    } else {
		System.out.println("Keep " + line + " since it has no a Indel Only the lines that have no indels are processed further.");
	    }
	}
    }

    /**
     * Include sites based on a FASTA-like file. The file needs to contain
     * digits between 0 and 9 to indicate a position. Entries are rejected if
     * they fail to be similar to the mask.
     *
     *
     * @param line VCF snip line that will be analysed.
     * @param fileContent contains digits ranging from 0 to 9, indication
     * positions which could be potentially masked.
     * @param mask User defined digit which determines what position would be
     * kept.
     * @param inverse Acts as a flag to either inverse the fileContent (false)
     * or leave it as it is (true).
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void mask(VariantContext line, String fileContent, String mask, boolean inverse) {
	/*
	 * Mask needs to have a fasta file containing digits, these digits serve
	 * to mask certain entries in the chromosome (disqualify vcf line).
	 * Also mask would need at a later date a fasta file reader. This is not
	 * included yet
	 */
	if (inverse == false) {
	    String pos = fileContent.substring(line.getStart(), line.getEnd() + 1);
	    if (!pos.equals(mask)) {
		System.out.println("This line needs to be masked");

	    }
	} else {
	    String reverse = new StringBuffer(fileContent).reverse().toString();
	    String pos = reverse.substring(line.getStart(), line.getEnd() + 1);
	    if (!pos.equals(mask)) {
		System.out.println("This line needs to be masked");

	    }
	}
    }

    /**
     *
     * @param line VCF snip line that will be analysed.
     * @param NIndividuals Numbers of all the VCF SNP's.
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void MeanDept(VariantContext line, int NIndividuals) {
	/*
	 *
	 * Note: this is based on the void
	 * variant_file::filter_sites_by_mean_depth(double min_mean_depth,
	 * double max_mean_depth) in variant_file_filters.cpp MeanDept would
	 * require a mean sum that counts all the means given in the file.
	 * Another requirement of MeanDept is it needs a Number of Individuals
	 * (lines in a file minus the header and empty lines). Once all the
	 * depts are added to the mean sum, a mean can be calculated using sum/
	 * individuals. After this each mean can be compared.
	 *
	 * Also the MinMeanDepth can not be lower than 0 and/or MaxMeanDepth can
	 * not exceed the max value of a float.
	 *
	 * Data: NIndividuals consits out of 669 lines in the regio.txt
	 *
	 */
    }

    /**
     * Filters sites on the basis of their FILTER flag, this FILTER flag is in
     * the vcf file.
     *
     * @param line VCF snip line that will be analysed.
     * @param all Acts as flag to either remove all the filtered (true) or
     * remove specific filters (false).
     * @param keep Acts as flag to either keep a certain filter condition (true)
     * or remove a certain filter condition (false).
     * @param condition User defined condition that either keeps or removes
     * filters based on that condition.
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    void FilterStatus(VariantContext line, boolean all, boolean keep, String condition) {
	//If you would like to keep only the sites that pass all filters use the --remove-filtered-all option. 
	if (all == true) {
	    //If a filter has not passed, flag it for removal
	    if (line.isNotFiltered() == false) {
		System.out.println("Line: " + line + " is rejected since it has not passed: " + line.getFilters());
	    }

	}//User want to keep or remove a specific filter condition.
	else {
	    //If you want to keep a certain  filter condition.
	    if (keep == true) {
		System.out.println(line.getFilters());
		if (!line.getFilters().contains(condition)) {
		    System.out.println("Could not find " + condition + " in " + line.getFilters() + " as a result it is marked for removal");
		}

	    }//Else you want to remove a certain  filter condition.
	    else {
		if (line.getFilters().contains(condition)) {
		    System.out.println("Found " + condition + " in " + line.getFilters() + " as a result it is marked for removal");
		}
	    }
	}
    }

    /**
     * Include only sites with alleles within the specified range.
     *
     * @param line VCF snip line that will be analysed.
     * @param MinAllels User defined int, determines the range of the option
     * --min-alleles. If user does not give a value, the program will ignore the
     * option.
     * @param MaxAllels User defined int, determines the range of the option
     * --max-alleles. If user does not give a value, the program will ignore the
     * option.
     *
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public void AlleleRanges(VariantContext line, int MinAllels, int MaxAllels) {
	// If --min-alleles is only given as a AlleleRanges, than FilterHandler assigns MaxAllels to -1. So that only the option --min-alleles  works.
	if (MinAllels > 0 && MaxAllels == -1) {
	    System.out.println("In MinAllels");
	    if (line.getNAlleles() < MinAllels) {
		System.out.println("Allel " + line.getNAlleles() + " is to low. So it is marked for removal.");
	    }
	}
	// If --max-alleles is only given as a AlleleRanges, than FilterHandler assigns MaxAllels to -1. So that only the option --max-alleles works.
	if (MaxAllels > 0 && MinAllels == -1) {
	    System.out.println("In MaxAllels");
	    if (line.getNAlleles() > MaxAllels) {
		System.out.println("Allel " + line.getNAlleles() + " is to high. So it is marked for removal.");
	    }
	}
	// Works if both options are given.
	if (MinAllels > 0 && MaxAllels > 0) {
	    System.out.println("In MinAllels > 0 && MaxAllels > 0");
	    if (line.getNAlleles() < MinAllels | line.getNAlleles() > MaxAllels) {
		System.out.println("Allel " + line.getNAlleles() + " is either to small or to high. So it is marked for removal.");
	    }
	}
    }

    public void FilterOnThinning(int minSnpDist) {
	if (minSnpDist < 0) {
	    System.out.println("Min snp distance has to be 1 or higher");
	}

	int positionPrevious;

	//positionPrevious = 
    }

    /**
     * Include SNP(s) with matching ID (e.g. a dbSNP rsID)
     *
     * @param line VCF snip line that will be analysed.
     * @param snp User defined String, determines the SNP ID which will be kept.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public void SNP(VariantContext line, String snp) {
	List<String> snps = asList(snp.split(","));
	if (snps.contains(line.getID())) {
	    System.out.println("Line is approved based on: " + line.getID() + " since it's in " + snps);
	} else {
	    System.out.println("Line is rejected based on: " + line.getID() + " since it's not in " + snps);
	}

    }

}
