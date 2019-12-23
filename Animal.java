package agh.cs.project;

import agh.cs.project.enums.MapDirection;
import agh.cs.project.enums.MoveDirection;
import agh.cs.project.interfaces.IAnimalChildrenObserver;
import agh.cs.project.interfaces.IMapElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection mapDirection;
    private JungleMap jungleMap;
    private int energy;
    private int moveEnergy;
    private int plantEnergy;
    public int lifeLength=0;
    int startEnergy;
    private Genotype gens;
    private Stack<IAnimalChildrenObserver> observers = new Stack<>();

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public Genotype getGens() {
        return this.gens;
    }

    public int getLifeLength() {
        return lifeLength;
    }

    public void setLifeLength(int lifeLength) {
        this.lifeLength = lifeLength;
    }

    public Stack<IAnimalChildrenObserver> getObservers() {
        return this.observers;
    }


    public MapDirection getMapDirection() {
        return mapDirection;
    }


    public Animal(JungleMap map, Vector2d initialPosition, Genotype gens, int startEnergy, int initialMoveE, int initialPlantE) {
        this.energy = startEnergy;
        this.moveEnergy = initialMoveE;
        this.position = initialPosition;
        this.jungleMap = map;
        this.mapDirection = this.mapDirection.findType(ThreadLocalRandom.current().nextInt(0, 8));
        this.gens = gens;
        this.observers.push(new ChildChange());
        this.plantEnergy = initialPlantE;
        this.startEnergy = startEnergy;
    }




    public Animal(JungleMap map, Vector2d initialPosition, int initialPlantE) {
        this.energy = 100;
        this.moveEnergy = 1;
        this.position = initialPosition;
        this.jungleMap = map;
        this.mapDirection = this.mapDirection.findType(ThreadLocalRandom.current().nextInt(0, 8));
        this.gens = new Genotype();
        this.observers.push(new ChildChange());
        this.plantEnergy = initialPlantE;
        this.startEnergy = startEnergy;
    }

    public Animal(JungleMap map, int startEnergy) {
        this.energy = startEnergy;
        this.moveEnergy = 1;
        this.position = new Vector2d(0, 0);
        this.jungleMap = map;
        this.mapDirection = MapDirection.NORTH;
        this.gens = new Genotype();
        this.observers.push(new ChildChange());
        this.plantEnergy = 1;
        this.startEnergy = startEnergy;
    }

    public Animal(JungleMap map) {
        this.energy = 100;
        this.moveEnergy = 1;
        this.position = new Vector2d(0, 0);
        this.jungleMap = map;
        this.mapDirection = MapDirection.NORTH;
        this.gens = new Genotype();
        this.observers.push(new ChildChange());
        this.plantEnergy = 1;
        this.startEnergy = 100;
    }

    public String toLongString() {
        return "Current position-->" + this.position.toString() + "\n" + "Current direction->" + mapDirection.toString();
    }


    public String toString() {
        switch (this.mapDirection) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            case NORTH_WEST:
                return "NW";
            case NORTH_EAST:
                return "NE";
            case SOUTH_WEST:
                return "SW";
            case SOUTH_EAST:
                return "SE";
            default:
                return null;
        }
    }


    public Animal createChild(Animal animal) {
        int childEnergy = (this.energy / 4 + animal.getEnergy() / 4);
        this.setEnergy(this.energy - (this.energy / 4));
        animal.setEnergy(animal.energy - (animal.energy / 4));
        Vector2d childPosition;
        do {
            childPosition = new Vector2d(position.x + ThreadLocalRandom.current().nextInt(-1, 2),
                    position.y + ThreadLocalRandom.current().nextInt(-1, 2));
        }
        while (childPosition.equals(animal.getPosition()));

        Animal child = new Animal(this.jungleMap, childPosition, new Genotype(this.gens, animal.getGens()), childEnergy, this.moveEnergy, this.plantEnergy);
        outOfMapCheck(child);
        notifyObservers(child);
        return child;
    }

    public ArrayList<Animal> getChildren() {
        return observers.peek().getChildren();
    }

    /**
     *Checking if Animal left the borders of field.
     *Redefine position if true.
     */
    public void outOfMapCheck(Animal animal){
        if (animal.position.x < animal.jungleMap.lowerLeft.x)
            animal.position = animal.position.add(new Vector2d(animal.jungleMap.upperRight.x + 1, 0));
        if (animal.position.y < animal.jungleMap.lowerLeft.y)
            animal.position = animal.position.add(new Vector2d(0, animal.jungleMap.upperRight.y + 1));
        if (animal.position.x > animal.jungleMap.upperRight.x) {
            animal.position = animal.position.subtract(new Vector2d(animal.jungleMap.upperRight.x + 1, 0));
        }
        if (animal.position.y > animal.jungleMap.upperRight.y) {
            animal.position = animal.position.subtract(new Vector2d(0, animal.jungleMap.upperRight.y + 1));
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD: {
                this.lifeLength++;
                this.jungleMap.animals.get(position).remove(this);
                if (this.jungleMap.animals.get(position).isEmpty())
                    this.jungleMap.animals.remove(position);
                this.position = this.position.add(this.mapDirection.toUnitVector(this.mapDirection));
                this.energy -= this.moveEnergy;
                outOfMapCheck(this);
                if (!this.jungleMap.animals.containsKey(position)) {
                    List<Animal> animalsOnPosition = new LinkedList<>();
                    animalsOnPosition.add(this);
                    this.jungleMap.animals.put(position, animalsOnPosition);
                } else {
                    this.jungleMap.animals.get(position).add((this));
                }
                break;
            }
            case SPIN_45:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 1) % 8);
                break;
            case SPIN_90:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 2) % 8);
                break;
            case SPIN_135:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 3) % 8);
                break;
            case SPIN_180:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 4 )% 8);
                break;
            case SPIN_225:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 5) % 8);
                break;
            case SPIN_270:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 6) % 8);
                break;
            case SPIN_315:
                this.mapDirection = this.mapDirection.findType((this.mapDirection.getId() + 7) % 8);
                break;
            default:
                return;
        }
    }

    void addObserver(IAnimalChildrenObserver observer) {
        observers.push(observer);
    }

    void removeObserver(IAnimalChildrenObserver observer) {
        observers.pop();
    }

    void notifyObservers(Animal animal) {
        for (IAnimalChildrenObserver observer : observers) {
            observer.childAdded(animal);
        }
    }

    public Color toColor() {
        if (energy == 0) return new Color(222, 221, 224);
        if (energy < 0.2 * startEnergy) return new Color(224, 179, 173);
        if (energy < 0.4 * startEnergy) return new Color(224, 142, 127);
        if (energy < 0.6 * startEnergy) return new Color(201, 124, 110);
        if (energy < 0.8 * startEnergy) return new Color(182, 105, 91);
        if (energy < startEnergy) return new Color(164, 92, 82);
        if (energy < 2 * startEnergy) return new Color(146, 82, 73);
        if (energy < 4 * startEnergy) return new Color(128, 72, 64);
        if (energy < 6 * startEnergy) return new Color(119, 67, 59);
        if (energy < 8 * startEnergy) return new Color(88, 50, 44);
        if (energy < 10 * startEnergy) return new Color(74, 42, 37);
        return new Color(55, 31, 027);
    }

}



