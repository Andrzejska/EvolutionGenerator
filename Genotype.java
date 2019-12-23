package agh.cs.project;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Genotype {
    private Integer[] geneCount;
    private Integer[] genes;

    public Genotype() {
        this.geneCount = new Integer[8];
        this.genes = new Integer[32];

        for (int i = 0; i < 32; i++) {
            this.genes[i] = i % 8;
        }
        this.countGenes();
        this.addMissingGenes();
        this.sortGenes();
    }

    public Genotype(Genotype firstParent, Genotype secondParent) {
        this.geneCount = new Integer[8];
        this.genes = new Integer[32];
        int boundFirst = ThreadLocalRandom.current().nextInt(0, 31);
        int boundSecond = ThreadLocalRandom.current().nextInt(boundFirst + 1, 32);
        int part = ThreadLocalRandom.current().nextInt(0, 3);
        Integer[] firstParentGenes = firstParent.getGeneArray();
        Integer[] secondParentGenes = secondParent.getGeneArray();
        Integer[] indices = {0, boundFirst, boundSecond, 32};
        for (int i = 0; i < 3; i++) {
            if (i == part) {
                for (int j = indices[i]; j < indices[i + 1]; j++) {
                    this.genes[j] = secondParentGenes[j];
                }
            } else {
                for (int j = indices[i]; j < indices[i + 1]; j++) {
                    this.genes[j] = firstParentGenes[j];
                }
            }
        }
        this.countGenes();
        this.addMissingGenes();
        this.sortGenes();
    }


    public Integer[] getGeneArray() {
        Integer[] ret = new Integer[32];
        for (int i = 0; i < 32; i++)
            ret[i] = this.genes[i];
        return ret;
    }

    public Integer[] getGeneCountArray() {
        Integer[] ret = new Integer[8];
        for (int i = 0; i < 8; i++)
            ret[i] = this.geneCount[i];
        return ret;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }

    public String toLongString() {
        return Arrays.toString(this.genes) + " " + Arrays.toString(this.geneCount);
    }

    private void countGenes() {
        for (int i = 0; i < 8; i++) {
            this.geneCount[i] = 0;
        }
        for (int i = 0; i < 32; i++)
            this.geneCount[this.genes[i]]++;
    }

    private void sortGenes() {
        int startIndex = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = startIndex; j < startIndex + this.geneCount[i]; j++) {
                this.genes[j] = i;
            }
            startIndex += this.geneCount[i];
        }
    }

    private void addMissingGenes() {
        int gene = 0;
        for (int i = 0; i < 8; i++) {
            if (this.geneCount[i] > 0)
                continue;
            do {
                gene = ThreadLocalRandom.current().nextInt(0, 8);
            } while (this.geneCount[gene] <= 1);
            this.geneCount[i]++;
            this.geneCount[gene]--;
            for (int j = 0; j < 32; j++) {
                if (!this.genes[j].equals(gene))
                    continue;
                this.genes[j] = i;
                break;
            }
        }
    }
}
