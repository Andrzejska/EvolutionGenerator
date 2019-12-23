package agh.cs.project.enums;


import agh.cs.project.Vector2d;

public enum MapDirection {
    NORTH(0),NORTH_EAST(1), EAST(2),SOUTH_EAST(3), SOUTH(4), SOUTH_WEST(5),WEST(6), NORTH_WEST(7);

    private final int id;

    MapDirection(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MapDirection findType(int type) {
        for (MapDirection t : values()) {
            if (t.id == type) return t;
        }
        return null;
    }


    public Vector2d toUnitVector(MapDirection mapDirection) {

        switch (mapDirection) {

            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case EAST:
                return new Vector2d(1, 0);
            case NORTH_WEST:
                return new Vector2d(-1, 1);
            case NORTH_EAST:
                return new Vector2d(1, 1);
            case SOUTH_WEST:
                return new Vector2d(-1, -1);
            case SOUTH_EAST:
                return new Vector2d(1, -1);
            default:
                return null;
        }
    }
}
