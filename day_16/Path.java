package day_16;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Point> visitedPoints;
    private boolean isComplete;
    private String path;

    public Path(List<Point> visitedPoints, boolean isComplete, String path) {
        this.visitedPoints = visitedPoints;
        this.isComplete = isComplete;
        this.path = path;
    }

    public List<Point> getVisitedPoints() {
        return visitedPoints;
    }

    public void setVisitedPoints(List<Point> visitedPoints) {
        this.visitedPoints = visitedPoints;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Path copy() {
        return new Path(new ArrayList<>(this.visitedPoints), this.isComplete, this.path);
    }

    public void appendPath(String direction) {
        this.path += direction;
    }

    public void printPath() {
        System.out.println("Walked path: " + this.path);
        System.out.println("Current position: " + this.getCurrentPosition());
        System.out.println("Current direction: " + this.getCurrentDirection());
    }

    public Point getCurrentPosition() {
        return this.visitedPoints.get(this.visitedPoints.size() - 1);
    }

    public String getCurrentDirection() {
        return String.valueOf(this.path.charAt(this.path.length() - 1));
    }
}
