package agh.cs.project;

import agh.cs.project.enums.MapDirection;
import agh.cs.project.enums.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    JungleMap map = new JungleMap(30, 100,0.2);
    Animal current = new Animal(map);



    @Test
    void move() {
        Animal test=new Animal(map,new Vector2d(4,30),1);
        test.move(MoveDirection.FORWARD);
        assertEquals(test.getPosition(),new Vector2d(4,0));
        current.move(MoveDirection.SPIN_135);
        assertEquals(current.getMapDirection(), MapDirection.SOUTH_EAST);
        assertEquals(test.getEnergy(),99);
    }

    @Test
    void createChild(){
        Animal firstParent=new Animal(map,55);
        Animal secondParent=new Animal(map,65);
        int firstParentE=firstParent.getEnergy();
        int secondParentE=secondParent.getEnergy();
        Animal child=firstParent.createChild(secondParent);
        assertEquals(child.getEnergy(),firstParentE/4 + secondParentE/ 4);
        assertEquals(firstParent.getEnergy(),firstParentE-firstParentE/4);
        assertEquals(firstParent.getPlantEnergy(),child.getPlantEnergy());

    }
}