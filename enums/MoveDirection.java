package agh.cs.project.enums;

public enum MoveDirection {
    FORWARD(8), SPIN_0(0), SPIN_45(1), SPIN_90(2), SPIN_135(3), SPIN_180(4),
    SPIN_225(5), SPIN_270(6), SPIN_315(7), UNKNOWN(-1);
   public final int id;

    public int getId() {
        return id;
    }


    MoveDirection(int id) {
        this.id=id;
    }

    MoveDirection(){
        this.id=0;
    }

    public static MoveDirection findType(int type) {
        for (MoveDirection t : values()) {
            if (t.id == type) return t;
        }
        return UNKNOWN;
    }
}