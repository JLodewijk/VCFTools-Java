To use this program type:

java -jar "nl.bioinf.vcftools.jar" 

Parameters that can be used are:

 -bcf <arg>                    This option defines the BCF file to be
                               processed
 -bed <arg>                    Include a set of sites on the basis of a
                               BED file
 -chr <arg>                    Chromosome identifiers can be used more
                               than once to include multiple chromosomes.
                               Separate the identifiers with ',' if
                               multiple identifiers are given
 -count                        this option results a file with a raw count
                               of allele per site of a given VCF file with
                               the suffix .frq.count
 -depth                        generates a file containing the mean depth
                               per individual. This file has the suffix
                               .idepth
 -excludeBed <arg>             Exclude a set of sites on the basis of a
                               BED files
 -excludePositions <arg>       Exclude a set of sites. Separate with ','
                               if multiple sites are wanted to be given
 -excludePositionsFile <arg>   Exclude a set of sites on the basis of a
                               list of positions in a file
 -excludeSnp <arg>             Exclude SNPs which are given by the user.
                               Separate the snps with a ',' if mulitple
                               snp are given
 -excludeSnpFile <arg>         Exclude a list of SNPs given in a file. The
                               file should contain a list of SNP IDs, with
                               one ID per line
 -freq                         outputs the allel frequency in a file with
                               the suffix .frq
 -fromBp <arg>                 This option defines the physical start
                               position of the site which will be
                               processed. A integer is expected. This
                               option must be used in right after -chr
 -geno <arg>                   Exclude sites on the basis of the
                               proportion of missing data (defined to be
                               between 0 and 1, where 1 indicates no
                               missing data allowed). A double is expected
 -gvcf <arg>                   This option defines the compressed VCF file
                               to be processed
 -h,--help                     Help function
 -hwe <arg>                    Sites with a p-value below the threshold
                               defined by this option are taken to be out
                               of the Hardy-Weinberg Equilibrium and
                               therefore excluded. A double is expected
 -invertMask <arg>             This option can be used to specify a mask
                               file that will be inverted before being
                               applied
 -keepFiltered <arg>           This option can be used to select sites on
                               the basis of specific filter flags
 -keepIndv <arg>               Specify an individual to be kept in the
                               analysis. This option can accept multiple
                               arguments to specify multiple individuals.
                               Each individual should be seperated with a
                               ','. A string is expected
 -keepIndvFile <arg>           Provide a file containing a list of
                               individuals to include in subsequent
                               analysis. Each individual ID (as defined in
                               the VCF header line) should be included on
                               a separate line
 -keepInfo <arg>               This option can be used to select sites on
                               the basis of specific INFO flags, keepInfo
                               is applied before removeInfo if both are
                               given
 -keepOnlyIndels <arg>         include sites that contain an indel
 -mac <arg>                    Include only sites with Minor Allele Count
                               which is higher than the given value. A
                               double is expected
 -maf <arg>                    Include only sites with Minor Allele
                               Frequency which is higher than the given
                               value. A double is expected
 -mask <arg>                   Include sites on the basis of a MASK file
 -maskMin <arg>                Set the threshold value which determines if
                               sites are filtered or not. A double is
                               expected
 -maxAlleles <arg>             Include only sites with a number of alleles
                               which is lower than the given value. For
                               example, to include only biallelic sites,
                               one could use --minAlleles 2. A doubleouble
                               is expected
 -maxDp <arg>                  exclude all genotypes with a sequencing
                               depth which is lower than the given value
 -maxIndv <arg>                Randomly thins individuals so that only the
                               specified number are retained. A double is
                               expected
 -maxIndvMeanDp <arg>          Calculate the mean coverage on a
                               per-individual basis. Only individuals with
                               a coverage which is lower the the given
                               value are included in subsequent analyses.
                               A double is expected
 -maxMac <arg>                 Include only sites with Minor Allele Count
                               which is lower than the given value. A
                               double is expected
 -maxMaf <arg>                 Inlude only sites with Minor Allele
                               Frequency which is lower than the given
                               value. A double is expected
 -maxMeanDp <arg>              Include sites with mean Depth which is
                               lower than the given value. A double is
                               expected
 -maxMissingCount <arg>        Exclude sites which has more than the given
                               value for the number of missing
                               chromosomes. An doubleouble is expected
 -maxNonRefAc <arg>            Include only sites with all Non-Reference
                               Allele Counts which is lower than the given
                               value. A double is expected
 -maxNonRefAf <arg>            Include only sites with all Non-Reference
                               Allele Frequencies which is lower than the
                               given value. A double is expected
 -minAlleles <arg>             Include only sites with a number of alleles
                               which is higher than the given value. For
                               example, to include only biallelic sites,
                               one could use --minAlleles 2. A doubleouble
                               is expected
 -mind <arg>                   Specify the minimum call rate threshold for
                               each individual. A double is expected
 -minDp <arg>                  exclude all genotypes with a sequencing
                               depth which is higher than the given value
 -minGq <arg>                  exclude all genotypes with a quality below
                               the threshold specified by this option
 -minIndvMeanDp <arg>          Calculate the mean coverage on a
                               per-individual basis. Only individuals with
                               a coverage which is higher than the given
                               value are included in subsequent analyses.
                               A double is expected
 -minMeanDp <arg>              Include sites with mean Depth which is
                               higher than higher the given value. A
                               double is expected
 -minQ <arg>                   Include only sites with Quality above this
                               threshold. A double is expected
 -nonRefAc <arg>               Include only sites with all Non-Reference
                               Allele Counts which is higher than the
                               given value. A double is expected
 -nonRefAf <arg>               Include only sites with all Non-Reference
                               Allele Frequencies which is higher than the
                               given value. A double is expected
 -notChr <arg>                 Chromosome identifiers can be used more
                               than once to exlude multiple chromosomes.
                               Separate the identifiers with ',' if
                               multiple identifiers are given
 -out <arg>                    This option defines the output filename
                               prefix for all files generated by vcftools
 -phased                       Only include phased data
 -positions <arg>              Include a set of sites. Separate with ','
                               if multiple sites are wanted to be given
 -positionsFile <arg>          Include a set of sites on the basis of a
                               list of positions in a file
 -removeFiltered <arg>         Exclude sites with a specific filter flag
 -removeFilteredAll            This option removes all sites with a FILTER
                               flag
 -removeFilteredGeno <arg>     This option removes all genotypes based on
                               a specific filter flag.
 -removeFilteredGenoAll        This option removes all genotypes based on
                               a filter flag. Default filter flag is '.'
                               or everything not equal to PASS
 -removeIndels <arg>           Exclude sites that contain an indel
 -removeIndv <arg>             Specify an individual to be removed from
                               the analysis. A string is expected. If
                               --indv also used, --indv will be applied
                               first.
 -removeIndvFile <arg>         Provide a file containing a list of
                               individuals to exclude in subsequent
                               analysis. Each individual ID (as defined in
                               the VCF header line) should be included on
                               a separate line if --keep also used, --keep
                               will be applied first
 -removeInfo <arg>             This option can be used to exclude sites
                               with a specific INFO flag
 -snp <arg>                    This option defines a snp which will be
                               processed
 -snpFile <arg>                Include a list of SNPs given in a file,
                               with one ID per line
 -thin <arg>                   Thin sites so that no two sites are within
                               the specified distance. A double is
                               expected
 -toBp <arg>                   This option defines the physical stop
                               position of the site which will be
                               processed. A integer is expected. This
                               option must be used in right after -fromBp
 -vcf <arg>                    This option defines the VCF file to be
                               processed
