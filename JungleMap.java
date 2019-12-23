package agh.cs.project;

import agh.cs.project.enums.MoveDirection;

import java.util.concurrent.ThreadLocalRandom;

public class JungleMap extends AbstractWorldMap {

   public Vector2d jungleLowerLeft;
   public Vector2d jungleUpperRight;
   public int height;
   public int width;
    int delay;
    MoveDirection moveDirection = MoveDirection.FORWARD;
    MapVisualizer mapVisualizer = new MapVisualizer(this);
    double jungleRatio;
    int animalNumber;
    public JungleMap(int height, int width, double jungleRatio) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.jungleRatio = jungleRatio;
        this.jungleLowerLeft = new Vector2d((int) (width / 2 - width * (jungleRatio/100) / 2), (int) (height / 2 - height * (jungleRatio/100) / 2));
        this.jungleUpperRight = new Vector2d((int) (width / 2 + width * (jungleRatio/100) / 2), (int) (height / 2 + height * (jungleRatio/100) / 2));
    }

    public JungleMap(int delayInit,int startEnergyInit,int plantEnergyInit,int widthInit,int heightInit,int jungleRatioInit,int moveEnergyInit,int animalNumberInit) {
        this.delay=delayInit;
        this.startEnergy=startEnergyInit;
        this.plantEnergy=plantEnergyInit;
        this.width=widthInit;
        this.height=heightInit;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(widthInit, heightInit);
        this.jungleRatio = jungleRatioInit;
        this.jungleLowerLeft = new Vector2d((int) (width / 2 - width * (jungleRatio/100) / 2), (int) (height / 2 - height * (jungleRatio/100) / 2));
        this.jungleUpperRight = new Vector2d((int) (width / 2 + width * (jungleRatio/100) / 2), (int) (height / 2 + height * (jungleRatio/100) / 2));
        this.moveEnergy=moveEnergyInit;
        this.animalNumber=animalNumberInit;
    }


    public boolean isOccupiedMapVisual(Vector2d position){
        if(this.animals.get(position)!=null||this.grass.get(position)!=null) return true;
        return false;
    }

    public Vector2d toNoBoundedPosition(Vector2d position) {
        int newX;
        int newY;

        if (position.x < lowerLeft.x) {
            newX = (width - Math.abs(position.x % width)) % width;
        } else {
            newX = Math.abs(position.x % width);
        }
        if (position.y < lowerLeft.y) {
            newY = (height - Math.abs(position.y % height)) % height;
        } else {
            newY = Math.abs(position.y % height);
        }

        return new Vector2d(newX, newY);
    }

    public void addRandomAnimal() {
        Animal animal = new Animal(this,
                new Vector2d(ThreadLocalRandom.current().nextInt(0, upperRight.x), ThreadLocalRandom.current().nextInt(0, upperRight.y)),
                new Genotype(),startEnergy,moveEnergy,plantEnergy);
        place(animal);
    }

    void generateGrassField() {
        boolean isDone = false;
        int i = 0;
        do {
            Vector2d newPosition = new Vector2d(ThreadLocalRandom.current().nextInt(0, upperRight.x+1), ThreadLocalRandom.current().nextInt(0, upperRight.y+1));
            ++i;
            if (isOccupied(newPosition)) {
                if (i > (int) (jungleRatio * jungleUpperRight.x * jungleRatio * jungleUpperRight.y)) return;
            } else {
                this.place(new Grass(newPosition));
                isDone = true;
            }
        } while (!isDone);
    }

    void generateGrassJungle() {
        boolean isDone = false;
        int i = 0;
        do {
            Vector2d newPosition = new Vector2d(ThreadLocalRandom.current().nextInt(jungleLowerLeft.x, jungleUpperRight.x), ThreadLocalRandom.current().nextInt(jungleLowerLeft.y, jungleUpperRight.y));
            ++i;
            if (isOccupied(newPosition)) {
                if (i > (int) (jungleRatio * jungleUpperRight.x * jungleRatio * jungleUpperRight.y)) return;
            } else {
                this.place(new Grass(newPosition));
                isDone = true;
            }
        } while (!isDone);
    }

    public void run() {
        for (int k = 0; k < animalsOrder.size(); k++) {
            Animal current = this.animalsOrder.get(k);
            current.move(this.moveDirection.findType(current.getGens().getGeneArray()[ThreadLocalRandom.current().nextInt(0, 8)]));
            current.move(MoveDirection.FORWARD);
        }
    }

    public void dayTurn() throws InterruptedException{
        System.out.println(this.toString());
        deadAnimalRemove();
        run();
        eatProcess();
        reproductionProcess();
        generateGrassField();
        generateGrassJungle();
        Thread.sleep(0);
        System.out.print("\033[H\033[2J");
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
