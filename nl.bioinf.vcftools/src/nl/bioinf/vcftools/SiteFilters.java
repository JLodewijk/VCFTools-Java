/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashMap;
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

    int positionPrevious = 0;

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

    /**
     * include snp when they are not closer then given minimal snp distance to
     * each other
     *
     * @author Marco Roelfes <marcoroelfes@gmail.com>
     * @param line VCF snip line that will be analysed.
     * @param minSnpDist minimal snp distance
     */
    public void FilterOnThinning(VariantContext line, int minSnpDist) {
        //check if minsnpDist is valid
        if (minSnpDist < 0) {
            System.out.println("Min snp distance has to be 0 or higher");
        }

        // check if it is first line, if it is set position
        if (this.positionPrevious == 0) {
            this.positionPrevious = line.getStart();
            // if not first line check if positions are to close, if to close reject line, else approve line and set new position
        } else {
            if ((line.getStart() - this.positionPrevious) < minSnpDist) {
                System.out.println("This SNP is too close to the previous because:" + this.positionPrevious + "and"
                        + line.getStart() + "are less than" + minSnpDist + "basepairs apart form each other");

            } else {
                System.out.println("This SNP is is approved:" + this.positionPrevious + "and"
                        + line.getStart() + "are more then" + minSnpDist + "basepairs apart form each other");
                this.positionPrevious = line.getStart();
            }

        }

    }

    /**
     * Include a list of SNP(s) ID. Filters any ID not corresponding to the
     * snps.
     *
     * @param line VCF snip line that will be analysed.
     * @param snps List<String>, contains either the data from --snp or the
     * –-snps file. Even if --snp is a single string it still needs to be given
     * as an ArrayList
     * @param args Acts as flag to either include SNP(s) with matching ID (true)
     * or exclude SNP(s) with matching ID.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public void SNPs(VariantContext line, List<String> snps, boolean args) {
        //Will include SNPs with matching ID
        if (args == true) {
            System.out.println("In true");
            if (snps.contains(line.getID())) {
                System.out.println("Line is approved based on: " + line.getID() + " since it's in " + snps);
            } else {
                System.out.println("Line is rejected based on: " + line.getID() + " since it's not in " + snps);
            }

        }//Will exclude SNPs with matching ID 
        else {
            if (snps.contains(line.getID())) {
                System.out.println("Line is rejected based on: " + line.getID() + " since it's in " + snps);
            } else {
                System.out.println("Line is approved based on: " + line.getID() + " since it's not in " + snps);
            }
        }
    }

    /**
     * Include/exclude a set of sites on the basis of a list of positions in a
     * file.
     *
     *
     * @param line VCF snip line that will be analysed.
     * @param pos HashMap, contains the data from the file given in the option:
     * --positions <filename> or --exclude-positions <filename>. The key is the
     * position and the value is the chromosome.
     * @param args Acts as flag to either include positions (true) or exclude
     * positions based on their position and chromosme.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public void Positions(VariantContext line, HashMap pos, boolean args) {
        if (args == true) {
            String position = Integer.toString(line.getStart());
            if (pos.containsKey(position) && pos.containsValue(line.getChr())) {
                System.out.println(line.getChr() + " and " + line.getStart() + " in " + pos + " therefore the are included.");
            } else {
                System.out.println(line.getChr() + " and " + line.getStart() + " not in " + pos + " therefore the are excluded.");
            }
        } else {
            String position = Integer.toString(line.getStart());
            if (pos.containsKey(position) && pos.containsValue(line.getChr())) {
                System.out.println(line.getChr() + " and " + line.getStart() + " in " + pos + " therefore the are excluded.");
            } else {
                System.out.println(line.getChr() + " and " + line.getStart() + " not in " + pos + " therefore the are included.");
            }
        }
    }

    /**
     * Check is allele frequency is between the given range
     *
     * @param line VCF snip line that will be analysed.
     * @param minAlleleFreq minimum allele frequency
     * @param maxAlleleFreq maximum allele frequency
     * @author Marco Roelfes <marcoroelfes@gmail.com>
     */
    public void AlleleFrequency(VariantContext line, float minAlleleFreq, double maxAlleleFreq) {
        Object valObj = line.getAttribute("AF");
        //System.out.println(valObj.getClass().getName());
        boolean reject = false;
        if (valObj instanceof String) {
            //handle single value
            Double val = Double.valueOf((String) valObj);
            //if val is between threshold approve line, else reject line
            if (val < maxAlleleFreq && val > minAlleleFreq) {
                System.out.println("Line is approved allelfreq is between" + minAlleleFreq + " and " + maxAlleleFreq);
            } else {
                System.out.println("Line is rejected allelfreq is not between" + minAlleleFreq + " and " + maxAlleleFreq);
            }
            //System.out.println("val="+val);
        } else {
            //ArrayList value
            ArrayList<String> values = (ArrayList<String>) valObj;
            List<Double> valuesDoubles = new ArrayList<Double>();
            //set String ArrayList to Double ArrayList            
            for (String str : values) {
                valuesDoubles.add(str != null ? Double.parseDouble(str) : null);
            }
            //for every double in ArrayList check if between threshold
            for (double dValue : valuesDoubles) {
                if (dValue < maxAlleleFreq && dValue > minAlleleFreq) {
                    reject = false;
                } else {
                    reject = true;
                    break;

                }
            }

            //check if to reject or approve SNP
            if (reject == true) {
                System.out.println("Line is rejected allelfreq is not between" + minAlleleFreq + " and " + maxAlleleFreq);
            } else {
                System.out.println("Line is approved allelfreq is between" + minAlleleFreq + " and " + maxAlleleFreq);
            }

        }

//            tr
        //checks if allele frecuency is between given maximum and minimum
        //if true approve snp
        //if false decline snp
    }

    /**
     * Check if meanDepth is between given thresholds
     *
     * @param line VCF snip line that will be analysed.
     * @param minDepth minimum Depth
     * @param maxDepth maximum Depth
     * @author Marco Roelfes <marcoroelfes@gmail.com>
     */
    public void meanDepth(VariantContext line, int minDepth, int maxDepth) {
        //get total depth per site
        int totalDepth = line.getAttributeAsInt("DP", 0);
        //get number of genotypes
        int numberOfGt = line.getGenotypes().size();
        //calculate meanDepth
        float meanDepth = totalDepth / numberOfGt;
        //if meanDepth is between threshold approve line, else decline line
        if (meanDepth < maxDepth && meanDepth > minDepth) {
            System.out.println("line approved meanDepth is between " + minDepth + " and " + maxDepth);
        } else {
            System.out.println("line declined meanDepth is not between " + minDepth + " and " + maxDepth);
        }

    }
    /**
     * Check is allele count is between the given range
     *
     * @param line VCF snip line that will be analysed.
     * @param minCount minimum allele count
     * @param maxCount maximum allele frequency
     * @author Marco Roelfes <marcoroelfes@gmail.com>
     */
    public void minAlleleCount(VariantContext line, int minCount, int maxCount) {
        Object valObj = line.getAttribute("AC");

        boolean reject = false;
        if (valObj instanceof String) {
            //handle single value
            int val;
            val = Integer.parseInt((String) valObj);
            //if val is between threshold approve line, else reject line
            if (val < maxCount && val > minCount) {
                System.out.println("Line is approved allelfreq is between" + minCount + " and " + maxCount);
            } else {
                System.out.println("Line is rejected allelfreq is not between" + minCount + " and " + maxCount);
            }
            //System.out.println("val="+val);
        } else {
            //ArrayList value
            ArrayList<String> values = (ArrayList<String>) valObj;
            List<Integer> valuesInteger = new ArrayList<Integer>();
            //set String ArrayList to Integer ArrayList            
            for (String str : values) {
                valuesInteger.add(str != null ? Integer.parseInt(str) : null);
            }
            //for every integer in ArrayList check if between threshold
            for (double dValue : valuesInteger) {
                if (dValue < maxCount && dValue > minCount) {
                    reject = false;
                } else {
                    reject = true;
                    break;

                }
            }

            //check if to reject or approve SNP
            if (reject == true) {
                System.out.println("Line is rejected allelfreq is not between" + minCount + " and " + maxCount);
            } else {
                System.out.println("Line is approved allelfreq is between" + minCount + " and " + maxCount);
            }

        }
    }
}
