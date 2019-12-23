package agh.cs.project.interfaces;

import agh.cs.project.Vector2d;

public interface IMapElement {
    Vector2d position = null;
    IWorldMap iWorldMap = null;

    Vector2d getPosition();
}
