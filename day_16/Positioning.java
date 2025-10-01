package day_16;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Positioning {
    private int x;
    private int y;
    private Point point;
    private Direction direction;
    private int score;
    private boolean complete;
    private List<Positioning> previous;

    public Positioning(int x, int y, Direction direction, int score, List<Positioning> previous) {
        this.x = x;
        this.y = y;
        this.point = new Point(x, y);
        this.direction = direction;
        this.score = score;
        this.complete = false;
        this.previous = previous;
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

    public boolean isComplete() {
        return complete;
    }

    public boolean setComplete(boolean complete) {
        return this.complete = complete;
    }

    public List<Positioning> getPrevious() {
        return previous;
    }

    public void setPrevious(List<Positioning> previous) {
        this.previous = previous;
    }

    public void addPrevious(Positioning previous) {
        this.previous.add(previous);
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

    public List<Positioning> getAdjacentPositionings2(String[][] maze)
    {
        Positioning clockwiseRotation = new Positioning(this.x, this.y, this.direction.clockwise(), 1000, new ArrayList<>(List.of(this)));
        Positioning counterClockwiseRotation = new Positioning(this.x, this.y, this.direction.counterClockwise(), 1000, new ArrayList<>(List.of(this)));
        Point directionVector = this.direction.getDirectionVector();
        if (!maze[this.y + directionVector.y][this.x + directionVector.x].equals("#")) {
            Positioning forwardMovement = new Positioning(this.x + directionVector.x, this.y + directionVector.y, this.direction, 1, new ArrayList<>(List.of(this)));
            return Arrays.asList(forwardMovement, clockwiseRotation, counterClockwiseRotation);
        }
        return List.of(clockwiseRotation, counterClockwiseRotation);
    }

    public String toString()
    {
        return "Positioning{" +
                "x=" + x +
                ", y=" + y +
                ", point=" + point +
                ", direction=" + direction +
                ", score=" + score +
                '}';
    }
}
