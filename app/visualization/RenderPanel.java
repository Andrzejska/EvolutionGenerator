package agh.cs.project.app.visualization;


import agh.cs.project.Animal;
import agh.cs.project.Grass;
import agh.cs.project.JungleMap;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    public JungleMap map;
    public MapSimulation simulation;

    public RenderPanel(JungleMap map, MapSimulation simulation) {
        this.map = map;
        this.simulation = simulation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.7), simulation.frame.getHeight() - 38);
        this.setLocation((int) (0.3 * simulation.frame.getWidth()), 0);
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / map.upperRight.getX());
        int heightScale = height / map.upperRight.getY();
//Field
        g.setColor(new Color(29, 255, 0));
        g.fillRect(0, 0, width, height);

//Jungle
        g.setColor(new Color(51, 151, 0));
        g.fillRect(map.jungleLowerLeft.getX() * widthScale,
                map.jungleLowerLeft.getY() * heightScale,
                (map.jungleUpperRight.getX() - map.jungleLowerLeft.getX()) * widthScale,
                (map.jungleUpperRight.getY() - map.jungleLowerLeft.getY()) * heightScale);


        for (Grass grass : map.getGrass()) {
            g.setColor(grass.toColor());
            int y = map.toNoBoundedPosition(grass.getPosition()).getY() * heightScale;
            int x = map.toNoBoundedPosition(grass.getPosition()).getX() * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }


        for (Animal a : map.getAnimals()) {
            g.setColor(a.toColor());
            int y = map.toNoBoundedPosition(a.getPosition()).getY() * heightScale;
            int x = map.toNoBoundedPosition(a.getPosition()).getX() * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }

}