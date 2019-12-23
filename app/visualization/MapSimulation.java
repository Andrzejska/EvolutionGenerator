package agh.cs.project.app.visualization;


import agh.cs.project.JungleMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MapSimulation implements ActionListener {


    public final int delay;
    public JungleMap map;
    public int animalNumber;


    public JFrame frame;
    public RenderPanel renderPanel;
    public PlotRenderPanel plotRenderPanel;
    public Timer timer;


    public MapSimulation(JungleMap map, int delay, int animalNumber ) {

        this.map = map;
        this.delay = delay;
        this.animalNumber=animalNumber;

        timer = new Timer(delay, this);

        frame = new JFrame("Evolution Simulator");
        frame.setSize(1400, 800);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        renderPanel = new RenderPanel(map, this);
        renderPanel.setSize(new Dimension(1, 1));

        plotRenderPanel = new PlotRenderPanel(map, this);
        plotRenderPanel.setSize(1, 1);


        frame.add(renderPanel);
        frame.add(plotRenderPanel);

    }

    public void startSimulation() {

        for (int i = 0; i < animalNumber; i++) {
            map.addRandomAnimal();
        }
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        plotRenderPanel.repaint();
        renderPanel.repaint();

        try {
            map.dayTurn();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

}