package agh.cs.project;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesLoader {


    private int delay;
    private int width;
    private int height;
    private int jungleRatio;
    private int startEnergy;
    private int plantEnergy;
    private int moveEnergy;
    private int animalNumber;

    static public PropertiesLoader loadPropFromFile() throws FileNotFoundException, IllegalArgumentException {
        Gson gson = new Gson();
        File f = new File("");
        System.out.println(f.getAbsolutePath());
        PropertiesLoader properties = (PropertiesLoader) gson.fromJson(new FileReader("src\\main\\agh\\cs\\project\\properties.json"), PropertiesLoader.class);
        properties.validate();
        return properties;
    }

    private void validate() throws IllegalArgumentException {
        if (this.width <= 0) {
            throw new IllegalArgumentException("Invalid map width");
        }
        if (this.height <= 0) {
            throw new IllegalArgumentException("Invalid map height");
        }
        if (this.jungleRatio <= 0 || this.jungleRatio > 100) {
            throw new IllegalArgumentException("Invalid jungleRatio");
        }
        if (this.startEnergy <= 0) {
            throw new IllegalArgumentException("Invalid jungle height");
        }
        if (this.plantEnergy <= 0) {
            throw new IllegalArgumentException("Invalid plantEnergy");
        }
        if (this.moveEnergy < 0) {
            throw new IllegalArgumentException("Invalid moveEnergy");
        }
        if (this.animalNumber < 0) {
            throw new IllegalArgumentException("Invalid moveEnergy");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getAnimalNumber() {
        return animalNumber;
    }

    public void setAnimalNumber(int animalNumber) {
        this.animalNumber = animalNumber;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getJungleRatio() {
        return jungleRatio;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setJungleRatio(int jungleRatio) {
        this.jungleRatio = jungleRatio;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public void setStartEnergy(int startEnergy) {
        this.startEnergy = startEnergy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }
}
