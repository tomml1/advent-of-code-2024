package day_16;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Positioning {
    private int x;
    private int y;
    private Point point;
    private Direction direction;
    private int score;

    public Positioning(int x, int y, Direction direction, int score) {
        this.x = x;
        this.y = y;
        this.point = new Point(x, y);
        this.direction = direction;
        this.score = score;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Positioning that = (Positioning) o;
        return x == that.x && y == that.y && Objects.equals(point, that.point) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, point, direction);
    }

    public List<Positioning> getAdjacentPositionings(String[][] maze)
    {
        Positioning clockwiseRotation = new Positioning(this.x, this.y, this.direction.clockwise(), this.score + 1000);
        Positioning counterClockwiseRotation = new Positioning(this.x, this.y, this.direction.counterClockwise(), this.score + 1000);
        Point directionVector = this.direction.getDirectionVector();
        if (!maze[this.y + directionVector.y][this.x + directionVector.x].equals("#")) {
            Positioning forwardMovement = new Positioning(this.x + directionVector.x, this.y + directionVector.y, this.direction, this.score + 1);
            return Arrays.asList(forwardMovement, clockwiseRotation, counterClockwiseRotation);
        }
        return List.of(clockwiseRotation, counterClockwiseRotation);
    }
}
