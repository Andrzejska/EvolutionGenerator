package agh.cs.project.app.menu;

import agh.cs.project.JungleMap;
import agh.cs.project.app.visualization.MapSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {
    //
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;
    //Fields for data entry
    private JTextField delay;
    private JTextField startEnergy;
    private JTextField plantEnergy;
    private JTextField width;
    private JTextField height;
    private JTextField jungleRatio;
    private JTextField moveEnergy;
    private JTextField animalNumber;
    //Labels to identify the fields
    private JLabel delayLabel;
    private JLabel startEnergyLabel;
    private JLabel plantEnergyLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel jungleRatioLabel;
    private JLabel moveEnergyLabel;
    private JLabel animalNumberLabel;

    //button
    private JButton startButton;

    public SettingsPanel(Integer[] defaultMapProperties) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);


        delayLabel = new JLabel("Real refresh time (ms):           ");
        startEnergyLabel = new JLabel("Animal start energy:              ");
        plantEnergyLabel = new JLabel("Grass energy profit:              ");
        heightLabel = new JLabel("Map height:                       ");
        widthLabel = new JLabel("Map width:                        ");
        jungleRatioLabel = new JLabel("Jungle ratio:                     ");
        moveEnergyLabel = new JLabel("Daily move cost:                ");
        animalNumberLabel = new JLabel("Starting animal number          ");

        //text
        int a = 10;
        delay = new JTextField();
        delay.setColumns(a);
        delay.setText(defaultMapProperties[0].toString());

        plantEnergy = new JTextField();
        plantEnergy.setColumns(a);
        plantEnergy.setText(defaultMapProperties[4].toString());


        startEnergy = new JTextField();
        startEnergy.setColumns(a);
        startEnergy.setText(defaultMapProperties[5].toString());


        width = new JTextField();
        width.setColumns(a);
        width.setText(defaultMapProperties[6].toString());

        height = new JTextField();
        height.setColumns(a);
        height.setText(defaultMapProperties[1].toString());

        jungleRatio = new JTextField();
        jungleRatio.setColumns(a);
        jungleRatio.setText(defaultMapProperties[2].toString());

        moveEnergy = new JTextField();
        moveEnergy.setColumns(a);
        moveEnergy.setText(defaultMapProperties[3].toString());

        animalNumber = new JTextField();
        animalNumber.setColumns(a);
        animalNumber.setText(defaultMapProperties[7].toString());


        //Labels to text fields
        delayLabel.setLabelFor(delay);
        startEnergyLabel.setLabelFor(startEnergy);
        plantEnergyLabel.setLabelFor(plantEnergy);
        widthLabel.setLabelFor(width);
        heightLabel.setLabelFor(height);
        jungleRatioLabel.setLabelFor(jungleRatio);
        moveEnergyLabel.setLabelFor(moveEnergy);
        animalNumberLabel.setLabelFor(animalNumber);

        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l3 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();
        JPanel l7 = new JPanel();
        JPanel l8 = new JPanel();


        l1.add(delayLabel);
        l2.add(startEnergyLabel);
        l3.add(plantEnergyLabel);
        l4.add(widthLabel);
        l5.add(heightLabel);
        l6.add(jungleRatioLabel);
        l7.add(moveEnergyLabel);
        l8.add(animalNumberLabel);

        l1.add(delay);
        l2.add(startEnergy);
        l3.add(plantEnergy);
        l4.add(width);
        l5.add(height);
        l6.add(jungleRatio);
        l7.add(moveEnergy);
        l8.add(animalNumber);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(new JLabel("Map properties"));
        add(l4);
        add(l5);
        add(new JLabel("Energy properties"));
        add(l2);
        add(l3);
        add(l7);
        add(new JLabel("Spawning properties"));
        add(l6);
        add(l8);
        add(new JLabel("Others"));
        add(l1);

        add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JungleMap map = new JungleMap(
                Integer.parseInt(delay.getText()),
                Integer.parseInt(startEnergy.getText()),
                Integer.parseInt(plantEnergy.getText()),
                Integer.parseInt(width.getText()),
                Integer.parseInt(height.getText()),
                Integer.parseInt(jungleRatio.getText()),
                Integer.parseInt(moveEnergy.getText()),
                Integer.parseInt(animalNumber.getText())
                );
        MapSimulation simulation = new MapSimulation(
                map, Integer.parseInt(delay.getText()),
                Integer.parseInt(animalNumber.getText()));

                simulation.startSimulation();

    }
}
