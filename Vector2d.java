package agh.cs.project;

public class Vector2d {
    final int x;
    final int y;


    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    boolean precedes(Vector2d other) {
        return (this.x <= other.x && this.y <= other.y);
    }

    boolean follows(Vector2d other) {
        return (this.x >= other.x && this.y >= other.y);
    }

    Vector2d upperRight(Vector2d other) {
        return new Vector2d((this.x > other.x ? this.x : other.x), this.y > other.y ? this.y : other.y);
    }

    Vector2d lowerLeft(Vector2d other) {
        return new Vector2d((this.x < other.x ? this.x : other.x), this.y < other.y ? this.y : other.y);

    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2d)) return false;
        Vector2d vector2d = (Vector2d) other;
        return (this.x == vector2d.x && this.y == vector2d.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-1 * this.x, -1 * this.y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
