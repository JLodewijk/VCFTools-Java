/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools;

/**
 *
 * @author aponnudurai
 */
public class FilterHandler {

    private VcfLine vcfLine;
    private Settings settings;

    public FilterHandler(Settings settings, VcfLine vcfLine) {
        this.vcfLine = vcfLine;
        this.settings = settings;

    }

    public boolean performFilters() {
        // perform filters
        boolean filterAway = false;
        SiteFilters sf = new SiteFilters();
        if (settings.getChr() != null) {
            boolean include;
            include = sf.InExChromosome(vcfLine.getChr(), settings.getChr());
            if(include == true){
                filterAway = true;
                return true;
            } else{
                filterAway = false;
            }
            
            
        } if (settings.getNotChr() != null) {
            boolean exclude;
            exclude = sf.InExChromosome(vcfLine.getChr(), settings.getNotChr());
            if(exclude == false){
                filterAway = true;
                return true;
            }else{
                filterAway = false;
            }
        } if (settings.getFromBp() != 0 && settings.getToBp() != 0) {
            sf.Bp(vcfLine.getPosition(), settings.getToBp(), settings.getFromBp());
        } else if (settings.getMinQ() != null) {
            sf.MinimalQuality(null, settings.getMinQ());
        } else if (settings.isKeepOnlyIndels()) {
            sf.Indel(null, true);
        } else if (settings.isRemoveIndels()) {
            sf.Indel(null, false);
        } else if (settings.getMask() == null) {
            sf.mask(null, null, settings.getMask(), true);
        } else if (settings.getInvertMask() == null) {
            sf.mask(null, null, settings.getInvertMask(), false);
        } else if (settings.isRemoveFilteredAll() == null) {
            sf.FilterStatus(null, true, false, null);
        } else if (settings.getRemoveFiltered() == null) {
            for (String filterCondition : settings.getRemoveFiltered()) {
                sf.FilterStatus(null, false, false, filterCondition);
            }
        } else if (settings.getKeepFiltered() == null) {
            for (String filterCondition : settings.getKeepFiltered()) {
                sf.FilterStatus(null, false, true, filterCondition);

            }
        } else if (settings.getMinAlleles() == null || settings.getMaxAlleles() == null) {
            sf.AlleleRanges(null, settings.getMinAlleles(), settings.getMaxAlleles());
        } else if (settings.getThin() == null) {
            sf.FilterOnThinning(null, settings.getThin());

        } else if (settings.getSnp() == null) {
            sf.SNPs(null, settings.getSnp(), true);
        } else if (settings.getSnpFile() == null) {
//        sf.SNPs(null, settings.getSnpFile(), true);       values in file must be saved in an arrayList
        } else if (settings.getExcludeSnp() == null) {
            sf.SNPs(null, settings.getExcludeSnp(), false);
        } else if (settings.getExcludeSnpFile() == null) {
//        sf.SNPs(null, settings.getExcludeSnpFile(), false);  values in file must be saved in an arrayList
        } else if (settings.getPositionsFile() == null) {
//        sf.Positions(null, settings.getPositionsFile(), true); values in file must be saved in a hashmap

        } else if (settings.getExcludePositionsFile() == null) {
//        sf.Positions(null, settings.getExcludePositionsFile(), false); values in file must be saved in a hashmap
        } else if (settings.getMaf() == null || settings.getMaxMaf() == null) {
            sf.AlleleFrequencies(null, settings.getMaf(), settings.getMaxMaf(), true); /*true AlleleFrequencies, false Non-Reference Allele Frequencies*/

        } else if (settings.getNonRefAf() == null || settings.getMaxNonRefAf() == null) {
            sf.AlleleFrequencies(null, settings.getNonRefAf(), settings.getMaxNonRefAf(), false);
        } else if (settings.getMinAlleles() == null || settings.getMaxAlleles() == null) {
            sf.AlleleCount(null, settings.getMinAlleles(), settings.getMaxAlleles());
        } else if (settings.getMinMeanDP() == null || settings.getMaxMeanDP() == null) {

            int minMeanDp = settings.getMinMeanDP().intValue();
            int maxMeanDp = settings.getMaxMeanDP().intValue();
            sf.MeanDepth(null, minMeanDp, maxMeanDp);
        } else if (settings.getKeepInfo() == null) {
            for (String infoFlag : settings.getKeepInfo()) {
                sf.InfoFilter(null, infoFlag, true);
            }
        } else if (settings.getRemoveInfo() == null) {
            for (String infoFlag : settings.getRemoveInfo()) {
                sf.InfoFilter(null, infoFlag, false);
            }
        } else if (settings.getMaxMissingCound() == null) {
            sf.MissingCount(null, settings.getMaxMissingCound());
        }

        return filterAway;

    }

}
