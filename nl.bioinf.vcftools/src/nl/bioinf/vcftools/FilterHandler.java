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
        SiteFilters sf = new SiteFilters();
        if (settings.getChr() != null) {
//        sf.Chromosome(null, settings.getChr(), true);
        } else if (settings.getNotChr() != null) {
//        sf.Chromosome(null, settings.getNotChr(), false);
        } else if (settings.getFromBp() != 0 && settings.getToBp() != 0) {
            sf.Bp(null, settings.getToBp(), settings.getFromBp());
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
        } else if (settings.getThin() == null){
        sf.FilterOnThinning(null,settings.getThin());
        
        } else if (settings.getSnp() == null){
        sf.SNPs(null, settings.getSnp(), true);
        } else if (settings.getSnpFile()== null){}

        return true;
        
    }

}
