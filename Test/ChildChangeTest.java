package agh.cs.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildChangeTest {
    JungleMap map = new JungleMap(30, 100, 0.2);
    Animal animalFirstParent = new Animal(map);
    Animal animalSecondParent = new Animal(map);

    @Test
    void childAdded() {
        Animal child=animalFirstParent.createChild(animalSecondParent);
        assertEquals(child,animalFirstParent.getObservers().peek().getChildren().get(0));
    }

    @Test
    void getChildren() {
        Animal child=animalFirstParent.createChild(animalSecondParent);
        assertEquals(child,animalFirstParent.getObservers().peek().getChildren().get(0));
    }
}