package agh.cs.project.app.visualization;

import agh.cs.project.Animal;
import agh.cs.project.Genotype;
import agh.cs.project.JungleMap;
import agh.cs.project.enums.MoveDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlotRenderPanel extends JPanel implements ActionListener {

    private JungleMap map;
    private MapSimulation simulation;
    private int totalDays;
    private int averageEnergy;
    private double averageChildCount;
    private int maxCountGen;
    private int maxIndexGen;
    Integer[] maxCountGens = new Integer[8];
    private JButton pauseButton;

    public PlotRenderPanel(JungleMap map, MapSimulation simulation)  {
        this.map = map;
        this.simulation = simulation;
        totalDays = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.3), simulation.frame.getHeight() - 38);
        this.setLocation(0, 0);
        int width = this.getWidth();
        int height = this.getHeight();
        totalDays++;
        averageEnergy = 0;
        for (Animal animal : map.getAnimals()) {
            averageEnergy += animal.getEnergy();
        }
        averageChildCount = 0;
        for (Animal animal : map.getAnimals()) {
            averageChildCount += animal.getChildren().size();
        }


        maxCountGen = 0;
        maxIndexGen = 0;
        maxCountGens = new Integer[8];
        for (int i = 0; i < 8; i++) {
            maxCountGens[i] = 0;
        }
        for (Animal animal : map.getAnimals()) {
            for (int i = 0; i < 8; i++) {
                maxCountGens[i] += animal.getGens().getGeneCountArray()[i];
            }
        }
        for (int i = 0; i < 8; i++) {
            if (maxCountGens[i] > maxCountGen) {
                maxCountGen = maxCountGens[i];
                maxIndexGen = i;
            }
        }

        g.drawString("Total days: " + totalDays, 10, height - 20);
        g.drawString("Total animals: " + map.getAnimals().size(), 10, height - 40);
        g.drawString("Total grass: " + map.getGrass().size(), 10, height - 60);
        g.drawString("Average energy: " + averageEnergy / map.getAnimals().size(), 10, height - 80);
        g.drawString("Average children percentage: " + (int) (averageChildCount / map.getAnimals().size() * 100), 10, height - 100);
        g.drawString("Most popular gen: " + MoveDirection.findType(maxIndexGen), 10, height - 120);
        g.drawString("Average dead animals life length: " + map.deadAnimalLifeLength / ((map.deadAnimalCount == 0) ? 1 : map.deadAnimalCount), 10, height - 140);
        FunctionPlot p1 = new FunctionPlot(width - 10, 0, 5, 0);

        g.setColor(new Color(0, 0, 0));
        g.drawString("Mapping: ", p1.cpx, p1.cpy + height / 10);
        int yp = p1.cpy + height / 10;
        int a = height / 22;
        g.drawString("Steppe Field ", p1.cpx + width / 10, yp + a);
        g.drawString("Jungle Field ", p1.cpx + width / 10, yp + 2 * a);
        g.drawString("Animal (Changes color to darker when has more energy)", p1.cpx + width / 10, yp + 3 * a);
        g.drawString("Grass ", p1.cpx + width / 10, yp + 4 * a);
//Field
        g.setColor(new Color(29, 255, 0));
        g.fillRect(p1.cpx, yp + a - 10, width / 20, height / 40);
//Jungle
        g.setColor(new Color(17, 233, 0));
        g.fillRect(p1.cpx, yp + 2 * a - 10, width / 20, height / 40);
//Animal
        g.setColor(new Color(55, 31, 23));
        g.fillOval(p1.cpx, yp + 3 * a - 10, width / 20, height / 40);
//Grass
        g.setColor(new Color(89, 201, 71));
        g.fillRect(p1.cpx, yp + 4 * a - 10, width / 20, height / 40);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
