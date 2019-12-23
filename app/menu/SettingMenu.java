package agh.cs.project.app.menu;

import javax.swing.*;

public class SettingMenu {
    public JFrame menuFrame;

    public SettingMenu() {

        menuFrame = new JFrame("Evolution Simulator (Settings)");
        menuFrame.setSize(500, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

    }
    public void startSimulation(Integer[] defaultMapProperties){
        menuFrame.add(new SettingsPanel(defaultMapProperties));
        menuFrame.setVisible(true);
    }
}

