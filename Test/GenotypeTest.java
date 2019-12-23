package agh.cs.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenotypeTest {
    Genotype gens = new Genotype();
    Integer[] gensTest = new Integer[32];
    Integer[] gensCountTest = new Integer[8];

    @Test
    void getGeneArray() {
        for (int i = 0; i < 32; i++) {
            gensTest[i] = i % 8;
        }
        assertArrayEquals(gensTest, gens.getGeneArray());
    }

    @Test
    void getGeneCountArray() {
        for (int i = 0; i < 8; i++) {
            gensCountTest[i] = 4;
        }
        assertArrayEquals(gensCountTest, gens.getGeneCountArray());
    }
}
