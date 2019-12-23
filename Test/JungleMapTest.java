package agh.cs.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JungleMapTest {
JungleMap map=new JungleMap(30,100,0.2);
    @Test
    void generateGrassField() {
        map.generateGrassField();
        assertEquals(map.grass.size(),1);
    }

    @Test
    void generateGrassJungle() {
        map.generateGrassJungle();
        map.generateGrassField();
        assertEquals(map.grass.size(),2);
    }
}