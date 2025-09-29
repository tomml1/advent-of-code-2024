package day_16;

import java.awt.*;
import java.util.Map;

public class Graph {
    private String[][] grid;
    private Point startNode;
    private Point endNode;
    private Map<Point, Integer> distanceToStartNode;
    private Map<Point, Point> previousNode;
}
