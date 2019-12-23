package agh.cs.project;

import agh.cs.project.interfaces.IAnimalChildrenObserver;

import java.util.ArrayList;

public class ChildChange implements IAnimalChildrenObserver {
    ArrayList<Animal> children=new ArrayList<>();


    @Override
    public void childAdded(Animal animal) {
        children.add(animal);
    }

    public ArrayList<Animal> getChildren() {
        return children;
    }

    public void childrenShow(){
        for(Animal animal:children){
            System.out.println(animal.toLongString()+"\n");
        }
    }
}
