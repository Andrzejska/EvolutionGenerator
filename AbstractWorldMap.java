package agh.cs.project;

import agh.cs.project.interfaces.IMapElement;
import agh.cs.project.interfaces.IWorldMap;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

abstract class AbstractWorldMap implements IWorldMap {
    public Vector2d lowerLeft = null;
    public Vector2d upperRight = null;
    public int deadAnimalLifeLength=0;
    public int deadAnimalCount=0;
    protected ArrayList<Animal> animalsOrder = new ArrayList<>();
    protected LinkedHashMap<Vector2d, List<Animal>> animals = new LinkedHashMap<>();
    protected LinkedHashMap<Vector2d, List<Grass>> grass = new LinkedHashMap<>();

    protected int plantEnergy;
    protected int moveEnergy;
    protected int startEnergy;



    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    public boolean place(IMapElement animal) {
        if (animal instanceof Animal) {
            this.animalsOrder.add((Animal) animal);
            if (!animals.containsKey(animal.getPosition())) {
                List<Animal> animalsOnPosition = new LinkedList<>();
                animalsOnPosition.add((Animal) animal);
                this.animals.put(animal.getPosition(), animalsOnPosition);
            } else {
                this.animals.get(animal.getPosition()).add((Animal) animal);
            }
            return true;
        }
        if (isOccupied(animal.getPosition())) return false;
        if (animal instanceof Grass) {
            List<Grass> grassOnPosition = new LinkedList<>();
            grassOnPosition.add((Grass) animal);
            this.grass.put(animal.getPosition(), grassOnPosition);
        }
        return true;
    }

    public void deadAnimalRemove() {
        for (int i = 0; i < animalsOrder.size(); i++) {
            Animal animal = animalsOrder.get(i);
            if (animal.getEnergy() <= 0) {
                deadAnimalLifeLength+=animal.getLifeLength();
                deadAnimalCount++;
                animalsOrder.remove(animal);
                animals.get(animal.getPosition()).remove(animal);
                if (animals.get(animal.getPosition()).isEmpty()) {
                    animals.remove(animal.getPosition());
                }
            }
        }
    }

    public void eatProcess() {
        Iterator animalIterator = this.animals.entrySet().iterator();
        while (animalIterator.hasNext()) {
            Map.Entry animalsPosition = (Map.Entry) animalIterator.next();
            List<Animal> animalsPositionList = (List<Animal>) animalsPosition.getValue();
            if (grass.containsKey(animalsPosition.getKey())) {
                Stack<Animal> animalMaxE = new Stack<>();
                animalMaxE.push(new Animal((JungleMap) this, 1));
                for (Animal animal : animalsPositionList) {
                    if (animal.getEnergy() > animalMaxE.peek().getEnergy()) {
                        animalMaxE.clear();
                        animalMaxE.push(animal);
                    }
                    if (animal.getEnergy() == animalMaxE.peek().getEnergy()) {
                        animalMaxE.push(animal);
                    }
                }
                grass.remove(animalMaxE.peek().getPosition());

                while (!animalMaxE.isEmpty()) {
                    Animal animal = animalMaxE.peek();
                    animal.setEnergy(animal.getEnergy() + (int) (plantEnergy / animalMaxE.size()));
                    animalMaxE.pop();
                }
            }
        }
    }

    public boolean reproductionProcess() {
        Iterator animalIterator = animals.entrySet().iterator();
        while (animalIterator.hasNext()) {
            Map.Entry animalsPosition = (Map.Entry) animalIterator.next();
            List<Animal> animalsPositionList = (List<Animal>) animalsPosition.getValue();
            if (animalsPositionList.size() >= 2) {
                Animal[] parents = new Animal[2];
                parents[0] = animalsPositionList.get(0);
                parents[1] = animalsPositionList.get(1);
                for (Animal animal : animalsPositionList) {
                    if (animal.getEnergy() > parents[1].getEnergy()) {
                        if (animal.getEnergy() > parents[0].getEnergy()) {
                            parents[1] = parents[0];
                            parents[0] = animal;
                        } else {
                            parents[1] = animal;
                        }
                    }
                }
                if (parents[1].getEnergy() > (parents[1].getStartEnergy() / 2)) {
                    Animal child = parents[0].createChild(parents[1]);
                    place(child);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<IMapElement> objectAt(Vector2d position) {
        ArrayList<IMapElement> mapElement = new ArrayList<>();
        List<Animal> animalsP = animals.get(position);
        List<Grass> grassP = grass.get(position);
        if (animalsP != null) {
            for (Animal animal : animalsP) {
                mapElement.add((IMapElement) animal);
            }
            return mapElement;
        } else {
            if (grassP != null) {
                mapElement.add((IMapElement) grassP.get(0));
                return mapElement;
            }

        }
        return null;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public List<Grass> getGrass() {
        List<Grass> oGrass = new ArrayList<>();
        Iterator grassIterator = grass.entrySet().iterator();
        while (grassIterator.hasNext()) {
            Map.Entry aGrass = (Map.Entry) grassIterator.next();
            List<Grass> cGrass = (List<Grass>) aGrass.getValue();
            oGrass.add(cGrass.get(0));
        }
        return oGrass;
    }

    public ArrayList<Animal> getAnimals() {
        return animalsOrder;
    }
}
