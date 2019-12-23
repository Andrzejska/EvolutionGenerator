package agh.cs.project;

import agh.cs.project.enums.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {
    JungleMap map = new JungleMap(30, 100, 0.2);
    Animal animal = new Animal(map, -2);

    @Test
    void isOccupied() {
        map.place(animal);
        assertEquals(map.isOccupied(animal.getPosition()), true);
    }

    @Test
    void place() {
        map.place(animal);
        assertEquals(animal.equals(map.animalsOrder.get(0)), true);
    }

    @Test
    void deadAnimalRemove() {
        map.place(animal);
        map.deadAnimalRemove();
        assertEquals(map.animalsOrder.contains(animal), false);
    }

    @Test
    void eatProcess() {
        Animal animal = new Animal(map);
        map.place(new Grass(new Vector2d(0, 1)));
        map.place(animal);
        animal.move(MoveDirection.FORWARD);
        map.eatProcess();
        assertEquals(animal.getEnergy(), 100);
    }

    @Test
    void reproductionProcess() {
        Animal firstParent=new Animal(map,55);
        Animal secondParent=new Animal(map,65);
        map.place(firstParent);
        map.place(secondParent);
        int energyFirstP=firstParent.getEnergy();
        assertEquals(map.reproductionProcess(),true);
        firstParent.getObservers().get(0).childrenShow();

    }


}