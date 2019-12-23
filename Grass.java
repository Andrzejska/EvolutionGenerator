package agh.cs.project;

import agh.cs.project.interfaces.IMapElement;

import java.awt.*;

public class Grass implements IMapElement {
    private Vector2d position;

    public Grass(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }

    public Color toColor() {
        return new Color(89, 201, 71);
    }
}
