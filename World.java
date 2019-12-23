package agh.cs.project;

import agh.cs.project.app.menu.SettingMenu;

import java.io.FileNotFoundException;

public class World {
    public static void main(String[] args) {
        try {
            PropertiesLoader properties = PropertiesLoader.loadPropFromFile();

            Integer[] defaultMapProperties = {
                    properties.getDelay(),
                    properties.getHeight(),
                    properties.getJungleRatio(),
                    properties.getMoveEnergy(),
                    properties.getPlantEnergy(),
                    properties.getStartEnergy(),
                    properties.getWidth(),
                    properties.getAnimalNumber()
            };
            SettingMenu menu = new SettingMenu();
            menu.startSimulation(defaultMapProperties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
