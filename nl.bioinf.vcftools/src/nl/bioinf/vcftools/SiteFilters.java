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
 * This Java class contains numerous test function for Site filtering.
 * If you want to run this file, than you need to call in a different program (ReadVcf.java) this program.  
 * You can do this by: SiteFilters site = new SiteFilters(); and site.*insert function name*( vcf.getNextIter(file),*insert other parameters*);
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 * @url: http://vcftools.sourceforge.net/options.html#site_filter
 */
public class SiteFilters {

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

    void Bp(VariantContext line, int bp, boolean args) {
        //Args true only if option FromBp is given.
        if (args == true) {
            if (bp > line.getStart()) {
                System.out.println("Line is rejected based on: " + line.getStart() + " since it less then " + bp);
            } else {
                System.out.println("Line is approved based on: " + line.getStart() + " since it more or equal then " + bp);
            }

        } //Else option ToBp was given.
        else {
            if (bp < line.getEnd()) {
                System.out.println("Line is rejected based on: " + line.getEnd() + " since it more then " + bp);
            } else {
                System.out.println("Line is approved based on: " + line.getEnd() + " since it more or equal then " + bp);
            }
        }
    }

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

    void MeanDept(VariantContext line, int NIndividuals) {
        /*
         *
         * Note: this is based on the void
         * variant_file::filter_sites_by_mean_depth(double min_mean_depth,
         * double max_mean_depth) in variant_file_filters.cpp MeanDept would
         * require a mean sum that counts all the means given in the file.
         * Another requirement of MeanDept is it needs a Number of Individual
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
        void FilterStatus(VariantContext line, boolean all, boolean keep) {
	//If you would like to keep only the sites that pass all filters use the --remove-filtered-all option. 
	if (all == true) {
	    //If a filter has not passed, flag it for removal
	    if(line.isNotFiltered() == false){
	    System.out.println("Line: "+line+" is rejected since it has not passed: "+line.getFilters());
	    }

	}//User want to keep or remove a specific filter condition.
	else {
	    //If you want to keep a certained filter condition.
	    if (keep == true) {

	    }//Else you want to remove a certained filter condition.
	    else {

	    }

	}
    }
}

