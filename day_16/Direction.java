package day_16;

import java.awt.*;

public enum Direction {
    NORTH(new Point(0, -1)), EAST(new Point(1, 0)), SOUTH(new Point(0, 1)), WEST(new Point(-1, 0));

    private final Point directionVector;

    Direction(Point directionVector) {
        this.directionVector = directionVector;
    }

    public Point getDirectionVector() {
        return this.directionVector;
    }

    public Direction clockwise() {
        int ordinal = this.ordinal() + 1;
        int length = values().length;
        int modulo = ((ordinal % length) + length) % length;
        return values()[modulo];
    }

    public Direction counterClockwise() {
        int ordinal = this.ordinal() - 1;
        int length = values().length;
        int modulo = ((ordinal % length) + length) % length;
        return values()[modulo];
    }
}
