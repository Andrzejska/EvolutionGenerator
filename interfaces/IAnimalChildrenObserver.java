package agh.cs.project.interfaces;

import agh.cs.project.Animal;

import java.util.ArrayList;

public interface IAnimalChildrenObserver {
    void childAdded(Animal animal);
    ArrayList<Animal> getChildren();

    void childrenShow();
}
