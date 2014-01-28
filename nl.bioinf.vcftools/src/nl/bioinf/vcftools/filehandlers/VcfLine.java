/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.filehandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.broadinstitute.variant.variantcontext.Allele;
import org.broadinstitute.variant.variantcontext.Genotype;
import org.broadinstitute.variant.variantcontext.GenotypeBuilder;
import org.broadinstitute.variant.variantcontext.VariantContext;
import org.broadinstitute.variant.variantcontext.VariantContextBuilder;

/**
 *
 * @author Sergio Bondietti <sergio@bondietti.nl>
 */
public class VcfLine {

    private VariantContext vc;

    /**
     * Constructor using GATK VariantContext.
     *
     * @param vc
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    VcfLine(VariantContext vc) {
        this.vc = vc;
    }

    @Override
    public String toString() {
        return "VcfLine{" + "vc=" + vc + '}';
    }

    /**
     * Replaces individuals with totally empty genotypes when filter list contains False
     *
     * @param filterList
     */
    public void filterIndividuals(List<Boolean> filterList) {
        // Only peform this action when where are actual individuals to be filtered
        if (filterList.contains(false)) {
            VariantContextBuilder vcb = new VariantContextBuilder(this.vc);
            List<Genotype> newGenotypes = new ArrayList<>();

            // create empty GATK genotype
            GenotypeBuilder gtb = new GenotypeBuilder();
            Genotype emptyGenotype = gtb.make();

            // Loop trough filter list and keep genotype when true
            int i = 0;
            for (Boolean filter : filterList) {
                if (filter == true) {
                    newGenotypes.add(vc.getGenotype(i));
                } else {
                    newGenotypes.add(emptyGenotype);
                }
                i++;
            }
            // store in builder object and set current replace VariantContext object with the builder one
            vcb.genotypes(newGenotypes);
            this.vc = vcb.make();
        }
    }

    /**
     * Replaces genotypes with empty alleles version of original genotypes when filter list contains false items
     *
     * @param filterList
     */
    public void filterGenotypes(List<Boolean> filterList) {
        // Only peform this action when where are actual genotypes to be filtered
        if (filterList.contains(false)) {
            // Create VariantContextBuilder using current VariantContext as base
            VariantContextBuilder vcb = new VariantContextBuilder(this.vc);
            List<Genotype> newGenotypes = new ArrayList<>();
            Genotype oldGenotype;

            // Loop trough filter list and keep genotype when true
            int i = 0;
            for (Boolean filter : filterList) {
                if (filter == true) {
                    newGenotypes.add(vc.getGenotype(i));
                } else {
                    // Get the old genotype and build a new genotype from it
                    oldGenotype = vc.getGenotype(i);
                    GenotypeBuilder gtb = new GenotypeBuilder(oldGenotype);

                    // Replace the alleles of the new genotype with empty ones
                    List<Allele> oldAlleles = oldGenotype.getAlleles();
                    List<Allele> newAlleles = new ArrayList<>();
                    // for each old allele element create an empty new one
                    for (Allele allele : oldAlleles) {
                        newAlleles.add(Allele.NO_CALL);
                    }
                    // set the new alleles in genotypebuilder object and add to the new genotype
                    gtb.alleles(newAlleles);
                    newGenotypes.add(gtb.make());
                }
                i++;
            }
            // finally we are implementing the new genotypes in the new VariantContext and updating the current one with the new one
            vcb.genotypes(newGenotypes);
            this.vc = vcb.make();
        }
    }

    /**
     * Get original GATK VariantContext for internal use
     *
     * @return
     */
    public VariantContext getBroadinstituteVariantContext() {
        return this.vc;
    }

    /**
     * Get the identifier of the SNP of the line.
     *
     * @return Identifier
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getId() {
        return vc.getID();
    }

    /**
     * Get the chromosome of the SNP of the line.
     *
     * @return Chromosome
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getChr() {
        return vc.getChr();
    }

    /**
     * Get the reference of the SNP of the line.
     *
     * @return Allele
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getReferenceAllele() {
        return vc.getReference().toString();
    }

    /**
     * Get the alt alleles of the SNP of the line.
     *
     * @return Alleles
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public List<String> getAltAlleles() {
        // convert list of objects to list of strings
        List<Allele> allelesObjects = this.vc.getAlternateAlleles();
        List<String> allelesStrings = new ArrayList<String>();
        for (Object object : allelesObjects) {
            allelesStrings.add(object != null ? object.toString() : null);
        }
        // Return list of strings
        return allelesStrings;
    }

    /**
     * Get the position of the SNP of the line.
     *
     * @return Position
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getPosition() {
        return this.vc.getStart();
    }

    /**
     * Get the Quality of the SNP of the line.
     *
     * @return Quality
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public double getQual() {
        return this.vc.getPhredScaledQual();
    }

    /**
     * Get the depth.
     *
     * @return depth
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getDp() {
        return this.vc.getAttributeAsInt("DP", 0);
    }

    /**
     * Get the number of genotypes.
     *
     * @return Number of genotypes
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getGenotypeNumber() {
        return vc.getGenotypes().size();
    }

    /**
     * Get if an indel is present.
     *
     * @return boolean
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public boolean getIndel() {
        return vc.isIndel();
    }

    /**
     * Get if it is not filtered.
     *
     * @return boolean
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public boolean getNotFiltered() {
        return vc.isNotFiltered();
    }

    /**
     * Get the specific filter status.
     *
     * @return Set<String> containing the filter status.
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public Set<String> getSpecificFilter() {
        return vc.getFilters();
    }

    /**
     * Get the amount of allels.
     *
     * @return int
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public int getNAllels() {
        return vc.getNAlleles();
    }

    /**
     * Get the minor allele count.
     *
     * @return double
     * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
     */
    public double getMinorAlleleCount() {
        return (double) vc.getAttribute("AC");
    }

    /**
     * Get an attribute as boolean of the SNP of the line.
     *
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public boolean getAttributeAsBoolean(String attribute) {
        return this.vc.getAttributeAsBoolean(attribute, false);
    }

    /**
     * Get an attribute as double of the SNP of the line.
     *
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public double getAttributeAsDouble(String attribute) {
        return this.vc.getAttributeAsDouble(attribute, 0.0);
    }

    /**
     * Get an attribute as int of the SNP of the line.
     *
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public int getAttributeAsInt(String attribute) {
        return this.vc.getAttributeAsInt(attribute, 0);
    }

    /**
     * Get an attribute as string of the SNP of the line.
     *
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public String getAttributeAsString(String attribute) {
        return this.vc.getAttributeAsString(attribute, null);
    }

    /**
     * Get an attribute of the SNP of the line.
     *
     * @param attribute The attribute to return its value of.
     * @return Attribute value
     * @author Marco Roelfes <marcoroelfes@gamil.com>
     */
    public Object getAttribute(String attribute) {
        return this.vc.getAttribute(attribute);
    }

    /**
     * Get the genotype using the specified index number
     *
     * @param index
     * @return
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public VcfGenotype getGenotype(int index) {
        return new VcfGenotype(this.vc.getGenotype(index));
    }

    /**
     * Set the genotypes for this line
     *
     * @param genotypes
     * @author Sergio Bondietti <sergio@bondietti.nl>
     */
    public void setGenotypes(List<VcfGenotype> genotypes) {
        // Create VariantContextBuilder using current VariantContext as base
        VariantContextBuilder vcb = new VariantContextBuilder(this.vc);

        List<Genotype> newGenotypes = new ArrayList<>();
        // Loop trough genotypes and store as GATK genotypes
        for (VcfGenotype i : genotypes) {
            newGenotypes.add(i.getBroadinstituteGenotype());
        }
        // store in builder object and set current replace VariantContext object with the builder one
        vcb.genotypes(newGenotypes);
        this.vc = vcb.make();
    }

}
