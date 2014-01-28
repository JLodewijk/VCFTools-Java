/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filters.site;

import nl.bioinf.vcftools.Settings;
import nl.bioinf.vcftools.filters.AbstractSiteFilter;
import nl.bioinf.vcftools.filehandlers.VcfLine;

/**
 * This class filters a vcf line based on position of the SNP, if a SNP is too close to another it returns false and the line is rejected.
 * @author mhroelfes <marcoroelfes@gmail.com>
 */
public class Thinning extends AbstractSiteFilter {

    int positionPreviousSnp;
     /**
     * filters a vcf line based on position of the SNP, if a SNP is too close to another it returns false and the line is rejected.
     * @param vcfLine
     * @param settings
     * @return
     * @author mhroelfes <marcoroelfes@gmail.com>
     */
    @Override
    public boolean filter(VcfLine vcfLine, Settings settings) {
        //check if minsnpDist is valid
        if (settings.getThin() < 0) {
            System.out.println("Min snp distance has to be 0 or higher");
        }

        // check if it is first line, if it is set position
        if (this.positionPreviousSnp == 0) {
            this.positionPreviousSnp = vcfLine.getPosition();
            return true;
            // if not first line check if positions are to close, if to close reject line, else approve line and set new position
        } else {
            if ((vcfLine.getPosition() - this.positionPreviousSnp) < settings.getThin()) {
                return false;

            } else {

                this.positionPreviousSnp = vcfLine.getPosition();
                return true;
            }

        }
    }

}
